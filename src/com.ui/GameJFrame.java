import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class GameJFrame extends JFrame implements KeyListener, ActionListener{

    //记录当前图片的排序
    private final int[][] imageArray = new int[4][4];

    //记录1图片正确排序
    private final int[][] imageCorrectArray = new int[][] {{1, 5, 9, 13},{2, 6, 10, 14},{3, 7, 11, 15},{4, 8, 12, 16}};

    //记录空白的位置
    private int x;
    private int y;

    //记录步数
    private int stepCount;

    //图片路径
    private final String imagePath = "/Users/seven/Desktop/Java Project/4.阶段项目/puzzleGame/";

    //创建每个选项下面的次选项
    private final JMenuItem startAgainItem = new JMenuItem("Start Again");
    private final JMenuItem logOutItem = new JMenuItem("Log Out");
    private final JMenuItem closeItem = new JMenuItem("Quit");
    private final JMenuItem accountItem = new JMenuItem("My OC");
    

    public GameJFrame(){
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenu();

        //初始化图片
        initImage();

        //可视化界面（写在最后）
        this.setVisible(true);

    }

    private void initJFrame(){
        //设置界面大小
        this.setSize(604,680);

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

        //给整个界面添加KeyListener
        this.addKeyListener(this);
    }

    private void initJMenu(){
        //创建菜单栏
        JMenuBar GameJMenuBar = new JMenuBar();

        //创建菜单选项
        JMenu settingJMenu = new JMenu("Settings");
        JMenu aboutJMenu = new JMenu("About Us");
        
        //把次选项加入选项中
        settingJMenu.add(startAgainItem);
        settingJMenu.add(logOutItem);
        settingJMenu.add(closeItem);
        
        aboutJMenu.add(accountItem);

        startAgainItem.addActionListener(this);
        logOutItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
    
        //把菜单选项加入菜单栏
        GameJMenuBar.add(settingJMenu);
        GameJMenuBar.add(aboutJMenu);
        
        //把菜单栏加入界面
        this.setJMenuBar(GameJMenuBar);

    }

    private void initSingleImage(String path, int x, int y, int w, int h, boolean Border){
        //创建一个图片ImageIcon的对象
        ImageIcon puzzelImageIcon = new ImageIcon(imagePath + path +".png");

        //创建一个JLable的对象（管理容器）
        JLabel JImageLabel = new JLabel(puzzelImageIcon);
        
        //指定图片位置
        JImageLabel.setBounds(x, y, w, h);

        //给图片添加边框
        //0：凸起 = BevelBorder.RAISED
        //1：凹下 = BevelBorder.LOWERED
        if(Border) JImageLabel.setBorder(new BevelBorder(BevelBorder.RAISED));

        //把管理容器添加进界面
        //this.add(JImageLabel1);
        this.getContentPane().add(JImageLabel);
    }

    private void initImage(){
        Random rd = new Random();
        ArrayList<Integer> array = new ArrayList<>();
        while(array.size() != 16){
            int index = rd.nextInt(1, 17);
            if(array.contains(index)){
                continue;
            }
            array.add(index);
        }
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.imageArray[i][j] = array.get(index);
                index ++;
            }
        }
        addImage();
    }

    private void addImage(){
        this.getContentPane().removeAll();
        if(finishGame(imageArray, imageCorrectArray)){
            initSingleImage("background/win", 410, 400, 150, 150, false);
        }
        
        JLabel stepCountLabel = new JLabel("Step Count: " + this.stepCount);
        stepCountLabel.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCountLabel);
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                initSingleImage("image/"+this.imageArray[i][j], 95 + i*106, 120 + j*106, 106, 106, true);
                if(this.imageArray[i][j] == 16){
                    this.x = i;
                    this.y = j;
                }
            }
        }
        //加入背景图片
        initSingleImage("background/theme", 55, 25, 508, 578, false);
        
        //刷新界面
        this.getContentPane().repaint();
    }

    private void MoveImage(int x, int y){
        int X = this.x + x;
        int Y = this.y + y;
        if(X > 3 || Y > 3 || X < 0 || Y < 0) return;
        this.stepCount ++;
        this.imageArray[this.x][this.y] = this.imageArray[X][Y];
        this.imageArray[X][Y] = 16;
        addImage();
    }

    private void showImage(){
        this.getContentPane().removeAll();
        initSingleImage("image/0", 85, 120, 424, 424, false);
        initSingleImage("background/theme", 45, 25, 508, 578, false);
        this.getContentPane().repaint();
    }

    private boolean finishGame(int[][] arr1, int[][] arr2){
        for (int i = 0; i < arr1.length; i++) {
            if(!Arrays.equals(arr1[i], arr2[i])) return false;
        }
        return true;
    }

    private void imageCopy(int[][] arr1, int[][] arr2){
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = Arrays.copyOf(arr2[i], arr2[i].length);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //空格32: 查看完整图片
        if(key == 32){
            showImage();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //↑38 ↓40 ←37 →39
        //1:49  刷新
        //w:87  速通
        if(finishGame(imageArray, imageCorrectArray)){
            return;
        }
        int key = e.getKeyCode();
        System.out.println(key);
        switch(key){
            case 32:
                addImage();
                break;
            case 37:
                MoveImage(1, 0);
                break;
            case 38:
                MoveImage(0, 1);
                break;
            case 39:
                MoveImage(-1, 0);
                break;
            case 40:
                MoveImage(0, -1);
                break;
            case 87:
                imageCopy(imageArray, imageCorrectArray);
                addImage();
        }
    }

    public JMenuItem getStartAgainItem() {
        return startAgainItem;
    }

    public JMenuItem getLogOutItem() {
        return logOutItem;
    }

    public JMenuItem getCloseItem() {
        return closeItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == startAgainItem){
            this.stepCount = 0;
            initImage();
        }
        else if(obj == logOutItem){
            //隐藏当前界面
            this.setVisible(false);
            //创建登录界面
            new LoginJFrame();
        }
        else if(obj == closeItem){
            //关闭虚拟机
            System.exit(0);
        }
        else if(obj == accountItem){
            //创建弹窗对象
            JDialog JDialog = new JDialog();
            //图片容器
            JLabel jLabel = new JLabel(new ImageIcon(this.imagePath + "image/oc.png"));
            //设置图片大小和位置
            jLabel.setBounds(0, 0, 300, 258);
            //将图片加入弹窗
            JDialog.getContentPane().add(jLabel);
            //设置弹窗大小
            JDialog.setSize(344, 344);
            //让弹窗处于最上层
            JDialog.setAlwaysOnTop(true);
            //弹窗居中
            JDialog.setLocationRelativeTo(null);
            //弹窗不关闭无法继续操作
            JDialog.setModal(true);
            //显示弹窗
            JDialog.setVisible(true);

        }
    }
    
}