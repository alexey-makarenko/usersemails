package makarenko.interview.namesemails

fun main() {
    val solver = Solver()
    val result = solver.solve(System.`in`)
    result.forEach {
        println("${it.key} -> ${it.value}")
    }
}