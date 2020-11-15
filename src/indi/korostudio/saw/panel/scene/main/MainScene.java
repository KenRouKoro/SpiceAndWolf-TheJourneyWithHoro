package indi.korostudio.saw.panel.scene.main;

import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.panel.component.ImagePanel;
import indi.korostudio.saw.panel.scene.Scene;
import indi.korostudio.saw.system.image.ImageBase;
import indi.korostudio.saw.tool.Tool;
import indi.korostudio.saw.tool.TweenTool;
import indi.korostudio.saw.tween.TweenImplements;
import indi.korostudio.saw.tween.TweenListener;
import indi.korostudio.saw.tween.TweenSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainScene extends Scene {
    protected BufferedImage mainBackground,rightMenu,buttonBack,title;
    protected ImagePanel mainBackgroundPanel,mainMenuPanel;
    protected TweenSystem inTw,outTw,menuIn;
    protected MainScene mainScene=this;
    protected Color mainMenuBackground = new Color(114,83,52,50);

    public MainScene(){
        load();
    }

    private void newThis(){
        setAlpha(0f);
        setSize(Data.mainDimension);
    }

    private void newPanels(){
        mainBackgroundPanel=new ImagePanel(mainBackground = ImageBase.get("main-0"));
        mainBackgroundPanel.setSize(Data.mainDimension);

        mainMenuPanel=new ImagePanel(Tool.fillRect((int)(getWidth()*0.2),getHeight(),mainMenuBackground));
        mainMenuPanel.setBounds((int)(Data.mainDimension.getWidth()*1),0,(int)(Data.mainDimension.getWidth()*0.2), (int)Data.mainDimension.getHeight());

    }

    private void addPanels(){
        add(mainMenuPanel);
        add(mainBackgroundPanel);
    }

    private void newTweens(){
        inTw = TweenTool.SimpleTween(this,4f,TweenImplements.ALPHA,1f).addTweenListener(new TweenListener() {
            @Override
            public void start() {

            }

            @Override
            public void finish() {
                doInInto();
            }

            @Override
            public void pause() {

            }

            @Override
            public void stop() {

            }
        });

        outTw = TweenTool.SimpleTween(this,4f,TweenImplements.ALPHA,0f).addTweenListener(new TweenListener() {

            @Override
            public void start() {

            }

            @Override
            public void finish() {
                doNextScene();
            }

            @Override
            public void pause() {

            }

            @Override
            public void stop() {

            }
        });

        menuIn = TweenTool.SimpleTween(mainMenuPanel,2f,TweenImplements.X,(float) Data.mainDimension.getWidth()*0.8f);
    }

    protected void doInInto(){
        menuIn.start();
    }

    @Override
    public void load() {
        newThis();
        newPanels();
        newTweens();
        addPanels();
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
