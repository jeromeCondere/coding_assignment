package exercise
import transaction.Transaction
import scala.collection.immutable.ListMap

trait Exercise[A,B] {
	val output_prefix = "output_exercise"
		
	/**Solve the exercise*/
	def solve(transactions: List[Transaction]): ListMap[A,B]

	/**Save output*/
	def write(result: ListMap[A,B])

	def statement: String

	/**solve and write the solution of the exercise*/
	final def execute(transactions: List[Transaction]) = {
		println(statement+"\n\n")
		val res = solve(transactions)
		res.foreach(println)
		write(res)
	}
}