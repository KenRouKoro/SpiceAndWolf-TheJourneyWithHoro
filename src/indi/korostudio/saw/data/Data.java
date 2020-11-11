package indi.korostudio.saw.data;

import indi.korostudio.saw.frame.MainFrame;
import indi.korostudio.saw.panel.ScenePanel;
import indi.korostudio.saw.panel.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class Data {
    public static int w=1280,h=720;
    public static MainFrame mainFrame;
    public static String title="Spice And Wolf - The Journey With Horo";
    public static ImageIcon icon;
    public static Dimension mainDimension=new Dimension(w,h);
    public static Rectangle mainRectangle=new Rectangle(0,0,mainDimension.width,mainDimension.height);
    public static ScenePanel scenePanel;
    public static ConcurrentHashMap<String,Scene> sceneMap=new ConcurrentHashMap<String,Scene>();
    public static String nowScene =null;


    public static boolean running=true;
    public static int fps=60;
    public static boolean fullScreen=false;
    //获取res文件
    final static public URL getRes(String file){
        return Data.class.getResource("/res/"+file);
    }

    public static void resize(int w,int h){ 
        mainDimension.setSize(w,h);
        mainRectangle.setSize(mainDimension);
    }

}
