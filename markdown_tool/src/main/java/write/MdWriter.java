package write;

import exception.ExceptionMessage;
import exception.LittleException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @Time : 2020年4月17日14:04:48
 * @Author : yyw@ustc
 * @E-mail : yang0@mail.ustc.edu.cn
 * @Github : https://github.com/ustcyyw
 * @desc : 按照制定格式创建md文件
 */
public class MdWriter {
    private PrintWriter writer = null;

    public MdWriter(String filename) throws LittleException {
        try {
            System.out.println(StringSetting.getPath() + filename);
            writer = new PrintWriter(StringSetting.getPath() + filename, StringSetting.getEncoding());
        } catch (FileNotFoundException e) {
            throw new LittleException(ExceptionMessage.FILE_ERROE.message);
        } catch (UnsupportedEncodingException e) {
            throw new LittleException(ExceptionMessage.ENCODING_ERROR.message);
        }
    }

    public void write(String name, int n) {
        writer.print(String.format(StringSetting.getArticle(), name));
        writer.print(StringSetting.getArticle2());
        writer.print(StringSetting.getAdvertise1());
        if (n == 1) {
            writer.print(StringSetting.getSolve());
            createSolve();
        } else {
            writer.print(String.format(StringSetting.getMulSolve(), n));
            for (int i = 1; i <= n; i++) {
                writer.print(String.format(StringSetting.getSolveIndex(), i));
                createSolve();
            }
        }
        writer.print(StringSetting.getAdvertise2());
        writer.close();
    }

    private void createSolve() {
        writer.print(StringSetting.getInsertCode());
        writer.print(StringSetting.getThinking());
        writer.print(StringSetting.getPoint());
        writer.print(StringSetting.getResult());
    }
}
