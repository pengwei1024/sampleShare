## Android - Kotlin 实践

### 语法糖

- 不用写分号

```
println("hello world")
```

- 变量

```
// 定义可变变量
var name: String = "hello world"

// 定义不可变变量，相当于 Java -> final
val useVal: Int = 1000

var tmpId = 1
//类型推导
```

- 三元运算符

```
// java
int code = isSuccessfully? 200: 400; 
 
// kotlin
int code = if(isSuccessfully) 200 else 400  
```

- import 别名

```
import cn.jasonmarzw.User as Person // 添加User别名为Person

var p = Person() // 初始化 User 类对象
```


- 字符串模板表达式

```
println("index:$i, value:${args.length}")
```

- 空指针安全

```
if (s2 != null) s2.length // Java式的判空方案
s2?.length // Kotlin的安全调用操作符?。当s2为null时，s2?.length也为null
```

- 类型判断

```
if (num instanceof Double) { ... }  // Java代码
if (num is Double) { ... }  // Kotlin代码

// 智能转换
fun main(args: Array<String>) {
	var animal: Animal? = xxx
    if (animal is Dog) {
    	//在这里 animal 被当做 Dog 的对象来处理
       animal.bark()
    }
}
```

- 可变参数

```
// Java
public void displayActors(String... name) {
    System.out.println("actors :" + name);
}

// Kotlin
fun displayActors(vararg name: String) {
    println("actors: " + name);
}
```

- 更强大的 switch

```
when (obj) {
        1 -> println("One")
        "Hello" -> println("Greeting")
        is Long -> println("Long")
        !is String -> println("Not a string")
        else -> println("Unknown")
}
    
when {
    "orange" in items -> println("juicy")
    "apple" in items -> println("apple is fine too")
}    
```

- 循环

```
for(i in arr){
    println(i)
}
//1 2 3

for(j in asc.indices){
    println(j)
}
//0 1 2

for (i in 1..5) {
	print(i)
}
// 1,2,3,4,5

loop@ for (i in 1..10) {
        for (j in 1..10) {
            if (i == 5) {
                continue@loop
            }
        }
    }
// 为循环设置 label  

list.forEach { 
	  if(it > 3) return@forEach 	
     println(it) 
 }  
// forEach 循环 

for ((index, item) in list.withIndex()) {
    println("the element at $index is $item")
}
// index 和 item
```

- 范围表达式

```
val r1 = 1..5
//该范围包含数值1,2,3,4,5  只能升序

val r2 = 5 downTo 1
//该范围包含数值5,4,3,2,1

val r3 = 5 downTo 1 step 2
//该范围包含数值5,3,1

if (x !in 0..array.lastIndex)
    print("Out")
// 不在范围内  

for (i in 1.rangeTo(100) step 20) {
    print("$i ")
}  
```

- 集合遍历

```
// map 遍历
val map = hashMapOf<String, Int>()
    map.put("one", 1)
    map.put("two", 2)

    for ((key, value) in map) {
        println("key = $key, value = $value")
    }
   
// 创建 map    
val map = mapOf("a" to 1, "b" to 2, "c" to 3)    
```

- lambda表达式

```
val fruits = listOf("apple", "banana", "kiwi")
fruits
.filter { it.startsWith("a") }
.sortedBy { it }
.map { it.toUpperCase() }
.forEach { println(it) }
```

- 反射

```
class A(val p: Int)

A::class.java // 获取 java class
A::class // 获取 kclass

println(A::p.javaGetter) // prints "public final int A.getP()"
println(A::p.javaField)  // prints "private final int A.p"

// new Intent
val intent = Intent(this, MainActivity::class.java)
```

- 函数定义

```
fun add(x: Int, y: Int) : Int {
    return x + y
}

fun add(x: Int,y: Int) : Int = x + y
```


- 闭包

```
{ x: Int, y: Int ->
        println("${x + y}")
}(1, 3)

// 相当于
fun get(x: Int, y: Int) {
        println("${x + y}")
    }
get(1, 3)
```

- 网络请求

```
 URL("http://www.baidu.com").readText()
```

- 构造函数

```
class User(private var name: String) {

    private var description: String? = null

    init {
        name = "Zhang Tao"
    }

    constructor(name: String, description: String) : this(name) {
        this.description = description
    }
}
```

- 修饰符

**open 修饰符**
> Kotlin 默认会为每个变量和方法添加 final 修饰符。这么做的目的是为了程序运行的性能，其实在 Java 程序中，你也应该尽可能为每个类添加final 修饰符( 见 Effective Java 第四章 17 条)。 
为每个类加了final也就是说，在 Kotlin 中默认每个类都是不可被继承的。如果你确定这个类是会被继承的，那么你需要给这个类添加 open 修饰符。

**internal 修饰符**
> 写过 Java 的同学一定知道，Java 有三种访问修饰符，public/private/protected，还有一个默认的包级别访问权限没有修饰符。
在 Kotlin 中，默认的访问权限是 public，也就是说不加访问权限修饰符的就是 public 的。而多增加了一种访问修饰符叫 internal。它是模块级别的访问权限。
何为模块(module)，我们称被一起编译的一系列 Kotlin 文件为一个模块。在 IDEA 中可以很明确的看到一个 module 就是一个模块，当跨 module 的时候就无法访问另一个module 的 internal 变量或方法。

- 嵌套函数

```
fun function() {
    val str = "hello!"

    fun say(count: Int = 10) {
        println(str)
        if (count > 0) {
            say(count - 1)
        }
    }
    say()
}
```


### Android 实践

- 替代 findViewById

```
import kotlinx.android.synthetic.main.activity_main.*

class HelloWorld : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = "1234"
        textView.setOnClickListener { Log.d("tag", "click") }
    }

}

// 类型转换
val forecastList = findViewById(R.id.forecast_list) as RecyclerView
```

- 方法监听

```
button.setOnClickListener { view ->
    startDetailActivity()
}

textView.setOnClickListener {
    Log.d("tag", "click")
}

toolbar.setOnLongClickListener { 
    showContextMenu()
    true
}
```

- Java bean

```
// java 写法
public class User {
    private String name;
    private String id;

    public User(String name, String id) {
    this.name = name;
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
}

```

```
// kotlin 写法
data class User(var name: String, var id: String)
```

```
// 自定义 get & set
class Book () {
    var name: String = ""
        get() = field;
        set(value) {
            field = "set $value" ;
        }
}

```

- 线程

```
// java 写法
handler.post(new Runnable(){
	@Override
	public void run(){
		//todo	
	}
});

MainActivity.this.runOnUiThread(
	public void run(){
		//todo
	}
});

// kotlin 写法
async() {
    //do something asynchronously
    uiThread {
        //do something on UI thread
    }
}
```

- 延迟加载

```
private val aTextView: TextView by lazy{
        findViewById(R.id.a_textview) as TextView
}
```

- 扩展方法

```
fun AppCompatActivity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun ImageView.loadUrl(url: String) {  
    Picasso.with(context).load(url).into(this)
}

inline fun <reified T> T.debug(log: Any){
    Log.d(T::class.simpleName, log.toString())
}
```

- Kotlin 转义

```
// Java 方法名是 kotlin 关键字 in,is,data 等
fun `is`() {
   
}

```

### anko 实践
> Anko是JetBrains开发的一个强大的库。它主要的目的是用来替代以前XML的方式来使用代码生成UI布局。anko-common 库也包含了很多实用的库 https://github.com/Kotlin/anko

- 生成布局

```
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            padding = dip(30)
            editText {
                hint = "Name"
                textSize = 24f
            }
            editText {
                hint = "Password"
                textSize = 24f
            }
            button("Login") {
                textSize = 26f
            }
            button("alert") {
                setOnClickListener {
                    alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
                        yesButton { toast("Oh…") }
                        noButton {}
                    }.show()
                }
            }
            button("selector") {
                setOnClickListener {
                    val countries = listOf("Russia", "USA", "Japan", "Australia")
                    selector("Where are you from?", countries) { dialogInterface, i ->
                        toast("So you're living in ${countries[i]}, right?")
                    }
                }
            }
        }
    }
}

```
![](http://upload-images.jianshu.io/upload_images/811230-c255de2891a11688.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


将布局封装成类

```
class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        MyActivityUI().setContentView(this)
    }
}

class MyActivityUI : AnkoComponent<MyActivity> {
    override fun createView(ui: AnkoContext<MyActivity>) = with(ui) {
        verticalLayout {
            val name = editText()
            button("Say Hello") {
                onClick { ctx.toast("Hello, ${name.text}!") }
            }
        }
    }
}
```


```
// 自定义 View 的 dialog
alert {
    customView {
        editText()
    }
}.show()

// 启动 Activity
startActivity(intentFor<SomeOtherActivity>("id" to 5).singleTop())

// view 统一处理 applyRecursively
verticalLayout {
    editText {
        hint = "Name"
    }
    editText {
        hint = "Password"
    }
}.applyRecursively { view -> when(view) {
    is EditText -> view.textSize = 20f
}}
```

操作 SQLite

```
database.use {
    createTable("Customer", true, 
        "id" to INTEGER + PRIMARY_KEY + UNIQUE,
        "name" to TEXT,
        "photo" to BLOB)
}
```


### 其他
- 查看编译后bytecode 
![](http://upload-images.jianshu.io/upload_images/811230-ddf20025f2780be4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

或者`kotlinc `编译

```
kotlinc xx.kt
```

### 参考文档
- [https://www.gitbook.com/book/huanglizhuo/kotlin-in-chinese/details](https://www.gitbook.com/book/huanglizhuo/kotlin-in-chinese/details)
- [https://wangjiegulu.gitbooks.io/kotlin-for-android-developers-zh](https://wangjiegulu.gitbooks.io/kotlin-for-android-developers-zh)
- [https://mp.weixin.qq.com/s/0kE-u6jH7BbgkRWEgHfRQg](https://mp.weixin.qq.com/s/0kE-u6jH7BbgkRWEgHfRQg)

