### <center>插件化整理</center>

此demo整理插件化中动态替换及静态代理两种方案，四大组件都有涉及。

1. [动态替换](https://github.com/clickListenerData/PluginTest/tree/master/pluginreplace)
<br/>
跳转activity时本应该hook AMS 或者 修改startActivity的实现，为了简便 直接再跳转时intent传值做处理，没有编写hook AMS的代码。只是在创建activity和service时 对 ActivityThread的H 以及 Instrumentation 做了替换修改。
<br/>
加载插件apk的类，代码里有两种写法
 + 替换classLoader (参考PluginManager类)
 + dex 合并  (参考DexMergeHelper类)
2. [静态代理](https://github.com/clickListenerData/PluginTest/tree/master/pluginproxy)
<br/>
插件apk里对四大组件的处理是直接继承公共库里基本组件或接口，没有真正继承四大组件。
<br/>
真实项目里应该继承四大组件，在编译的时候 通过gradle 的 transform替换父类Activity等的名字，如此可以做到 插件可以是一个单独完整的apk。

3. 资源处理
<br/>
通过反射创建AssetManager，构建属于插件的Resources加载插件资源
<br/>
demo未实现部分：
可以通过反射修改系统的Resources值，此时需要考虑资源id冲突问题,解决方案：
+ 修改aapt命令里的packageid值，生成新的aapt替换原系统下的aapt，做到插件id与宿主id不一样（参考ZeusPlulgin）
+ hook Android打包流程，修改arsc以及R文件生成的ID值（参考gradle-small）