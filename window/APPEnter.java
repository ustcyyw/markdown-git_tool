package window;

import javax.swing.*;

/**
 * @Time :
 * @Author : yyw@ustc
 * @E-mail : yang0@mail.ustc.edu.cn
 * @Github : https://github.com/ustcyyw
 * @desc :
 */
public class APPEnter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            new Window();
        });
    }
}
