- [Android so库防客户端破解的解决方案](https://juejin.im/post/5c4082b16fb9a049fa100ab8?utm_source=gold_browser_extension)


**注意下gradle设置宏cFlags和cppFlags区别**
```
arguments += ["-DANDROID_TOOLCHAIN=clang", "-DANDROID_NATIVE_API_LEVEL=16", "-DANDROID_PLATFORM=android-16", "-DANDROID_CPP_FEATURES=rtti exceptions", "-DANDROID_STL=c++_static"]
cFlags += ["-fsigned-char"]
cppFlags += ["-fsigned-char"]
```

### 方法签名
```
在GetMethodID中第四个参数()V就是方法签名，Java是支持重载的，所以需要标明方法的传参和返回值，这就是方法的签名。它是用来保证方法的唯一性。其中()代表不传参数，V代表返回值为void。方法签名对于Java的类型都有一一对应的值。方法签名中用大写的字母对应了java的基本数据类型:

Z -> boolean
B -> byte
C -> char
S -> short
I -> int
J -> long
F -> float
D -> double

其实就是有两个比较特殊的：boolean对应的是Z,long对应的J，其他的都是首个字母的大写即可。
数组的表示方法，以[为标志，一个[标识一维数组，[[表示二维数组,例如:

byte[] -> [B
int[][] -> [[I

引用类型的表示方法，需要以L开头，以;结束，中间对应类型的包名加类名，例如:

String -> Ljava/lang/String;
Object -> Ljava/lang/Object;
```