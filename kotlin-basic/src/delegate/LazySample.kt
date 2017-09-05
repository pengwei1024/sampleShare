package delegate

/**
 * Created by pengwei on 2017/8/7.
 */
class LazySample {
    val lazyObject: String by lazy {
        println("init!")
        "aaa"
    }

//    constructor() {
//        println("constructor, lazy= ${this.lazy}")
//    }
}

fun main(args: Array<String>) {
    val sample = LazySample()
    println("lazy = ${sample.lazyObject}")
    println("lazy = ${sample.lazyObject}")

//    var kk = lazy {
//        println("yyyy")
//        "xxxx"
//    }
//    println("#=" + kk.value)
}

/**
 * 所有类声明的泛型尖括号里面如果加入了out关键字，则说明这个类的对象是只读的，
 * 例如他只有：get()、size（）等方法，而没有 set()、remove()等方法。
 *
 * 还有一个in。表示泛型参数是只写的。
 */

interface Y<out T> {
    public val value: T
    public fun isInitialized(): Boolean
}

fun <T> x(args: () -> T): Y<T> = Abc(args)

class Abc<out T>(initializer: () -> T) : Y<T> {
    private var initializer: (() -> T)? = initializer
    override fun isInitialized(): Boolean {
        return false;
    }

    override val value: T
        get() {
            val typedValue = initializer!!()
            return typedValue
        }
}