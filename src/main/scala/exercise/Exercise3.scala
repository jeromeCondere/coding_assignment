package exercise
import transaction.Transaction
import scala.collection.immutable.ListMap
import java.io._
import scala.math.Ordering

object Exercise3 extends Exercise[(Int,String),List[Double]] {
	def solve(transactions: List[Transaction]) = {
		val maxDay = transactions.map(_.transactionDay).max
		val resultMapStats = (6 to maxDay).map(day => rollingWindowStats(transactions,day)).flatten.toMap

		val orderedSeq = resultMapStats.toSeq.sortBy{case((day,accountId),_) => (day, accountId)}(Ordering[(Int, String)])
		ListMap(orderedSeq:_*)
	}

	//get statistics for one day
	def rollingWindowStats(transactions: List[Transaction], day: Int) = {
		val categories = List("AA", "CC", "FF")
		transactions.filter{
			t => ((day - 5) until day).contains(t.transactionDay) // filter on the five previous days
		}.groupBy(transaction => (day, transaction.accountId))
		.mapValues{
			listTransaction => List(
				listTransaction.map(_.transactionAmount).max,
				listTransaction.map(_.transactionAmount).sum / listTransaction.length,
			) ++ categories.map{
				category => listTransaction.find(_.category == category) match {
					case None => 0
					case _ => listTransaction.filter(_.category == category).map(_.transactionAmount).sum
				}
			}
		}
	}

	def write(result: ListMap[(Int,String),List[Double]]) = {
		val file = new File(output_prefix+"3.csv")
		val bw = new BufferedWriter(new FileWriter(file))

		bw.write("day,accountId,maximum,average,AA_Total_Value,CC_Total_Value,FF_Total_Value\n")
		result.foreach{
			case (a,b) => bw.write(a._1+","+a._2+","+b.mkString(",")+"\n")
		}
		bw.close()
	}

	def statement = "For each day, calculate statistics for each account number for the previous five days of transactions, not including transactions from the day statistics are being calculated for"
}