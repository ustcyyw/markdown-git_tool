# -*- coding: utf-8 -*-
# @Time    : 2020/4/18 0018 上午 11:56
# @Author  : yyw@ustc
# @E-mail  : yang0@mail.ustc.edu.cn
# @Github  : https://github.com/ustcyyw
# @desc    :

# 用于隐藏命令行窗口
hide = '@echo off\n' \
       'if "%1"=="h" goto begin\n' \
       'start mshta vbscript:createobject("wscript.shell").run("""%~nx0"" h",0)(window.close)&&exit\n' \
       ':begin\n'

# 改为jar包的完整文件名
filename = "E:\study\github\markdown_tool-1.0-SNAPSHOT.jar"

with open("md_tool_cmd.bat", "w") as f:
    f.write(hide)
    f.write("java -jar " + filename)

