package indi.korostudio.saw.system.image;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.system.json.JSONActuator;
import indi.korostudio.saw.tool.Tool;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ImageLoader {
    static public void firstLoad(){
        loadJSONImage(Data.getRes("/image/启动画面/map.json"));
    }
    static public void lastLoad(){
        loadJSONImage(Data.getRes("/image/Logo/map.json"));
        loadJSONImage(Data.getRes("/image/main/map.json"));
    }
    static public void loadJSONImage(URI uri){
        File file=Paths.get(uri).toFile();
        if (file.isDirectory()){
            for(File file1:file.listFiles()){
                if (!(file1.getName().substring(file1.getName().lastIndexOf(".") + 1)).equalsIgnoreCase("json"))continue;
                loadJSONImage(file1.toURI());
            }
        }else {
            String json = "{}";
            try {
                json= FileUtils.readFileToString(file,"utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArrayList<JSONImageFile> imageFiles= JSONActuator.getJSONImageFile(json);
            for (JSONImageFile jsonImageFile:imageFiles){
                ImageBase.put(Tool.loadImage(new File(file.getParent()+"/"+jsonImageFile.getFile())),jsonImageFile.getName());
            }
        }
    }
    static public void removeJSONImage(URI uri){
        File file=Paths.get(uri).toFile();
        String json = "{}";
        try {
            json= FileUtils.readFileToString(file,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray=JSONArray.parseArray(json);
        for (Object obj:jsonArray.toArray()){
            JSONObject jsonObject=(JSONObject)obj;
            ImageBase.remove(jsonObject.getString("name"));
        }
    }
}
