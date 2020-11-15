package indi.korostudio.saw.system.cmd;

import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.panel.scene.Scene;
import indi.korostudio.saw.system.image.ImageBase;
import indi.korostudio.saw.system.image.ImageLoader;
import indi.korostudio.saw.system.json.JSONActuator;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CMDAnalysis {
    public void run(String cmd){
        String key,todo=null;
        System.out.println("RUNCMD:"+cmd);
        if(cmd.contains(" ")) {
            key = cmd.substring(0, cmd.indexOf(" ")).trim();
            todo = cmd.substring(cmd.indexOf(" ") + 1).trim();
        }else{
            key=cmd.trim();
        }
        key=key.toLowerCase();
        switch (key){
            case "show":
                show(todo);
                break;
            case "reload":
                reload(todo);
                break;
            case "exit":
                exit();
                break;
            case "reimage":
                reImage();
                break;
            case "loadimage":
                loadImage();
                break;
            case "nullscene":
                nullScene();
                break;
            case "saveset":
                saveset();
                break;
        }
    }

    protected void saveset(){
        try {
            FileUtils.write(new File("setting.json"), JSONActuator.getSetting(),"UTF-8",false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void nullScene(){
        Scene scene = Data.sceneMap.get(Data.nowScene);
        Data.scenePanel.remove(scene);
        Data.nowScene=null;
        scene.setVisible(false);
    }

    protected void reImage(){
        ImageBase.removeALL();
        loadImage();//********
    };

    protected void loadImage(){
        ImageLoader.firstLoad();
        run("show Load");
        ImageLoader.lastLoad();
    }

    protected void reload(String str){
        if (str==null|!Data.sceneMap.containsKey(str)){

        }else{
            Scene scene =Data.sceneMap.get(str);
            scene.load();
        }
    }

    protected void show(String str){
        if (str==null|!Data.sceneMap.containsKey(str)){

        }else{
            Scene scene =Data.sceneMap.get(str);
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
