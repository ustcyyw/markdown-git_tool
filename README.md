# markdown_tool
### 项目简介

🤗**项目目的**：

* 由于总结算法使用md文件时格式固定，每次都需要手动输入一些固定的格式，很麻烦。
* 在leetcode写题解（题解仓库[点击here](https://github.com/ustcyyw/yyw_algorithm)）的时候要加上固定的落款，每次也需要手动添加，很麻烦。
* 所以写一了一个简单的小项目来帮我自动添加题解的框架及落款。（依旧是兴趣驱动的小玩具）

😋**使用方式**：

* 大概就是fork这个库，然后clone到你本地，修改一些设置然后将java程序打包成jar，再自己做一个.bat文件来运行jar就行。
* 在GUI页面按要求输入参数一键生成即可（下拉看更详细的介绍）

😎**思路分享**：

* typora或者一些其它支持md格式的网页都是讲字符串渲染成固定格式，所以只需要在md文件中输入固定格式的字符串，就可以达到设定格式，添加内容的目的！
* 如果每次都需要打开ide再运行或者使用命令行传参数好像很麻烦，不如做个简单的GUI，再做一个.bat文件来启动GUI，多方便！
* 由于扩展性很差，可以看懂这个思路然后自己造属于自己的小工具来方便自己呀（下拉看更详细的介绍）

-----

### 效果展示

##### 一种解法

如果只总结一种方法，就这样去输入参数。

![1种解法正确示范.png](https://github.com/ustcyyw/markdown_tool/blob/master/picture_for_readme/1%E7%A7%8D%E8%A7%A3%E6%B3%95%E6%AD%A3%E7%A1%AE%E7%A4%BA%E8%8C%83.png?raw=true)

题目名称自选，文件名称可以写路径+文件名，不过这需要调整java文件中的配置。建议直接写文件名，不需要后缀.md。解法数目只能为整数，这里生成一种解法的题解框架，就输入。点击确定得到题解框架如下：

![1种解法生成框架.png](https://github.com/ustcyyw/markdown_tool/blob/master/picture_for_readme/1%E7%A7%8D%E8%A7%A3%E6%B3%95%E7%94%9F%E6%88%90%E6%A1%86%E6%9E%B6.png?raw=true)

然后就可以愉快的开始往里面写你的总结了。 

##### 2种或多种解法

如果总结2种或更多方法，就将题解数目填入对应的数目：

![2种及以上解法正确示范.png](https://github.com/ustcyyw/markdown_tool/blob/master/picture_for_readme/2%E7%A7%8D%E5%8F%8A%E4%BB%A5%E4%B8%8A%E8%A7%A3%E6%B3%95%E6%AD%A3%E7%A1%AE%E7%A4%BA%E8%8C%83.png?raw=true)

其余同上，点击确定得到题解框架如下：

![2种及以上解法生成框架.png](https://github.com/ustcyyw/markdown_tool/blob/master/picture_for_readme/2%E7%A7%8D%E5%8F%8A%E4%BB%A5%E4%B8%8A%E8%A7%A3%E6%B3%95%E7%94%9F%E6%88%90%E6%A1%86%E6%9E%B6.png?raw=true)

##### 两种错误示范

* 文件名称（路径错误），报错如下：将错误提示框点击确定，重新输入即可

![文件路径错误.png](https://github.com/ustcyyw/markdown_tool/blob/master/picture_for_readme/%E6%96%87%E4%BB%B6%E8%B7%AF%E5%BE%84%E9%94%99%E8%AF%AF.png?raw=true)

* 数字输入了非整形，报错如下：将错误提示框点击确定，重新输入即可

![数字输入错误.png](https://github.com/ustcyyw/markdown_tool/blob/master/picture_for_readme/%E6%95%B0%E5%AD%97%E8%BE%93%E5%85%A5%E9%94%99%E8%AF%AF.png?raw=true)

-----

### 思路分享：

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

* 为了避免每次打卡ide来运行程序，设置参数。先为其设计一个GUI，然后打包成jar文件，然后写一个.bat文件直接运行。

* GUI设计，java学这个属实很傻，所以就不详细说：

    * 把你需要的组件对象添加到你的窗口，为你的组件添加动作监听。
    * 注意异常处理，就是处理不合法输入，然后弹窗提示修改。
    * 小程序入口`APPEnter.java`，涉及GUI的固定写法。

* 打包成jar文件，如果你使用idea，并且使用maven创建项目，可以参考[我的笔记]([https://github.com/ustcyyw/nootbook/blob/master/Tool_use_guide/%E5%9C%A8IDEA%E4%B8%AD%E7%94%9F%E6%88%90Maven%E9%A1%B9%E7%9B%AE%E7%9A%84jar%E6%96%87%E4%BB%B6.md](https://github.com/ustcyyw/nootbook/blob/master/Tool_use_guide/在IDEA中生成Maven项目的jar文件.md))。其他情况就去百度/Google。

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

**本地使用**

* fork并克隆这个仓库到本地，修改文件生成路径，然后打包成jar，生成你的.bat文件
* 如果要修改的话，要动的东西就比较多了，希望这个思路可以帮到你，让你做自己的小工具。

-----

### 感谢

* 感谢免费软件 typora
* 如果觉得有用请给我一个星星吧~感谢给我 star 的小伙伴！（我在想pitch）:smile:

### 欢迎交流

* 微信Y154578009 /QQ154578009 ❤️​
* Email：yang0@mail.ustc.edu.cn

