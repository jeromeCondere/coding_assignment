import scala.io.{Source, StdIn}
import transaction._
import transaction.implicits.TransactionImplicits._
import exercise._


object Solver extends App {
	println("exercises to solve")
	val transactionslines = Source.fromFile("transactions.txt").getLines().drop(1)
	val transactions = transactionslines.map(line => line: Transaction).toList

	print("Enter the exercise number: ")
	try {
		StdIn.readInt match {
			case 1 => Exercise1.execute(transactions)
			case 2 => Exercise2.execute(transactions)
			case 3 => Exercise3.execute(transactions)
			case _ => println("index out of bounds!")
		}
	} catch {
		case _: Throwable => println("unexpected input!")
	}
}