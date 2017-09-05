package delegate

/**
 * Created by pengwei on 2017/8/7.
 */

interface Base {
    fun show()
}

open class BaseImpl : Base {
    override fun show() {
        print("BaseImpl::show()")
    }
}

class BaseProxy(base: Base) : Base by base


fun main(args: Array<String>) {
    val base = BaseImpl()
    BaseProxy(base).show()
}
