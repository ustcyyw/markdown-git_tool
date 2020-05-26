# markdown_tool & Git_tool
## 项目简介

* 总结leetcode题解时使用的md文件格式固定，且要带上固定的落款（一些小广告），并且要将题解放到自己的github仓库（题解仓库[点击here](https://github.com/ustcyyw/yyw_algorithm)）。
* 固定格式框架，提交到github的“add commit push”三步曲都是重复性劳动，不如用个工具解决麻烦吧（依旧是兴趣驱动的小玩具）。

* markdown_tool用于一键生成题解框架；git_tool用于一键将文件提交到github。

-----

## 效果展示

### markdown_tool：

* 可以生成仅有一种解法的题解框架。
    * <img src="https://github.com/ustcyyw/markdown-git_tool/blob/master/picture/md_tool%E4%B8%80%E7%A7%8D%E8%A7%A3%E6%B3%95.png?raw=true" alt="md_tool一种解法.png" style="zoom:80%;" />
    * GUI界面是在作图的时候变瘦了，真实的比例还不错。
    * 题目名称填入Leetcode算法题的名称；文件名称的输入不需要后缀.md；生成文件存放的路径可以自己设置（查看markdown_tool目录下分项目的readme文件有详细介绍）
* 可以生成2种或者多种解法的题解框架。
    * <img src="https://github.com/ustcyyw/markdown-git_tool/blob/master/picture/md_tool%E4%B8%A4%E7%A7%8D%E8%A7%A3%E6%B3%95.png?raw=true" alt="md_tool两种解法.png" style="zoom:80%;" />
    * 在“解法数目”处填入解法数目。

### git_tool：

* 提交仓库中所有新文件。
    * <img src="https://github.com/ustcyyw/markdown-git_tool/blob/master/picture/git_tool%E5%B1%95%E7%A4%BA1.png?raw=true" alt="git_tool展示1.png" style="zoom:80%;" />
    * 选择仓库，填写commit的内容，文件名处填入“*”后点击确定。
* 提交仓库中的指定文件。
    * <img src="https://github.com/ustcyyw/markdown-git_tool/blob/master/picture/git_tool%E5%B1%95%E7%A4%BA2.png?raw=true" alt="git_tool展示2.png" style="zoom:80%;" />
    * 选择仓库，填写commit的内容，文件名处填入指定的文件名（不能省略文件后缀），后点击确定。
    * 这里要确保仓库中没有重名的文件（哪怕他们的相对路径并不同），此小bug是将来可以补齐的地方。

## 错误示范及改正方式

### markdown_tool：

* <img src="https://github.com/ustcyyw/markdown-git_tool/blob/master/picture/md_tool%E9%94%99%E8%AF%AF.png?raw=true" alt="md_tool错误.png" style="zoom:80%;" />
    * （a）这种错误就是解法数目输入错误，只能输入大于0的整数。
    * （b）这种错误就是文件名中出现非法字符，不能输入非法的文件名字符，要修改路径可以修改`StringSetting.java`中的路径设置。

### git_tool：

* <img src="https://github.com/ustcyyw/markdown-git_tool/blob/master/picture/%E9%94%99%E8%AF%AF%E6%88%96%E8%80%85%E8%AD%A6%E5%91%8A.png?raw=true" alt="错误或者警告.png" style="zoom:80%;" />
    * 第一个错误：使用前先单选选定一个要提交文件的仓库。
    * 第二个错误：仓库中的文件名一定要写对，且别忘记后缀，如果不是需要单独提交某个文件，可以使用默认添加所有文件"*"。
    * 警告：选择了仓库且制定文件确实存在，但是已经不需要提交了，就会发出“文件已提交”的警告。
    * 并且注意，这个小工具只针对 add commit push的三连操作，其它git的命令不能使用它。

## 本地使用

* 首先fork这个库（并且给个❤️star），然后clone到本地，修改配置类中的配置。然后根据项目结构图搭建项目框架，然后将java程序打包成jar，再自己做一个.bat文件来运行jar就行。
* 我使用的maven搭建项目，如果你也是这样，可以将pom文件也放置到合适位置，不需要修改pom文件（除非你改了主类名字），然后利用maven的打包命令就可以生成jar。如果你使用JB家的IDEA，并且使用maven创建项目，可以参考[我的笔记](https://github.com/ustcyyw/nootbook/blob/master/Tool_use_guide/在IDEA中生成Maven项目的jar文件.md)。其他情况就去百度/Google。
* 在GUI页面按要求输入参数一键生成即可（下拉看更详细的介绍）

## 思路分享：

* markdown_tool：
    * typora或者一些其它支持md格式的网页都是将字符串渲染成固定格式。
    * 所以只需要在md文件中输入固定格式的字符串，就可以达到设定格式，添加内容的目的！
* git_tool:
    * 就是将add commit push三个命令行在程序中自动执行。
    * 需要添加commit的内容，需要知道往哪一个仓库添加什么文件，这就需要进行文件相关的检索。
* 如果每次都需要打开ide再运行或者使用命令行传参数好像很麻烦，不如做个简单的GUI，再做一个.bat文件来启动GUI，多方便！
* 由于扩展性较差，下面两个链接是对markdown_tool, git_tool的一些实现细节的介绍，可以看懂这个思路然后自己造属于自己的小工具来方便自己呀：
    * [markdown_tool思路](https://github.com/ustcyyw/markdown-git_tool/blob/master/markdown_tool/markdown_tool思路.md)
    * [git_tool思路](https://github.com/ustcyyw/markdown-git_tool/blob/master/git_tool/git_tool%20%E6%80%9D%E8%B7%AF.md)

### 欢迎交流

* 如果觉得有用请给我一个星星吧~感谢给我 star 的小伙伴！可以fork去自己使用呀。
* 如果有什么问题可以私信：
    * 微信Y154578009 /QQ154578009 ❤️​
    * Email：yang0@mail.ustc.edu.cn

