package indi.korostudio.saw.main;

import indi.korostudio.saw.system.GameStartup;
import indi.korostudio.saw.system.json.JsonActuator;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String arg0[]){
        GameStartup gameStartup =new GameStartup();
        gameStartup.load();
        System.out.println(JsonActuator.getSetting());
        try {
            FileUtils.write(new File("setting.json"), JsonActuator.getSetting(),"UTF-8",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
