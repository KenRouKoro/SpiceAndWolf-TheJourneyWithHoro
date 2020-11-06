package indi.korostudio.saw.frame;

import indi.korostudio.saw.data.Data;


import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame {
    protected MainFrame mainFrame=this;
    protected JPanel panel=new JPanel();
    protected Thread thread;
    int fps=0;
    Timer timer = new Timer("FPS");
    public MainFrame(){
        //panel.setSize(Data.w,Data.h);
        panel.setPreferredSize(Data.mainDimension);
        panel.setLayout(null);
        panel.setOpaque(false);
        setSize(Data.w,Data.h);
        setContentPane(panel);
        pack();
        setVisible(false);
        setLayout(null);
        setResizable(false);
        setTitle(Data.title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        thread=new Thread(new Loop());
        thread.start();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mainFrame.setTitle(Data.title+"  fps:"+fps);
                fps=0;
            }
        }, 5000, 1000);
        if (Data.fullScreen) {
            setFullSceen(this);
        }
    }

    public void setFullSceen(JFrame jFrame){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //通过调用GraphicsEnvironment的getDefaultScreenDevice方法获得当前的屏幕设备了
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        // 全屏设置
        gd.setFullScreenWindow(jFrame);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        fps++;
    }

    @Override
    public Component add(Component comp) {
        return panel.add(comp);
    }

    @Override
    public Component add(Component comp, int index) {
        return panel.add(comp, index);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void reSize(){
        setSize(Data.w,Data.h);
    }
    protected class Loop implements Runnable{
        @Override
        public void run() {
            while (Data.running){
                try {
                    Thread.sleep(1000/Data.fps);
                    mainFrame.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
