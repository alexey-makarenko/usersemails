package makarenko.interview.namesemails

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DisjointTest {

    @Test
    fun `self parents`() {
        val ds = DisjointSet()
        ds.makeSet("a")
        ds.makeSet("b")
        Assertions.assertEquals("a", ds.findSet("a"))
        Assertions.assertEquals("b", ds.findSet("b"))
    }

    @Test
    fun `simple union`() {
        val ds = DisjointSet()
        ds.makeSet("a")
        ds.makeSet("b")
        ds.union("a", "b")
        Assertions.assertEquals("a", ds.findSet("a"))
        Assertions.assertEquals("a", ds.findSet("b"))
    }

    @Test
    fun `rank optimization`() {
        val ds = DisjointSet()
        ds.makeSet("a")
        ds.makeSet("b")
        ds.makeSet("c")
        ds.makeSet("d")
        ds.union("a", "b")
        ds.union("a", "c")
        ds.union("d", "c")
        Assertions.assertEquals("a", ds.findSet("b"))
        Assertions.assertEquals("a", ds.findSet("c"))
        Assertions.assertEquals("a", ds.findSet("d"))
    }
}
