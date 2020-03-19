package makarenko.interview.namesemails

class Merger {
    private val emailToUser = mutableMapOf<String, String>()
    private val ds = DisjointSet()

    fun add(user: String, emails: Array<String>) {
        var toUnion: String? = null
        for (email in emails) {
            val existing = emailToUser[email]
            if (existing != null) {
                toUnion = email
            } else {
                emailToUser[email] = user
                ds.makeSet(email)
            }
        }
        if (toUnion == null && emails.isNotEmpty())
            toUnion = emails[0]
        if (toUnion != null)
            (emails.indices).forEach { ds.union(toUnion, emails[it]) }
    }

    fun getUser(email: String): String {
        return emailToUser.getOrDefault(email, "")
    }

    fun calculate(): Map<String, Set<String>> {
        val result = mutableMapOf<String, MutableSet<String>>()
        for (email in emailToUser.keys) {
            val root = ds.findSet(email)
            val user = getUser(root)
            result.computeIfAbsent(user) { mutableSetOf() }.add(email)
        }
        return result
    }
}