package indi.korostudio.saw.panel.scene.logo;

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
import java.util.ArrayList;

public class LogoScene extends Scene {
    protected ArrayList<BufferedImage>images=new ArrayList<>();
    protected TweenSystem in,out;
    protected TweenActuator inout;
    protected LogoScene logoScene=this;

    public LogoScene(){
        load();
    }

    @Override
    public void in() {
        in = TweenTool.SimpleTween(this,5f,TweenImplements.ALPHA,1f);

        out = TweenTool.SimpleTween(this,5f,TweenImplements.ALPHA,0f).addTweenListener(new TweenListener() {
            @Override
            public void start() {

            }

            @Override
            public void finish() {
                Data.scenePanel.remove(logoScene);
                out();
            }

            @Override
            public void pause() {

            }

            @Override
            public void stop() {

            }
        });;

        inout = TweenTool.SimpleActuator(in,out);
        try {
            Data.scenePanel.remove(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setAlpha(0f);
        Data.scenePanel.add(this);
        this.setVisible(true);

        inout.start();
    }

    @Override
    public void out() {
        Data.sceneMap.get("Main").in();
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



}
