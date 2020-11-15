package indi.korostudio.saw.system.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.system.image.JSONImageFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class JSONActuator {
    public static void SettingLoad(String json){
        JSONObject object = JSONObject.parseObject(json);
        int w,h,fps;
        boolean fullScreen;
        String title,icon;
        w=object.getInteger("width");
        h=object.getInteger("height");
        fps=object.getInteger("fps");
        fullScreen=object.getBoolean("fullScreen");
        title=object.getString("title");
        icon=object.getString("icon");
        Data.resize(w,h);
        Data.fps=fps;
        Data.title=title;
        Data.fullScreen=fullScreen;
        Data.iconFile=icon;
    }
    public static void SettingLoad(URL url){
        String json="";
        try {
            json=FileUtils.readFileToString(new File(url.getFile()),"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SettingLoad(json);
    }
    public static String getSetting(){
        int w= Data.w,h=Data.h,fps=Data.fps;
        boolean fullScreen=Data.fullScreen;
        String title=Data.title,icon=Data.iconFile;
        JSONObject object = new JSONObject();
        object.put("width",w);
        object.put("height",h);
        object.put("fullScreen",fullScreen);
        object.put("fps",fps);
        object.put("title",title);
        object.put("icon",icon);
        return object.toJSONString();
    }

    public static ArrayList <JSONImageFile> getJSONImageFile(String json){
        ArrayList <JSONImageFile> arrayList=new ArrayList<>();
        JSONArray jsonArray=JSONArray.parseArray(json);
        for(Object obj:jsonArray.toArray()){
            arrayList.add(JSON.parseObject(((JSONObject)obj).toJSONString(),JSONImageFile.class));
        }
        return arrayList;
    }

}
