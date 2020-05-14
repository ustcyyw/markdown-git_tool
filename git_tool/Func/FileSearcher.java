package FuncClass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Time : 2020年5月13日20:39:32
 * @Author : yyw@ustc
 * @E-mail : yang0@mail.ustc.edu.cn
 * @Github : https://github.com/ustcyyw
 * @desc : 用于得到仓库目录下的所有仓库名
 */
public class FileSearcher {
    /**
     * 用于得到仓库目录下的所有仓库名列表
     * @return List<String> 仓库名列表
     */
    public static List<String> getRepositories() {
        File reposFolder = new File(Setting.getRepPath());
        List<String> repositories = new ArrayList<>();
        // 可能的仓库名中肯定不含.
        File[] files = reposFolder.listFiles((dir, name) -> !name.contains("."));
        for (File file : files) {
            // 仓库中一定有.git文件
            if (file.listFiles((dir, name) -> name.equals(".git")).length == 1)
                repositories.add(file.getName());
        }
        return repositories;
    }

    /**
     *
     * @param name 不含路径的文件名，文件名包括后缀 e.g text.txt, readme.md
     * @return 补全了从仓库目录开始的带相对路径的文件名
     */
    private static String res;
    public static String completeFileName(String repoName, String name){
        File reposFolder = new File(Setting.getRepPath() + "\\" + repoName);
        res = "";
        backTrack(new ArrayList<>(), reposFolder, name);
        return res;
    }

    private static void backTrack(List<String> path,File curFile, String target){
        if(!res.equals("") || curFile.getName().equals(".git"))
            return;
        if(target.equals(curFile.getName())){
            for(int i = 0; i < path.size() - 1; i++)
                res += path.get(i) + "/";
            res += target;
            return;
        }
        if(!curFile.isDirectory())
            return;
        for(File file : curFile.listFiles()){
            path.add(file.getName());
            backTrack(path, file, target);
            path.remove(path.size() - 1);
        }
    }
}
