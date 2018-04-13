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
			Transaction("T2","A1",1,"AA",15),// mean = 10
			Transaction("T3","A1",1,"BB",4),
			Transaction("T4","A1",1,"BB",2),
			Transaction("T5","A1",1,"BB",3),// mean = 3
			Transaction("T6","A1",2,"CC",20),// mean = 20
			Transaction("T7","A1",2,"DD",9),// mean = 9
			Transaction("T8","A1",3,"EE",1),// mean = 1
			Transaction("T9","A1",3,"FF",17),// mean = 17
			Transaction("T91","A1",3,"GG",5),
			Transaction("T92","A1",3,"GG",2),// mean = 3.5

			Transaction("T11","A2",1,"AA",6),
			Transaction("T12","A2",1,"AA",8),// mean = 7
			Transaction("T13","A2",1,"BB",4),
			Transaction("T14","A2",1,"BB",11),// mean = 7.5
			Transaction("T16","A2",2,"CC",140),// mean = 140
			Transaction("T19","A2",3,"FF",17),
			Transaction("T19","A2",3,"FF",1),// mean = 9
			Transaction("T191","A2",3,"GG",1),// mean = 1
		)

		//the listmap matches shall this pattern (accountId -> List(mean(AA),mean(BB),mean(CC),mean(DD),mean(EE),mean(FF),mean(GG)))
		val results = Exercise2.solve(transactions)
		
		assert(results.size == 2) //one result by day
		results.foreach{
			case (_,list) => assert(list.length == 7)
		}
		assert(results("A1") == List(10,3,20,9,1,17,3.5))
		assert(results("A2") == List(7,7.5,140,0,0,9,1))


		val otherTransactions = List(
			Transaction("T1","A1",1,"AA",5),
			Transaction("T2","A1",1,"AA",15),
			Transaction("T2","A1",1,"AA",13),// mean = 10
			Transaction("T6","A1",2,"CC",20),// mean = 20

			Transaction("T11","A2",1,"AA",6),
			Transaction("T12","A2",1,"AA",8),// mean = 7
			Transaction("T191","A2",3,"GG",1),// mean = 1
		)

		val otherResults = Exercise2.solve(otherTransactions)
		
		assert(otherResults.size == 2) //one result by day
		otherResults.foreach{
			case (_,list) => assert(list.length == 7)
		}
		assert(otherResults("A1") == List(11,0,20,0,0,0,0))
		assert(otherResults("A2") == List(7,0,0,0,0,0,1))
	}

	"Exercise3" should "Compute statistics based on a five days rolling window per account (for one day)" in {
		val day6 = 6
		// categories = List("AA", "CC", "FF")
		val transactions = List(
			Transaction("T1","A1",1,"AA",5),
			Transaction("T2","A1",1,"AA",15),

			Transaction("T1","A1",2,"CC",2),
			Transaction("T2","A1",2,"FF",4),

			Transaction("T1","A1",3,"AA",50),//max
			Transaction("T2","A1",3,"AA",7),

			Transaction("T1","A1",4,"CC",11),
			Transaction("T2","A1",4,"FF",3),

			Transaction("T1","A1",5,"CC",4),
			Transaction("T2","A1",5,"CC",9)
		)

		val resultsForDay6 = Exercise3.rollingWindowStats(transactions,day6)
		assert(resultsForDay6((day6,"A1")) == List(50,11,77,26,7))

		//test with missing values

		val otherTransactions = List(
			Transaction("T1","A1",2,"AA",5),
			Transaction("T2","A1",3,"AA",15),//max for A1

			Transaction("T1","A2",5,"CC",4),
			Transaction("T2","A2",5,"CC",9),//max for A2
		)
		val otherResultsForDay6 = Exercise3.rollingWindowStats(otherTransactions,day6)
		assert(otherResultsForDay6((day6,"A1")) == List(15,10,20,0,0))
		assert(otherResultsForDay6((day6,"A2")) == List(9,6.5,0,13,0))
	}

}