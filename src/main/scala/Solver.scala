import scala.io.Source
import transaction._
import transaction.implicits.TransactionImplicits._
import exercise._


object Solver extends App {
	println("exercises to solve")
	val transactionslines = Source.fromFile("transactions.txt").getLines().drop(1)
	val transactions = transactionslines.map(line => line: Transaction).toList

	Exercise1.solve(transactions)
}