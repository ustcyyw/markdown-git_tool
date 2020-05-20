### markdown_tool思路

**md的固定格式**：

* 支持md语法的软件/网页会将固定格式的字符串渲染成对应的格式。
    * 所以我们就可以利用这一点，查看格式对应的字符串，然后将你需要的格式以字符串的形式写入md文件即可。
    * 我使用typora这个软件的源代码模式来看格式与字符串的对应关系。
* `StringSetting.java`中进行了多种格式对应字符串的设置。
    * 比如“两种解法”这一个标题格式对应的字符串为`private static String mulSolve = "\n### %d种解法"`，这里%d是为了填入具体几种解法
    * 在这个程序中还要设置文件的编码，比如我需要写入中文，所以我的编码设置为`String encoding = "GB2312";`，之后写入文件的时候需要用到。
    * 并且还要指定生成的md文件放置的目录`String path = "C:\\Users\\Administrator\\Desktop\\";`
    * 如果需要写入什么其他内容，就自定义一些字符串进去就好。
* `MdWriter.java`，将`StringSetting.java`中设置好的字符串，按照你的格式逻辑依次写入文件即可。
    * 使用类`PrintWriter writer`
    * 字符串的写入等等就按照你想生成的格式依次写入即可。在`StringSetting.java`设置字符串时已经添加了换行符，这里就不需要进行了。

**小程序制作**：

* GUI设计，java学这个属实很傻，所以就不详细说：

    * 把你需要的组件对象添加到你的窗口，为你的组件添加动作监听。
    * 注意异常处理，就是处理不合法输入，然后弹窗提示修改。
    * 小程序入口`APPEnter.java`，涉及GUI的固定写法。

* .bat文件的设置，`md_tool_cmd.py`：

    * ```
        @echo off
        if "%1"=="h" goto begin
        start mshta vbscript:createobject("wscript.shell").run("""%~nx0"" h",0)(window.close)&&exit
        :begin
        用于在启动程序时不显示命令行窗口
        ```

    * `java -jar E:\study\github\md_kit-1.0.jar`命令行执行java jar，文件名写绝对路径。

* 异常处理：防止非法输入，一个简单的类`LittleException`

    * 继承`Exception`，重写`getMessage`方法，在错误信息后面添加上”请修改输入“
    * 处理三个异常：均在点击确定按钮生成`MdWriter`实例创建文件时处理。
        * 文件名（路径）的错误
        * 输入非整形
        * 编码冲突时的错误