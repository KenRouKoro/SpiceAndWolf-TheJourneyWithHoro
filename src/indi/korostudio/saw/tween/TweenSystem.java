package indi.korostudio.saw.tween;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Cubic;
import indi.korostudio.saw.data.Data;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TweenSystem {
    TweenManager tweenManager;
    CopyOnWriteArrayList<TweenListener>tweenListeners=new CopyOnWriteArrayList<>();
    TweenEquation tweenMode= Cubic.INOUT;
    Component component;
    Thread runThread;
    protected boolean again=false;
    float time=2f;
    int mode=TweenImplements.XY;
    boolean running = false;
    float []arg;
    class RunThread implements  Runnable{
        public void run() {
            long lastMillis = System.currentTimeMillis();
            long deltaLastMillis = System.currentTimeMillis();
            running=true;
            while (running) {
                long newMillis = System.currentTimeMillis();
                long sleep = 1000/ Data.fps - (newMillis - lastMillis);
                lastMillis = newMillis;
                if (sleep > 1){
                    try {
                        Thread.sleep(sleep);
                    }
                    catch (InterruptedException ex) {
                    }
                }
                long deltaNewMillis = System.currentTimeMillis();
                final float delta = (deltaNewMillis - deltaLastMillis) / 1000f;
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {
                            tweenManager.update(delta);
                            List<BaseTween<?>> baseTweens=tweenManager.getObjects();
                            boolean canStop=true;
                            for (BaseTween baseTween:baseTweens){
                                if (!baseTween.isFinished())canStop=false;
                            }
                            if (canStop)running=false;
                        }});
                } catch (InterruptedException ex) {
                } catch (InvocationTargetException ex) {
                }
                deltaLastMillis = newMillis;
            }
            for (TweenListener tweenListener:tweenListeners){
                tweenListener.finish();
            }
        }};
    Runnable runnable ;

    public void setArg(float... arg){

        this.arg=arg;

    }

    public void setTime(float time) {
        this.time = time;
    }

    public boolean isRunning() {
        return running;
    }

    public void start(){
        load();
        for (TweenListener tweenListener:tweenListeners){
            tweenListener.start();
        }
        runThread.start();
    }
    public void setTweenMode(TweenEquation mode){
        this.tweenMode=mode;
    }
    public void setComponent(Component component){
        this.component=component;
    }
    public void setMode(int mode){
        this.mode=mode;
    }

    public void stop(){
        List<BaseTween<?>> baseTweens=tweenManager.getObjects();
        for (BaseTween baseTween:baseTweens){
            baseTween.free();
        }
        for (TweenListener tweenListener:tweenListeners){
            tweenListener.stop();
        }
    }

    public void pause(){
        List<BaseTween<?>> baseTweens=tweenManager.getObjects();
        for (BaseTween baseTween:baseTweens){
            baseTween.pause();
        }
    }

    public void addTweenListener(TweenListener... tweenListeners){
        this.tweenListeners.addAll(Arrays.asList(tweenListeners));
    }

    public void removeTweenListener(TweenListener... tweenListeners){
        this.tweenListeners.removeAll(Arrays.asList(tweenListeners));
    }


    protected void load (){
        Tween.registerAccessor(Component.class, new TweenImplements());
        tweenManager=new TweenManager();
        Tween.to(component, mode, time).ease(Cubic.INOUT).target(arg ).start(tweenManager);
        runThread=new Thread(new RunThread());
    }
}
