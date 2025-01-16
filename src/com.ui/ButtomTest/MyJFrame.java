import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MyJFrame extends JFrame{

    JButton jtb = new JButton("click me");

    public MyJFrame(){
        //设置界面大小
        this.setSize(603,680);

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

        //给按钮添加动作监听：ActionListener（用鼠标左键/空格触发）
        //jtb: 组件对象，表示那你要给那个组件添加事件
        //参数: 事件被执行后要触发的代码

        //方法一：调用class
            //jtb.addActionListener(new MyActionListener()); 
        //方法二：匿名内部类
        jtb.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    System.out.println("click");
                }
            }
        );
        //方法三：见MyJFrame_Key和MyJFrame_Mouse

        //键盘监听：KeyListener分为按下键和松开键 ->快捷键
            //如果按下一个键不松，会反复调用keyPressed

        //鼠标监听：MouseListener分为单击、按下不松、松开、划入、划出几个分类

        this.getContentPane().add(jtb);

        this.setVisible(true);
    }
}