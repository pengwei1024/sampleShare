import java.net.URL

/**
 * Created by pengwei on 2017/6/24.
 */
fun main(args: Array<String>) {

    /*loop@ for(i in 1..100){
        print(i)
    }*/


    val fruits = listOf("apple", "banana", "kiwi")

    val forecastJsonStr = URL("http://www.baidu.com").readText()
    println(forecastJsonStr)

    fruits.forEach {
        x -> println(x)
    }

    println("************")


    fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }

    val r1 = 1..5
    for (i in r1) {
        print(i)
    }

    println("hello world")

    loop(arrayOf("1", "3", "5"))

    for (i in 1..5) {
        print(i)
    }

    val language = if (args.size == 0) "EN" else args[0]
    println(when (language) {
        "EN" -> "Hello!"
        "FR" -> "Salut!"
        "IT" -> "Ciao!"
        else -> "Sorry, I can't greet you in $language yet"
    })

    if (args.isEmpty()) {
        println("empty")
        return
    }
    println("first=${args[0]}")

    for (name in args) {
        println("hello $name")
    }

    fun max(a: Int, b: Int) = if (a > b) a else b
}


fun parseInteger(str: String): Int? {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        return null
    }
}

fun getLength(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }
    return null
}

fun loop(args: Array<String>) {
    for (i in args) {
        println(i)
    }

    for (i in args.indices) {
        println("index:$i, value:${args[i]}")
    }
}