package indi.korostudio.saw.system.json;

import com.alibaba.fastjson.JSONObject;
import com.sun.jndi.toolkit.url.UrlUtil;
import indi.korostudio.saw.data.Data;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsonActuator {
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

}
