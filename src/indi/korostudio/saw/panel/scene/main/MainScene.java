package indi.korostudio.saw.panel.scene.main;

import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.panel.component.ImagePanel;
import indi.korostudio.saw.panel.scene.Scene;
import indi.korostudio.saw.tool.Tool;
import indi.korostudio.saw.tool.TweenTool;
import indi.korostudio.saw.tween.TweenImplements;
import indi.korostudio.saw.tween.TweenListener;
import indi.korostudio.saw.tween.TweenSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainScene extends Scene {
    protected BufferedImage mainBackground,rightMenu,buttonBack,title;
    protected ImagePanel mainBackgroundPanel;
    protected TweenSystem inTw,outTw;
    protected TweenListener outIm;
    protected MainScene mainScene=this;

    public MainScene(){
        load();
    }

    @Override
    public void load() {
        setAlpha(0f);
        setSize(Data.mainDimension);
        mainBackgroundPanel=new ImagePanel(mainBackground = Tool.loadImage("/image/main/MainBackground.jpg"));
        mainBackgroundPanel.setSize(Data.mainDimension);
        System.out.println(mainBackgroundPanel);
        add(mainBackgroundPanel);

        inTw = TweenTool.SimpleTween(this,4f,TweenImplements.ALPHA,1f);

        outTw = TweenTool.SimpleTween(this,4f,TweenImplements.ALPHA,0f).addTweenListener(outIm = new TweenListener() {

            @Override
            public void start() {

            }

            @Override
            public void finish() {
                Data.scenePanel.remove(mainScene);
            }

            @Override
            public void pause() {

            }

            @Override
            public void stop() {

            }
        });


    }

    @Override
    public void in() {
        try {
            Data.scenePanel.remove(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.setAlpha(0f);
        Data.scenePanel.add(this);
        this.setVisible(true);
        inTw.start();
    }

    @Override
    public void out() {
        outTw.start();
    }
}
