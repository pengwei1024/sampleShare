/**
 * Created by pengwei on 2017/6/24.
 */

class Person (val name: String, val age: Int?) {
    fun action() {
        println("hello $name")
    }
}

fun main(args: Array<String>) {
    Person("sun", 10).action();
}