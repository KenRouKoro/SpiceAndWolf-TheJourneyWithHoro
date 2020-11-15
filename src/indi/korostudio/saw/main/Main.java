package indi.korostudio.saw.main;

import indi.korostudio.saw.system.main.GameStartup;
import indi.korostudio.saw.system.cmd.CMD;
import indi.korostudio.saw.system.json.JSONActuator;

import java.io.File;
import java.net.MalformedURLException;


public class Main {
    public static void main(String arg0[]){
        try {
            JSONActuator.SettingLoad(new File("setting.json").toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        GameStartup gameStartup =new GameStartup();
        gameStartup.load();
        CMD.run("saveset");
    }
}
