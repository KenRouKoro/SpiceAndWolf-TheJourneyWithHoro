package indi.korostudio.saw.main;

import indi.korostudio.saw.system.GameStartup;
import indi.korostudio.saw.system.cmd.CMD;
import indi.korostudio.saw.system.json.JsonActuator;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Main {
    public static void main(String arg0[]){
        try {
            JsonActuator.SettingLoad(new File("setting.json").toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        GameStartup gameStartup =new GameStartup();
        gameStartup.load();
        CMD.run("saveset");
    }
}
