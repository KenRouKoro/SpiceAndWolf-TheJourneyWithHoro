package indi.korostudio.saw.system;

import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.frame.MainFrame;
import indi.korostudio.saw.panel.ScenePanel;
import indi.korostudio.saw.panel.scene.load.LoadScene;
import indi.korostudio.saw.panel.scene.logo.LogoScene;

public class GameSystem {

    protected MainFrame frame;
    protected boolean starting=false;
    //初始化
    public void load(){
        Data.mainFrame=frame=new MainFrame();
        Data.scenePanel =new ScenePanel();
        Data.loadScene =new LoadScene();
        Data.loadScene.load();
        Data.logoScene =new LogoScene();
        start();
    }

    //相当于脚本了XD
    protected void start(){
        starting=true;
        frame.setVisible(true);
        showLoading();
        Data.loadScene.end();
        Data.logoScene.start();
    }


    public void reSize(int w,int h){
        Data.w=w;
        Data.h=h;
        frame.reSize();
    }
    static public void showLoading(){
        //Data.scenePanel.add(Data.loadPanel);
        Data.loadScene.start();
    }

    public boolean isStarting() {
        return starting;
    }
}
