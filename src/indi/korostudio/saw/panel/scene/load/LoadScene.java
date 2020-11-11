package indi.korostudio.saw.panel.scene.load;


import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.panel.scene.Scene;
import indi.korostudio.saw.tool.Tool;
import indi.korostudio.saw.tool.TweenTool;
import indi.korostudio.saw.tween.TweenActuator;
import indi.korostudio.saw.tween.TweenImplements;
import indi.korostudio.saw.tween.TweenListener;
import indi.korostudio.saw.tween.TweenSystem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoadScene extends Scene {
    protected TweenSystem in,out,lastout;
    protected TweenActuator inout;
    protected CopyOnWriteArrayList<BufferedImage> images=new CopyOnWriteArrayList<>();
    protected int nowImage=1;
    protected Random r = new Random();
    protected Font font;
    protected BufferedImage strImage;
    protected LoadScene loadScene=this;

    @Override
    public void in() {
        setAlpha(0f);
        in = TweenTool.SimpleTween(this,4f, TweenImplements.ALPHA,1f);
        out = TweenTool.SimpleTween(this,4f, TweenImplements.ALPHA,0f);
        lastout = TweenTool.SimpleTween(this,4f,TweenImplements.ALPHA,0f);
        inout = TweenTool.SimpleActuator(in,out);
        inout.setLoop(true);
        reImage();
        Data.scenePanel.add(this);
        this.setVisible(true);
        lastout.addTweenListener(new TweenListener() {
            @Override
            public void start() {

            }

            @Override
            public void finish() {
                setVisible(false);
                setAlpha(1f);
                Data.scenePanel.remove(loadScene);
                loadScene.doNextScene();
            }

            @Override
            public void pause() {

            }

            @Override
            public void stop() {

            }
        });
        inout.start();
    }

    @Override
    public void out() {
        inout.stop();
        lastout.start();
    }

    public void load(){
        this.setLayout(null);
        //this.setBackground(Color.white);
        this.setSize(Data.mainDimension);
        this.setAlpha(0f);
        for(int i=1;i<=9;i++){
            images.add(Tool.loadImage("/image/启动画面/loading-"+i+".jpg"));
        }
        font=new Font("Times New Roman",Font.BOLD,40);
        strImage=Tool.stringImage(Color.GRAY,font,"loading......");
    }

    public void reImage(){
        nowImage=r.nextInt(8)+1;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.drawImage(images.get(nowImage),getWidth()-350,getHeight()-150,null);
        g2d.setFont(font);
        //g2d.drawString("Loading...",100,100);
        g2d.drawImage(strImage,getWidth()-200,getHeight()-50,null);
    }
}
