package indi.korostudio.saw.system;

import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.frame.MainFrame;
import indi.korostudio.saw.panel.ScenePanel;
import indi.korostudio.saw.panel.scene.load.LoadScene;
import indi.korostudio.saw.panel.scene.logo.LogoScene;
import indi.korostudio.saw.panel.scene.main.MainScene;

public class GameStartup {

    protected MainFrame frame;
    protected boolean starting=false;
    static public String now="";
    protected Thread runThread;
    //初始化
    public void load(){
        Data.mainFrame=frame=new MainFrame();
        Data.scenePanel =new ScenePanel();
        Data.sceneMap.put("Load",new LoadScene());
        Data.sceneMap.get("Load").load();
        start();
    }
    public void afterLoad(){
        Data.sceneMap.put("Main",new MainScene());
        Data.sceneMap.put("Logo",new LogoScene());
    }

    //相当于脚本了XD
    protected void start(){
        runThread=new Thread(new Runnable() {
            @Override
            public void run() {
                starting=true;
                frame.setVisible(true);
                CMD.getCMD().run("show Load");
                afterLoad();
                CMD.getCMD().run("show Logo");
            }
        });
        runThread.start();
    }


    public void reSize(int w,int h){
        Data.w=w;
        Data.h=h;
        frame.reSize();
    }

    public boolean isStarting() {
        return starting;
    }
}
