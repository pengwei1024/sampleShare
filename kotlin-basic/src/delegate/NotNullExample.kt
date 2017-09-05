package delegate

import kotlin.properties.Delegates

/**
 * Created by pengwei on 2017/8/7.
 */

class User {
    var name: String by Delegates.notNull()

    fun init(name: String) {
        this.name = name
    }
}

fun main(args: Array<String>) {
    val user = User()
    print(user.name)
    // user.name -> IllegalStateException
//    user.init("Carl")
//    println(user.name)
}
