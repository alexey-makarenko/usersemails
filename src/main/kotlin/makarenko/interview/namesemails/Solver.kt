package makarenko.interview.namesemails

import java.io.InputStream
import java.util.*

class Solver {
    fun solve(input: InputStream): Map<String, Set<String>> {
        val scan = Scanner(input)
        val merger = Merger()
        while (scan.hasNextLine()) {
            val nextLine: String = scan.nextLine()
            val arrow = nextLine.findAnyOf(listOf("->")) ?: return emptyMap()
            val user = nextLine.substring(0, arrow.first).trim()
            val emailLine = nextLine.substring(arrow.first + 2, nextLine.length)
            val emails = emailLine.split(',').map { it.trim() }.toTypedArray()
            merger.add(user, emails)
        }
        return merger.calculate()
    }
}
