package exercise
import transaction.Transaction
import scala.collection.immutable.ListMap
import java.io._

object Exercise1 extends Exercise[Int,Double] {
	def solve(transactions: List[Transaction]) = {
		val res = transactions.groupBy(_.transactionDay).mapValues(_.map(_.transactionAmount).sum)
		ListMap(res.toSeq.sortBy(_._1):_*) //sort by transactionDay
	}

	def write(res: ListMap[Int,Double]) = {
		val file = new File(output_prefix+"1.csv")
		val bw = new BufferedWriter(new FileWriter(file))
		
		bw.write("day,total_transactions\n")
		res.foreach{case (a,b) => bw.write(a+","+b+"\n")}
		bw.close()
	}
}