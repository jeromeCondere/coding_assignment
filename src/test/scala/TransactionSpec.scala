import org.scalatest._
import transaction._
import transaction.implicits.TransactionImplicits._

class TransactionSpec extends FlatSpec {
	"A line" should "be correctly converted into a transaction" in {

		val line = "Transaction1,Account27,1,category27,338.11"
		val transaction: Transaction = line
		assert(transaction.transactionId == "Transaction1")
		assert(transaction.accountId == "Account27")
		assert(transaction.transactionDay == 1)
		assert(transaction.category == "category27")
		assert(transaction.transactionAmount == 338.11)
	}

}