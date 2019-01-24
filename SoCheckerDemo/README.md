- [Android so库防客户端破解的解决方案](https://juejin.im/post/5c4082b16fb9a049fa100ab8?utm_source=gold_browser_extension)


**注意下gradle设置宏cFlags和cppFlags区别**
```
arguments += ["-DANDROID_TOOLCHAIN=clang", "-DANDROID_NATIVE_API_LEVEL=16", "-DANDROID_PLATFORM=android-16", "-DANDROID_CPP_FEATURES=rtti exceptions", "-DANDROID_STL=c++_static"]
cFlags += ["-fsigned-char"]
cppFlags += ["-fsigned-char"]
```