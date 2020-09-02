package write;

import exception.ExceptionMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Time : 2020年4月17日14:06:16
 * @Author : yyw@ustc
 * @E-mail : yang0@mail.ustc.edu.cn
 * @Github : https://github.com/ustcyyw
 * @desc :
 */

public class StringSetting {
    private final static String CONFIG_FILE = "config.txt";

    private final static String encoding = "GB2312";
    private final static String article = "# %s";
    private final static String article2 = "\n### 原题\n\n\n";
    private final static String solve = "\n### 解法";
    private final static String mulSolve = "\n### %d种解法";
    private final static String solveIndex = "\n##### %d.";
    private final static String insertCode = "\n```java\n```";
    private final static String thinking = "\n思路分析：";
    private final static String point = "\n* \n";
    private final static String result = "\n运行结果：";

//    private static String advertise1 = "\n**更多LeetCode题解请看[题解仓库](https://github.com/ustcyyw/yyw_algorithm)," +
//            "如果觉得还不错请别吝啬你的star~**";
//    private static String advertise2 = "\n\n----" +
//            "\n* 更多LeetCode题解请看[题解仓库](https://github.com/ustcyyw/yyw_algorithm)" +
//            "\n* 题解框架由小工具自动生产，参考[工具项目](https://github.com/ustcyyw/markdown_tool)" +
//            "\n* [我的github](https://github.com/ustcyyw)还有别的小项目也很好玩。卑微求个~小星星蟹蟹";

    private static String advertise1 = "\n**%s**";
    private static String advertise2 = "\n\n----";

    private static String path;

    /**
     * 从.txt文件加载广告语及md文件输出到路径
     */
    private static Map<String, String> loadSetting() {
        Map<String, String> properties = null;
        String fileName = System.getProperty("user.dir") + File.separator + CONFIG_FILE;
        try {
            Scanner in = new Scanner(new File(fileName));
            properties = new HashMap<>();
            properties.put("path", in.next().substring(5));
            properties.put("ad1", in.next().substring(4));
            properties.put("ad2", in.next().substring(4));
            return properties;
        } catch (FileNotFoundException e) {
            System.out.println(ExceptionMessage.LOAD_CONFIG_ERROR.message
                    + " : " + e.getLocalizedMessage());
            return null;
        }
    }

    public static void setSetting() {
        Map<String, String> properties = loadSetting();
        if (properties == null) {
            path = System.getProperty("user.dir") + File.separator;
            advertise1 = String.format(advertise1, "前置广告位招租");
            advertise2 = advertise2 + "\n* 后置广告位招租";
        } else {
            path = properties.get("path") + File.separator;
            advertise1 = String.format(advertise1, properties.get("ad1"));
            String lineStart = "\n* ";
            String[] lines = properties.get("ad2").split(";");
            StringBuilder temp = new StringBuilder();
            for (String line : lines) {
                temp.append(lineStart).append(line);
            }
            advertise2 = advertise2 + temp.toString();
        }
    }

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

    public static String getAdvertise1() {
        return advertise1;
    }

    public static String getAdvertise2() {
        return advertise2;
    }
}
