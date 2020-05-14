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
public class RepositorySearcher {
    private File reposFolder;

    public RepositorySearcher() {
        reposFolder = new File(Setting.getRepPath());
    }

    /**
     * 用于得到仓库目录下的所有仓库名列表
     *
     * @return
     */
    public List<String> getRepositories() {
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
}
