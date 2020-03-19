package makarenko.interview.namesemails

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solver = Solver()
            val result = solver.solve(System.`in`)
            result.forEach {
                val list = it.value.joinToString(", ")
                println("${it.key} -> $list")
            }
        }
    }
}