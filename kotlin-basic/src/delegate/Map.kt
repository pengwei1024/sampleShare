package delegate

/**
 * Created by pengwei on 2017/8/7.
 */

class UserX(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

fun main(args: Array<String>) {
    val user = UserX(mapOf(
            "name" to "John Doe",
            "age"  to 123
    ))

    // key 不存在报错  Key age is missing in the map.
    // 类型不一致 java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Number

    println("name = ${user.name}, age = ${user.age}")
}
