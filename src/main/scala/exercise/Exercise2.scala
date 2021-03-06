package exercise
import transaction.Transaction
import scala.collection.immutable.{ListMap, Iterable, Map}
import java.io._
import scala.math.Ordering

object Exercise2 extends Exercise[String,List[Double]] {
	val categories = ('A' to 'G').map(c => c+""+c)

	def solve(transactions: List[Transaction]) = {	
		val meanByAccountCategory = transactions.groupBy(transaction => (transaction.accountId, transaction.category)) //groupBy accountId and category
					.mapValues( listTransaction  => listTransaction.map(_.transactionAmount).sum / listTransaction.length) // compute average

		//groupBy accountId
		val finalRes = meanByAccountCategory.toSeq.groupBy(_._1._1).mapValues{
			listOfGroups => categories.map{
				category => listOfGroups.find{
						case(((_,groupCategory),_)) => groupCategory == category
					} match {
					case Some(((_,_),transactionAverage)) => transactionAverage //the category is available so we return the average value of transactions
					case None => 0 //the category is missing for this account
				}
			}.toList
		}

	    ListMap(finalRes.toSeq.sortBy(_._1):_*)
	 }

	def write(result: ListMap[String,List[Double]]) = {
		val file = new File(output_prefix+"2.csv")
		val bw = new BufferedWriter(new FileWriter(file))

		bw.write("accountId,"+ categories.mkString(",")+"\n")
		result.foreach{
			case(accountId, averageValues) =>  bw.write(accountId+","+averageValues.mkString(",")+"\n")
		}

		bw.close()
	}

	def statement = "Calculate the average value of transactions per account for each type of transaction (there are seven in total)."
}