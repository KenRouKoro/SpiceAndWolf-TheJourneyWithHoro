package indi.korostudio.saw.system;

import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.panel.scene.Scene;

import java.awt.*;

public class CMDAnalysis {
    public void run(String cmd){
        String key,todo=null;
        System.out.println("RUN:"+cmd);
        if(cmd.contains(" ")) {
            key = cmd.substring(0, cmd.indexOf(" ")).trim();
            todo = cmd.substring(cmd.indexOf(" ") + 1).trim();
        }else{
            key=cmd.trim();
        }
        switch (key){
            case "show":
                show(todo);
                break;
            case "reload":
                break;
            case "exit":
                exit();
                break;
        }
    }

    protected void show(String str){
        if (str==null){

        }else{
            Scene scene =Data.sceneMap.get(str);
            Data.scenePanel.remove(scene);
            if(Data.nowScene ==null){
                Data.scenePanel.add(scene);
                Data.nowScene=str;
                scene.in();
            }else{
                Data.sceneMap.get(Data.nowScene).setNextScene(scene);
                Data.sceneMap.get(Data.nowScene).out();
                Data.nowScene=str;
            }
        }
    }

    protected void exit(){
        System.exit(0);
    }
}
