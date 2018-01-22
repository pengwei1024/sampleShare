## Robust 方案描述
[Android热更新方案Robust](https://tech.meituan.com/android_robust.html)
![](https://tech.meituan.com/img/android_robust/patching.png)

在编译时给每段代码添加判断是否优先从dex加载

##### 优点
- 高兼容性
- 实时生效

##### 缺点
- 会添加大量代码，对运行效率、方法数、包体积产生影响