import org.scalatest._
import transaction._
import exercise._

class ExerciseSpec extends FlatSpec {
	"Exercise1" should "Calculate the total transaction value for all transactions for each day" in {

		val line = "Transaction1,Account27,1,category27,338.11"
		val transactions = List(
			Transaction("T1","A1",1,"AA",5),
			Transaction("T2","A1",1,"AA",4),
			Transaction("T3","A1",2,"AA",20),
			Transaction("T4","A1",2,"AA",9),
			Transaction("T5","A1",3,"AA",1),
		)
		val results = Exercise1.solve(transactions)
		assert(results.size == 3) //one result by day
		assert(results(1) == 9)
		assert(results(2) == 29)
		assert(results(3) == 1)
	}

}