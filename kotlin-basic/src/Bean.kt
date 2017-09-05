/**
 * Created by pengwei on 2017/7/2.
 */

class Book () {

    var name: String = ""
        get() = field;
        set(value) {
            field = "set $value" ;
        }
}

class User(private var name: String) {

    private var description: String? = null

    init {
        name = "Zhang Tao"
    }

    constructor(name: String, description: String) : this(name) {
        this.description = description
    }

    internal fun sayHello() {
        println("hello $name")
    }

    fun  good() {
        println("hello $name")
    }
}

fun main(args: Array<String>) {
    val  book = Book()
    book.name = "1123"
    print(book.name)


    val user = User("你好", "")
    user.sayHello()
    user.good()
}