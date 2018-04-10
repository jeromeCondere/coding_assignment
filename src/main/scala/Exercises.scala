import scala.io.Source
import transaction._
import transaction.implicits.TransactionImplicits._
import scala.collection.immutable.ListMap
import java.io._

object Exercises extends App {
	println("exercises to solve")
	val transactionslines = Source.fromFile("transactions.txt").getLines().drop(1)
	val transactions = transactionslines.map(line => line: Transaction).toList
	val output_prefix = "output_exercise"

	def exercise1 = {
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
	exercise1
}