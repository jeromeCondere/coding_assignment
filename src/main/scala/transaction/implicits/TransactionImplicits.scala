package transaction.implicits
import transaction.Transaction

object TransactionImplicits {
	implicit def string2transaction(line: String) = {
		val values = line.split(',')
		Transaction(values(0), values(1), values(2).toInt, values(3), values(4).toDouble)
	}
}