package makarenko.interview.namesemails

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class FunctionalTest {
    @Test
    fun test() {
        val data = "user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru\n" +
                "user2 -> foo@gmail.com, ups@pisem.net\n" +
                "user3 -> xyz@pisem.net, vasya@pupkin.com\n" +
                "user4 -> ups@pisem.net, aaa@bbb.ru\n" +
                "user5 -> xyz@pisem.net"
        val input = ByteArrayInputStream(data.toByteArray())
        val solver = Solver()
        val result = solver.solve(input)

        Assertions.assertEquals(setOf("user1", "user3"), result.keys)
        Assertions.assertEquals(
            setOf("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"),
            result["user1"]
        )
        Assertions.assertEquals(setOf("xyz@pisem.net", "vasya@pupkin.com"), result["user3"])
    }
}