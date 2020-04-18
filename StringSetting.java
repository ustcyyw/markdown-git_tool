package write;

/**
 * @Time : 2020年4月17日14:06:16
 * @Author : yyw@ustc
 * @E-mail : yang0@mail.ustc.edu.cn
 * @Github : https://github.com/ustcyyw
 * @desc :
 */

class StringSetting {
    private static String encoding = "GB2312";
    private static String article = "# %s";
    private static String article2 = "\n### 原题";
    private static String solve = "\n### 解法";
    private static String mulSolve = "\n### %d种解法";
    private static String solveIndex = "\n##### %d.";
    private static String insertCode = "\n```java\n```";
    private static String thinking = "\n思路分析：";
    private static String point = "\n* \n";
    private static String result = "\n运行结果：";
    private static String ending = "\n\n----" +
            "\n* 更多LeetCode题解请看[题解仓库](https://github.com/ustcyyw/yyw_algorithm)" +
            "\n* 题解框架由小工具自动生产，参考[工具项目](123)" +
            "\n* [我的github](https://github.com/ustcyyw)还有别的小项目也很好玩。卑微求个~小星星蟹蟹";

    private static String path = "C:\\Users\\Administrator\\Desktop\\";

    public static String getEncoding() {
        return encoding;
    }

    public static String getArticle() {
        return article;
    }

    public static String getArticle2() {
        return article2;
    }

    public static String getSolve() {
        return solve;
    }

    public static String getMulSolve() {
        return mulSolve;
    }

    public static String getSolveIndex() {
        return solveIndex;
    }

    public static String getInsertCode() {
        return insertCode;
    }

    public static String getThinking() {
        return thinking;
    }

    public static String getPoint() {
        return point;
    }

    public static String getResult() {
        return result;
    }

    public static String getPath() {
        return path;
    }

    public static String getEnding() {
        return ending;
    }
}
