package delegate

/**
 * Created by pengwei on 2017/8/7.
 */

import kotlin.properties.Delegates

class User2 {
    var name: String by Delegates.observable("oldName") {
        kProperty, old, new ->
        println("${kProperty.returnType}, $old -> $new")
    }

    // 默认值不受 vetoable 影响
    var address: String by Delegates.vetoable("wan", {
        kProperty, oldValue, newValue ->
        println("oldValue：$oldValue | newValue：$newValue")
        newValue.contains("wang")
    })
}

fun main(args: Array<String>) {
    val user = User2()
    println(user.name)
    user.name = "Carl"

    user.address = "abcd";
    println(user.address)
}
