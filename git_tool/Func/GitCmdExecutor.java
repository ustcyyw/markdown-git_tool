package FuncClass;

import java.io.*;

/**
 * @Time : 2020年5月13日22:04:35
 * @Author : yyw@ustc
 * @E-mail : yang0@mail.ustc.edu.cn
 * @Github : https://github.com/ustcyyw
 * @desc :
 */
public class GitCmdExecutor {
    private String cmd;

    /**
     * 生成要执行的cmd命令：包括 add commit push
     *
     * @param repoName 要进行提交的仓库的名称
     * @param fileName （注意不要有不同路径下的同名文件）文件名称 不含路径但是含有文件类型 e.g text.txt, readme.md
     * @param commit   git commit的内容
     */
    public GitCmdExecutor(String repoName, String fileName, String commit) {
        cmd = "cmd /c " + Setting.getRepPath().substring(0, 2) + " && " +
                String.format(Setting.getGoPath(), repoName) +
                " && " +
                String.format(Setting.getAdd(), fileName) +
                " && " +
                String.format(Setting.getCommit(), commit) +
                " && " +
                Setting.getPush();
    }

    /**
     * 生成要执行的cmd命令：仅进行push，这是在push失败之后仅进行push的命令行
     *
     * @param repoName 要进行提交的仓库的名称
     */
    public GitCmdExecutor(String repoName) {
        cmd = "cmd /c " + Setting.getRepPath().substring(0, 2) + " && " +
                String.format(Setting.getGoPath(), repoName) +
                " && " +
                Setting.getPush();
    }

    /**
     * 执行 cmd命令
     *
     * @return 如果指定的文件已经提交过/没有可以提交的文件 返回"nothing to commit, working tree clean"
     * 如果有异常发生，返回的是异常信息
     * 如果提交成功，返回的是"create ......."
     * 如果是没有commit的情况下，执行push会返回空字符串 ""
     */
    public String exec() {
        String res = ""; // 存放命令行执行结果的最后一行 或者异常信息
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream(), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                res = line;
            }
            br.close();
            proc.waitFor(); // 等待命令行执行完成，然后返回主线程
        } catch (Exception e) {
            res = "error";
        }
        return res;
    }
}
