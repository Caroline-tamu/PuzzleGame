import javax.swing.*;

public class LoginJFrame extends JFrame{
    public LoginJFrame(){
        this.setSize(480, 430);
        this.setVisible(true);
        this.setTitle("Login");
        //界面永远处于最上层（不会被其他界面覆盖）
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //关闭模式：EXIT_ON_CLOSE = 3 -> 关掉一个就全部关闭
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //或this.setDefaultCloseOperation(3);
    }

}
