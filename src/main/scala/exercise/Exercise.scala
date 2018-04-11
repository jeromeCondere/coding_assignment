package exercise
import transaction.Transaction

trait Exercise {
	val output_prefix = "output_exercise"
	val categories = ('A' to 'G').map(c => c+""+c)
	
	def solve(transactions: List[Transaction])
}