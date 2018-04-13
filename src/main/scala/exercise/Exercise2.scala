package exercise
import transaction.Transaction
import scala.collection.immutable.{ListMap, Iterable}
import java.io._
import scala.math.Ordering

object Exercise2 extends Exercise[String,List[Double]] {
	def solve(transactions: List[Transaction]) = {	
		val res = transactions.groupBy(transaction => (transaction.accountId, transaction.category)) //groupBy accountId and category
					.mapValues( listTransaction  => listTransaction.map(_.transactionAmount).sum / listTransaction.length) // compute average


		//Sort the completed Map by accountId and category
		val orderedSeq = completeResult(res).toSeq.sortBy(x => (x._1._1, x._1._2))(Ordering[(String, String)])
		val orderedRes = ListMap(orderedSeq:_*)

		/*
		Restructure data for file processing.
		The groupBy doesn't alter the order of the ListMap
		*/
		val structuredRes = orderedRes.groupBy(_._1._1).mapValues(_.map(_._2).toList)
	    ListMap(structuredRes.toSeq.sortBy(_._1):_*)
	 }
 
	 //Fills the resulted average map with the missing values
	 def completeResult(res: Map[(String, String), Double]) = {
	 	val accountSet = res.keySet.map(_._1)
	 	val categorySet = res.keySet.map(_._2)

	 	//create all tuples of type (accountId, category)
	 	val tuples = for {
	 		client <- accountSet
	 		category <- categorySet
	 	} yield (client, category)

	 	// if the combinaison is not found in res we create a key associate with a zero value
	 	tuples.foldLeft(Map[(String, String), Double]()){
	 		case (map, t)=> res.get(t) match {
	 			case Some(value) => map + (t-> value)
	 			case None => map + (t -> 0.0)
	 		}
	 	}
	 }

	def write(res: ListMap[String,List[Double]]) = {
		val file = new File(output_prefix+"2")
		val bw = new BufferedWriter(new FileWriter(file))
		bw.write("accountId,"+ categories.mkString(",")+"\n")
		res.foreach{
			case(accountId, averageValues) =>  bw.write(accountId+","+averageValues.mkString(",")+"\n")
		}
		bw.close()
	}
}