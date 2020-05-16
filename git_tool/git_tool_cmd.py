# -*- coding: utf-8 -*-
# @Time    : 2020/5/16 0016 上午 11:57
# @Author  : yyw@ustc
# @E-mail  : yang0@mail.ustc.edu.cn
# @Github  : https://github.com/ustcyyw
# @desc    :

# 用于隐藏命令行窗口
hide = '@echo off\n' \
       'if "%1"=="h" goto begin\n' \
       'start mshta vbscript:createobject("wscript.shell").run("""%~nx0"" h",0)(window.close)&&exit\n' \
       ':begin\n'

filename = "E:\study\github\git_kit-1.0.jar"

with open("git_tool_cmd.bat", "w") as f:
    f.write(hide)
    f.write("java -jar " + filename)