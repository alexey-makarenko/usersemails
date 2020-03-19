package makarenko.interview.namesemails

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MergerTest {

    @Test
    fun `get user by email`() {
        val merger = Merger()
        merger.add("u1", arrayOf("a@a", "b@b"))

        Assertions.assertEquals("u1", merger.getUser("a@a"))
        Assertions.assertEquals("u1", merger.getUser("b@b"))
    }

    @Test
    fun `basic calculate`() {
        val merger = Merger()
        merger.add("u1", arrayOf("a@a", "b@b"))

        val result = merger.calculate()

        Assertions.assertEquals(setOf("u1"), result.keys)
        Assertions.assertEquals(setOf("a@a", "b@b"), result["u1"])
    }

    @Test
    fun `no common merge`() {
        val merger = Merger()
        merger.add("u1", arrayOf("a@a"))
        merger.add("u2", arrayOf("b@b"))

        val result = merger.calculate()

        Assertions.assertEquals(setOf("u1", "u2"), result.keys)
        Assertions.assertEquals(setOf("a@a"), result["u1"])
        Assertions.assertEquals(setOf("b@b"), result["u2"])
    }

    @Test
    fun `with common merge`() {
        val merger = Merger()
        merger.add("u1", arrayOf("a@a"))
        merger.add("u2", arrayOf("b@b", "a@a"))

        val result = merger.calculate()

        Assertions.assertEquals(setOf("u1"), result.keys)
        Assertions.assertEquals(setOf("a@a", "b@b"), result["u1"])
    }

    @Test
    fun `example input test`() {
        val merger = Merger()
        merger.add("user1", arrayOf("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"))
        merger.add("user2", arrayOf("foo@gmail.com", "ups@pisem.net"))
        merger.add("user3", arrayOf("xyz@pisem.net", "vasya@pupkin.com"))
        merger.add("user4", arrayOf("ups@pisem.net", "aaa@bbb.ru"))
        merger.add("user5", arrayOf("xyz@pisem.net"))

        val result = merger.calculate()

        Assertions.assertEquals(setOf("user1", "user3"), result.keys)
        Assertions.assertEquals(
            setOf("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"),
            result["user1"]
        )
        Assertions.assertEquals(setOf("xyz@pisem.net", "vasya@pupkin.com"), result["user3"])
    }

}
