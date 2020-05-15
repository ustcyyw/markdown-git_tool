package FuncClass;

/**
 * @Time : 2020年5月13日20:42:05
 * @Author : yyw@ustc
 * @E-mail : yang0@mail.ustc.edu.cn
 * @Github : https://github.com/ustcyyw
 * @desc :
 */
public class Setting {
    private static String RepPath = "E:\\study\\github";

    private static String goPath = "cd " + RepPath + "\\%s";
    private static String add = "git add %s";
    private static String commit = "git commit -m \"%s\"";
    private static String push = "git push";

    public static String getGoPath() {
        return goPath;
    }

    public static String getRepPath() {
        return RepPath;
    }

    public static String getAdd() {
        return add;
    }

    public static String getCommit() {
        return commit;
    }

    public static String getPush() {
        return push;
    }
}
