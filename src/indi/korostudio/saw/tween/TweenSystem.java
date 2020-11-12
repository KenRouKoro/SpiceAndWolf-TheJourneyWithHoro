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
    protected TweenManager tweenManager;
    protected CopyOnWriteArrayList<TweenListener>tweenListeners=new CopyOnWriteArrayList<>();
    protected TweenEquation tweenMode= Cubic.INOUT;
    protected Component component;
    protected Thread runThread;
    protected boolean again=false;
    protected float time=2f;
    protected int mode=TweenImplements.XY;
    protected boolean running = false;
    protected float []arg;
    protected Runnable runnable ;

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
                        ex.printStackTrace();
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

    public TweenSystem setArg(float... arg){

        this.arg=arg;
        return this;
    }

    public TweenSystem setTime(float time) {
        this.time = time;
        return  this;
    }

    public boolean isRunning() {
        return running;
    }

    public TweenSystem start(){
        load();
        for (TweenListener tweenListener:tweenListeners){
            tweenListener.start();
        }
        runThread.start();
        return this;
    }
    public TweenSystem setTweenMode(TweenEquation mode){
        this.tweenMode=mode;
        return this;
    }

    public TweenSystem setComponent(Component component){
        this.component=component;
        return this;
    }

    public TweenSystem setMode(int mode){
        this.mode=mode;
        return this;
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

    public TweenSystem addTweenListener(TweenListener... tweenListeners){
        this.tweenListeners.addAll(Arrays.asList(tweenListeners));
        return this;
    }

    public TweenSystem removeTweenListener(TweenListener... tweenListeners){
        this.tweenListeners.removeAll(Arrays.asList(tweenListeners));
        return this;
    }

    protected void load (){
        try {
            Tween.registerAccessor(Component.class, new TweenImplements());
            tweenManager = new TweenManager();
            Tween.to(component, mode, time).ease(Cubic.INOUT).target(arg).start(tweenManager);
            runThread = new Thread(new RunThread());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
