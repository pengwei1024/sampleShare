package delegate

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by pengwei on 2017/8/7.
 */

class Example {
    var p: String by Delegate()
    var w: String? by A()
    val x:String? by B()
}

class Delegate() {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${prop.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        println("$value has been assigned to ${prop.name} in $thisRef")
    }
}


class A : ReadWriteProperty<Any?, String?> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String? {
        return "aaaa"
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
        println("setValue=" + value)
    }

}

class B : ReadOnlyProperty<Any?, String?> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}




fun main(args: Array<String>) {
    val e = Example()
    println(e.p)
    e.p = "NEW"


    println(e.w)
    e.w = "change"

    println(e.x)
}