package indi.korostudio.saw.system.cmd;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class CMD {
    static public boolean run=true;
    protected volatile static CMD cmd=new CMD();
    protected LinkedBlockingQueue<String> cmds = new LinkedBlockingQueue<String>();
    protected CMDAnalysis cmdAnalysis;
    protected Thread cmdThread;
    protected Thread cCMD;
    protected Scanner scan ;

    private CMD(){
        cmdThread=new Thread(new RunningTh());
        cmdAnalysis=new CMDAnalysis();
        cCMD=new Thread(new Runnable() {
            @Override
            public void run() {
                scan = new Scanner(System.in);
                scan.useDelimiter("\n");
                while (true){
                    String str=scan.next();
                    getCMD().run(str);
                }
            }
        });
        cmdThread.start();
        cCMD.start();
    }

    public static CMD getCMD(){
        return cmd;
    }

    public void run(String cmd){
        try {
            cmds.put(cmd);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class RunningTh implements Runnable{
        @Override
        public void run() {
            while (run){
                try {
                    cmdAnalysis.run(cmds.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
