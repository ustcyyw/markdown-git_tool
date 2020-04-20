package write;

/**
 * @Time :
 * @Author : yyw@ustc
 * @E-mail : yang0@mail.ustc.edu.cn
 * @Github : https://github.com/ustcyyw
 * @desc :
 */
public class LittleException extends Exception {
    public LittleException(String msg){
        super(msg);
    }

    @Override
    public String getMessage(){
        return super.getMessage() + ",请修改输入";
    }
}
