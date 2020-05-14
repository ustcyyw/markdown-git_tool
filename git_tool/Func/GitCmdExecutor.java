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
    public GitCmdExecutor(String repoName, String addContent, String commit) {
        cmd = createGitCmd(repoName, addContent, commit);
    }

    private String createGitCmd(String repoName, String addContent, String commit){
        StringBuilder cmd = new StringBuilder("cmd /c " + Setting.getRepPath().substring(0, 2));
        cmd.append(" && ");
        cmd.append(String.format(Setting.getGoPath(), repoName));
        cmd.append(" && ");
        cmd.append(String.format(Setting.getAdd(), addContent));
        cmd.append(" && ");
        cmd.append(String.format(Setting.getCommit(), commit));
        cmd.append(" && ");
        cmd.append(Setting.getPush());
        return cmd.toString();
    }

    public String exec(){
        String res = ""; // 存放命令行执行结果的最后一行 或者异常信息
        BufferedReader br = null;
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            br = new BufferedReader(new InputStreamReader(proc.getInputStream(), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                res = line;
            }
            proc.waitFor(); // 等待命令行执行完成，然后返回主线程
        } catch (Exception e){
            res = e.getMessage();
        } finally {
            try {
                if(br != null)
                    br.close();
            } catch (IOException e){

            }
            return res;
        }
    }

    public static void main(String[] args) throws Exception {
        GitCmdExecutor bt = new GitCmdExecutor("markdown-git_tool", "GitCmdExecutor.java",
                "git命令执行类 2020年5月14日11:33:45");
        System.out.println(bt.exec());
    }
}
