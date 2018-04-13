import org.scalatest._
import transaction._
import exercise._

class ExerciseSpec extends FlatSpec {
	"Exercise1" should "Calculate the total transaction value for all transactions for each day" in {

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

	"Exercise2" should "Calculate the average value of transactions per account for each type of transaction" in {

		val transactions = List(
			Transaction("T1","A1",1,"AA",5),
			Transaction("T2","A1",1,"AA",15),
			Transaction("T3","A1",1,"BB",4),
			Transaction("T4","A1",1,"BB",2),
			Transaction("T5","A1",1,"BB",3),
			Transaction("T6","A1",2,"CC",20),
			Transaction("T7","A1",2,"DD",9),
			Transaction("T8","A1",3,"EE",1),
			Transaction("T9","A1",3,"FF",17),

			Transaction("T11","A2",1,"AA",5),
			Transaction("T12","A2",1,"AA",15),
			Transaction("T13","A2",1,"BB",4),
			Transaction("T14","A2",1,"BB",2),
			Transaction("T15","A2",1,"BB",3),
			Transaction("T16","A2",2,"CC",20),
			Transaction("T17","A2",2,"DD",9),
			Transaction("T18","A2",3,"EE",1),
			Transaction("T19","A2",3,"FF",17),
		)
		val results = Exercise2.solve(transactions)
		assert(results.size == 2) //one result by day
		assert(results("A1") == 9)
		assert(results(2) == 29)
		assert(results(3) == 1)
	}

	"Exercise3" should "Compute statistics based on a five days rolling window per account" in {
		fail
	}

}