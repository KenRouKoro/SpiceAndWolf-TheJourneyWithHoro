package indi.korostudio.saw.system.image;

import indi.korostudio.saw.tool.Tool;

public class ImageLoader {
    static public void firstLoad(){
        for(int i=1;i<=9;i++){
            ImageBase.put(Tool.loadImage("/image/启动画面/loading-"+i+".jpg"),"Load-"+i);
        }
    }
    static public void lastLoad(){
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
