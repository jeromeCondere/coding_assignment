package exercise
import transaction.Transaction

trait Exercise {
	val output_prefix = "output_exercise"

	def solve(transactions: List[Transaction])
}