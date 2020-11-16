package indi.korostudio.saw.panel.scene.main;

import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.panel.component.ImageButton;
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
    protected ImageButton testStarButton;
    protected TweenSystem inTw,outTw,menuIn;
    protected MainScene mainScene=this;
    protected Color mainMenuBackground = new Color(139,126,102,150);

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

        mainMenuPanel=new ImagePanel(Tool.fillRect((int)(getWidth()*0.15),getHeight(),mainMenuBackground));
        mainMenuPanel.setBounds((int)(Data.mainDimension.getWidth()*1),0,(int)(Data.mainDimension.getWidth()*0.15), (int)Data.mainDimension.getHeight());

        testStarButton=new ImageButton(Tool.fillRect((int)(getWidth()*0.125),(int)(getWidth()*0.125),new Color(189,176,152)),Tool.fillRect((int)(getWidth()*0.125),(int)(getWidth()*0.125),new Color(120,106,79)),Tool.fillRect((int)(getWidth()*0.125),(int)(getWidth()*0.125),new Color(139,126,102)));
        testStarButton.setBounds((int)(getWidth()*0.0125),(int)((getHeight()-getWidth()*0.125*4)/5),(int)(getWidth()*0.125),(int)(getWidth()*0.125));
    }

    private void addPanels(){
        add(mainMenuPanel);
        add(mainBackgroundPanel);
        mainMenuPanel.add(testStarButton);
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

        menuIn = TweenTool.SimpleTween(mainMenuPanel,2f,TweenImplements.X,(float) Data.mainDimension.getWidth()*0.85f);
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
