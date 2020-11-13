package indi.korostudio.saw.system.image;

import indi.korostudio.saw.tool.Tool;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;

public class ImageBase {
    private ImageBase(){}
    protected ConcurrentHashMap <String,BufferedImage>imageMap=new ConcurrentHashMap<>();
    protected static ImageBase imageBase = new ImageBase();

    public static void removeALL(){
        imageBase.removeImageMap();
    }

    protected void removeImageMap(){
        imageMap = new ConcurrentHashMap<>();
    }

    public static ImageBase getImageBase() {
        return imageBase;
    }

    public ConcurrentHashMap<String, BufferedImage> getImageMap() {
        return imageMap;
    }

    public static BufferedImage put(URI uri, String name){
        BufferedImage bufferedImage= Tool.loadImage(uri);
        imageBase.putImage(bufferedImage,name);
        return bufferedImage;
    }
    public static BufferedImage put(String file,String name){
        BufferedImage bufferedImage = Tool.loadImage(file);
        imageBase.putImage(bufferedImage,name);
        return bufferedImage;
    }

    public static BufferedImage put(BufferedImage bufferedImage,String name){
        imageBase.putImage(bufferedImage,name);
        return bufferedImage;
    }

    public void putImage(BufferedImage image,String name){
        imageMap.put(name,image);
    }

    public static BufferedImage get(String name){
        return imageBase.getImage(name);
    }

    public BufferedImage getImage(String name){
        return imageMap.get(name);
    }

    public static void remove(String name){
        imageBase.removeImage(name);
    }

    public void removeImage(String name){
        imageMap.remove(name);
    }
}
