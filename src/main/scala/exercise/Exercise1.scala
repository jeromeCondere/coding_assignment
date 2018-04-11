package exercise
import transaction.Transaction
import scala.collection.immutable.ListMap
import java.io._

object Exercise1 extends Exercise {
	def solve(transactions: List[Transaction]) = {
		val res = transactions.groupBy(_.transactionDay).mapValues(_.map(_.transactionAmount).sum)
		val resOrdered = ListMap(res.toSeq.sortWith(_._1 < _._1):_*) //sort by transactionDay
		resOrdered.foreach(println)

		//save result
		val file = new File(output_prefix+"1")
		val bw = new BufferedWriter(new FileWriter(file))
		bw.write("day,total_transactions\n")
		resOrdered.foreach{case (a,b) => bw.write(a+","+b+"\n")}
		bw.close()
	}
}