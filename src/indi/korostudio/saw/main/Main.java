package indi.korostudio.saw.main;

import indi.korostudio.saw.system.GameSystem;


public class Main {
    public static void main(String arg0[]){


        //BufferedImage bufferedImage= Tool.loadImage("/image/Logo/科洛工作室.png");
        GameSystem gameSystem=new GameSystem();
        gameSystem.load();


        /*
        JFrame jFrame=new JFrame("hello world");
        jFrame.setSize(1024,720);
        //jFrame.getRootPane().setLayout(null);
        jFrame.setLayout(null);
        AComponent aComponent=new AComponent();
        aComponent.setBackground(Color.red);
        aComponent.setBounds(0,0,400,400);
        JButton jButton=new JButton("yes");
        jButton.setBounds(200,200,100,100);
        aComponent.add(jButton);
        jFrame.add(aComponent);
       // jFrame.add(jButton);
        jFrame.setVisible(true);
        jFrame.repaint();
        TweenSystem tweenSystem= TweenTool.SimpleTween(aComponent,10, TweenImplements.ALPHA,0.2f);
        tweenSystem.start();
        */

    }
}
