### git_tool 思路

**关于git**

* 由于本工具使用`Process proc = Runtime.getRuntime().exec(cmd);`执行git命令，所以需要配置git的环境，将其设置成全局变量。
* git仓库都有一个`.git`文件，只有在有这个文件的目录下才能使用git命令

**Func中的类**

* `Setting.java`进行一些固定的字符串设置：
    * `RepPath`指定仓库所在的目录，根据你自己的情况进行设置。
    * `goPath = "cd " + RepPath + "\\%s"`，由于执行命令时需要进入仓库的目录，所以这个实际是执行进入指定仓库目录的命令行。
    * `add, commit, push`则分别对应git的三个命令，其中`commit`需要嵌入输入的内容，`add`需要嵌入文件名。
* `FileSearcher.java`进行文件操作的工具类：
    * 第一个功能，要从仓库的目录提取出所有仓库的名字供你选择。`public static List<String> getRepositories()`，这里主要是对`File`类的API进行运用。首先要保证是一个文件夹而不是文件`reposFolder.listFiles((dir, name) -> !name.contains("."));`，其次要保证文件夹下有`.git`文件`file.listFiles((dir, name) -> name.equals(".git")).length == 1`
    * 第二个功能能，要从指定的仓库找到指定的文件，并且补全相对路径（因为git add的时候需要相对路径）`public static String completeFileName(String repoName, String name)`。这里使用了回溯算法。细节看代码，注意这里需要保证仓库中没有重名文件。
* `GitCmdExecutor.java`执行cmd命令：
    * 首先生成实例的时候就生成根据参数确定的cmd命令对应的字符串。
    * 然后采用`Process proc = Runtime.getRuntime().exec(cmd);`执行命令行，并将执行结果的字符串返回，用来判断执行是否完成，或者出现异常/错误等情况。

**GUI设计/小工具制作**

* `APPEnter.java`是程序入口。固定写法。

* `Window.java`，实现GUI功能，用java写GUI不多说，并不是最好选择，这里说几个重点的思路：

    * 要给用户提供可选仓库，就需要使用工具类`FileSearcher.java`的第一个功能产生仓库名字符串List，然后通过java中的单选框组件`ButtonGroup, JRadioButton`来完成单选功能（具体看代码）
    * 然后输入文件名及commit内容的输入框使用`JTextField`
    * 还有一个确定按钮，添加事件然后，事件中首先要进行读取，将单选框和文本框的内容读取，然后生成`GitCmdExecutor`的实例，执行命令。要注意错误输入的排除，以及执行异常的处理。
    * 布局之类的可以借助`JPanel`，这些直接看代码。

* .bat文件的设置，`git_tool_cmd.py`：

    * ```
        @echo off
        if "%1"=="h" goto begin
        start mshta vbscript:createobject("wscript.shell").run("""%~nx0"" h",0)(window.close)&&exit
        :begin
        用于在启动程序时不显示命令行窗口
        ```

    * `java -jar E:\study\github\git_kit-1.0.jar`命令行执行java jar，文件名写绝对路径。