package exception;

/**
 * @Time : 2020/9/1-11:13 下午
 * @Author : yyw@ustc
 * @E-mail : yang0@mail.ustc.edu.cn
 * @Github : https://github.com/ustcyyw
 * @desc :
 */
public enum ExceptionMessage {
    FILE_ERROE("路径设置或者文件名格式错误"),
    ENCODING_ERROR("编码设置有错误"),
    NUM_FORMAT_ERROR("数字输入格式错误"),
    LOAD_CONFIG_ERROR("初始化配置错误");

    public String message;

    ExceptionMessage(String message){
        this.message = message;
    }
}
