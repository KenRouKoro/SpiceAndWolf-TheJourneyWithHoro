package indi.korostudio.saw.panel.scene.main;

import indi.korostudio.saw.panel.component.ImagePanel;
import indi.korostudio.saw.panel.scene.Scene;
import indi.korostudio.saw.tool.Tool;

import java.awt.image.BufferedImage;

public class MainScene extends Scene {
    protected BufferedImage mainBackground,rightMenu,buttonBack,title;
    protected ImagePanel mainBackgroundPanel;
    @Override
    public void load() {
        mainBackgroundPanel=new ImagePanel(mainBackground = Tool.loadImage("/image/main/MainBackground.jpg"));
        

    }

    @Override
    public void in() {

    }

    @Override
    public void out() {

    }
}
