package window;

import FuncClass.FileSearcher;
import FuncClass.GitCmdExecutor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @Time : 2020年5月14日19:35:49
 * @Author : yyw@ustc
 * @E-mail : yang0@mail.ustc.edu.cn
 * @Github : https://github.com/ustcyyw
 * @desc :
 */
public class Window extends JFrame {
    private JTextField fileName, enterCommit;
    private JButton button;
    private JPanel panel;

    public Window() {
        super("git提交工具(by ustcyyw)");
        setSize(450, 400);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initLabel();
        initJTextField();
        initButton();
        initPanel();

        setVisible(true);
    }

    /**
     * 添加 "仓库选择"，"输入文件" 两块label
     */
    private void initLabel() {
        Font f = new Font("Dialog", Font.PLAIN, 22);
        initOneLabel("仓库选择", f, 20, 0, 100, 150);
        initOneLabel("Commit", f, 20, 125, 100, 50);
        initOneLabel("输入文件", f, 20, 200, 100, 50);
    }

    private void initOneLabel(String labelName, Font f, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelName);
        label.setFont(f);
        label.setBounds(x, y, width, height);
        getContentPane().add(label);
    }

    /**
     * 添加输入文件名的 TextField
     */
    private void initJTextField() {
        Font f = new Font("Dialog", Font.PLAIN, 20);
        enterCommit = initOneTextField("auto commit", f, 150, 125, 250, 50);
        fileName = initOneTextField("*", f, 150, 200, 250, 50);
    }

    private JTextField initOneTextField(String defaultText, Font f, int x, int y, int width, int height) {
        JTextField textField = new JTextField(defaultText);
        textField.setFont(f);
        textField.setBounds(x, y, width, height);
        getContentPane().add(textField);
        return textField;
    }

    /**
     * 添加进行仓库选取的 单选模块
     */
    private void initPanel() {
        panel = new JPanel();

        List<String> repos = FileSearcher.getRepositories();
        ButtonGroup buttonGroup = new ButtonGroup();
        for (String repo : repos) {
            JRadioButton temp = new JRadioButton(repo);
            panel.add(temp);
            buttonGroup.add(temp);
        }

        // 向window中加入选择仓库的模块panel
        panel.setLayout(new FlowLayout());
        panel.setBounds(100, 20, 330, 150);
        getContentPane().add(panel);
    }

    private void initButton() {
        button = new JButton("确定");
        button.setFont(new Font("Dialog", Font.PLAIN, 22));
        button.setBounds(175, 290, 100, 50);
        getContentPane().add(button);
        button.addActionListener((event) -> {
            try {
                // 仓库选取
                String repoName = "";
                for (Component c : panel.getComponents()) {
                    JRadioButton cb = (JRadioButton) c;
                    if (cb.isSelected()) {
                        repoName = cb.getText();
                    }
                }
                if (repoName.equals("")) {
                    JOptionPane.showMessageDialog(this,
                            "请选择仓库", "Error 笨ju", 0);
                    throw new Exception("error");
                }

                // 文件名补全
                String fileName = FileSearcher.completeFileName(repoName, this.fileName.getText());
                if (fileName.equals("")) {
                    JOptionPane.showMessageDialog(this,
                            "仓库不存在该文件", "Error 笨ju", 0);
                    throw new Exception("error");
                }

                // 读取commit的内容
                String commit = enterCommit.getText();
                commit = commit.equals("") ? "auto commit" : commit;

                // 执行 add commit push命令
                GitCmdExecutor exec = new GitCmdExecutor(repoName, fileName, commit);
                String res = exec.exec();
//                System.out.println(res);
                if (res.equals("nothing to commit, working tree clean")) {
                    JOptionPane.showMessageDialog(this,
                            "指定文件已提交", "别交啦emmm", 2);
                } else if (res.equals("error")) {
                    JOptionPane.showMessageDialog(this,
                            "提交过程出现异常，建议打开git bash查看是否有其他情况", "重新提交sigh", 0);
                    throw new Exception("error");
                } else
                    JOptionPane.showMessageDialog(this,
                            "指定文件提交成功", "bingo (^.^)", 1);
            } catch (Exception e) {
                //不做任何处理
            }
        });
    }
}
