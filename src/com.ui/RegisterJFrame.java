import javax.swing.*;

public class RegisterJFrame extends JFrame{
    public RegisterJFrame(){
        this.setSize(480, 500);
        //可视化界面
        this.setVisible(true);
        //设置标题
        this.setTitle("Register");
        //界面永远处于最上层（不会被其他界面覆盖）
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //关闭模式：EXIT_ON_CLOSE = 3 -> 关掉一个就全部关闭
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //或this.setDefaultCloseOperation(3);
    }
}
