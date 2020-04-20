package window;

import write.LittleException;
import write.MdWriter;

import javax.swing.*;
import java.awt.*;

/**
 * @Time : 2020年4月17日15:34:11
 * @Author : yyw@ustc
 * @E-mail : yang0@mail.ustc.edu.cn
 * @Github : https://github.com/ustcyyw
 * @desc :
 */
public class Window extends JFrame {
    private JTextField nameField, countField, fileNameField;
    private JButton button;

    public Window() {
        super("生产格式化md文件(by ustcyyw)");
        setSize(450, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initLabel();
        initField();
        initButton();
        setVisible(true);
    }

    private void initLabel() {
        Font f = new Font("Dialog", Font.PLAIN, 22);
        initOneLabel("题目名称", f, 50, 25, 150, 50);
        initOneLabel("解法数目", f, 50, 100, 150, 50);
        initOneLabel("文件名称", f, 50, 175, 150, 50);
    }

    private void initOneLabel(String labelName, Font f, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelName);
        label.setFont(f);
        label.setBounds(x, y, width, height);
        getContentPane().add(label);
    }

    private void initField() {
        Font f = new Font("Dialog", Font.PLAIN, 22);
        nameField = initOneField("我的题解", f, 225, 25, 150, 50);
        countField = initOneField("1", f, 225, 100, 150, 50);
        fileNameField = initOneField("solve", f, 225, 175, 150, 50);
    }

    private JTextField initOneField(String defaultText, Font f, int x, int y, int width, int height) {
        JTextField field = new JTextField(defaultText);
        field.setFont(f);
        field.setBounds(x, y, width, height);
        getContentPane().add(field);
        return field;
    }

    private void initButton() {
        button = new JButton("确定");
        button.setFont(new Font("Dialog", Font.PLAIN, 22));
        button.setBounds(175, 275, 100, 50);
        getContentPane().add(button);
        button.addActionListener((event) -> {
            try{
                String fileStr = fileNameField.getText();
                String nameStr = nameField.getText();
                try{
                    int c = Integer.parseInt(countField.getText());
                    MdWriter writer = new MdWriter(fileStr + ".md");
                    writer.write(nameStr, c);
                } catch (NumberFormatException e){
                    throw new LittleException("数字输入格式错误");
                }
            } catch (LittleException e){
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error 笨ju", 1);
            }
        });
    }
}
