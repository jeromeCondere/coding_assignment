package exercise
import transaction.Transaction
import scala.collection.immutable.ListMap

trait Exercise[A,B] {
	val output_prefix = "output_exercise"
	val categories = ('A' to 'G').map(c => c+""+c)
	
	/**Solve the exercise*/
	def solve(transactions: List[Transaction]): ListMap[A,B]

	/**Save output*/
	def write(result: ListMap[A,B])

	final def execute(transactions: List[Transaction]) = {
		val res = solve(transactions)
		res.foreach(println)
		write(res)
	}
}