package indi.korostudio.saw.panel.scene.logo;

import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.panel.AComponent;
import indi.korostudio.saw.panel.scene.Scene;
import indi.korostudio.saw.tool.Tool;
import indi.korostudio.saw.tool.TweenTool;
import indi.korostudio.saw.tween.TweenActuator;
import indi.korostudio.saw.tween.TweenImplements;
import indi.korostudio.saw.tween.TweenListener;
import indi.korostudio.saw.tween.TweenSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class LogoScene extends Scene {
    protected ArrayList<BufferedImage>images=new ArrayList<>();
    protected TweenSystem in,out;
    protected TweenActuator inout;
    protected LogoScene logoScene=this;

    @Override
    public void in() {

    }

    @Override
    public void out() {

    }

    public void load(){
        setSize(Data.mainDimension);
        setVisible(false);
        setAlpha(0f);
        this.setLayout(null);
            //images.add(ImageIO.read(Data.getRes("image/Logo/科洛工作室.png")));
        images.add(Tool.loadImage("/image/Logo/科洛工作室.png"));
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d=(Graphics2D)g;
        g2d.drawImage(images.get(0),(getWidth()-getHeight())/2,0,getHeight(),getHeight(),null);
    }
    public void start(){
        load();
        in = TweenTool.SimpleTween(this,5,TweenImplements.ALPHA,1f);
        out = TweenTool.SimpleTween(this,5,TweenImplements.ALPHA,0f);
        inout = TweenTool.SimpleActuator(in,out);
        try {
            Data.scenePanel.remove(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setAlpha(0f);
        Data.scenePanel.add(this);
        this.setVisible(true);
        out.addTweenListener(new TweenListener() {
            @Override
            public void start() {

            }

            @Override
            public void finish() {
            Data.scenePanel.remove(logoScene);
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


}
