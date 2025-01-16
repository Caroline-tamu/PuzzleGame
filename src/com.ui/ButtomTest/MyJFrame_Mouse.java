import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class MyJFrame_Mouse extends JFrame implements MouseListener{

    JButton jtb = new JButton("click me");

    public MyJFrame_Mouse(){
        //设置界面大小
        this.setSize(603,680);

        //设置标题
        this.setTitle("Puzzle Game");

        //界面永远处于最上层（不会被其他界面覆盖）
        this.setAlwaysOnTop(true);

        //设置界面居中
        this.setLocationRelativeTo(null);

        //关闭模式：EXIT_ON_CLOSE = 3 -> 关掉一个就全部关闭
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //或this.setDefaultCloseOperation(3);

        //取消默认的居中放置
        this.setLayout(null);

        jtb.setBounds(100, 0, 100, 100);

        jtb.addMouseListener(this);

        this.getContentPane().add(jtb);

        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("单击");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按下");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("划入");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("划出");
    }
    
}
