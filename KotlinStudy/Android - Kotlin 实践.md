## Android - Kotlin 实践

### 语法糖

- 不用写分号

```
println("hello world")
```
- 三元运算符

```
// java
int code = isSuccessfully? 200: 400; 
 
// kotlin
int code = if(isSuccessfully) 200 else 400  
```


- 字符串模板表达式

```
println("index:$i, value:${args[i]}")
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


// kotlin 写法
data class User(var name: String, var id: String)
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

