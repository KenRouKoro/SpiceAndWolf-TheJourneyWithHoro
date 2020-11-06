package indi.korostudio.saw.tween;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class TweenActuator {
    final static public int Synchronization=0;
    final static public int Sequential=1;
    protected TweenActuator tweenActuator= this;
    protected int mode=Sequential;
    protected CopyOnWriteArrayList<TweenSystem> tweenSystems=new CopyOnWriteArrayList<>();
    protected int nowTween=0;
    protected boolean loop=false;
    protected CopyOnWriteArrayList<TweenListener>tweenListeners=new CopyOnWriteArrayList<>();

    public void addTweenListener(TweenListener... tweenListeners){
        this.tweenListeners.addAll(Arrays.asList(tweenListeners));
    }

    public void removeTweenListener(TweenListener... tweenListeners){
        this.tweenListeners.removeAll(Arrays.asList(tweenListeners));
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean isLoop() {
        return loop;
    }

    public void add(TweenSystem... tweenSystems){
        this.tweenSystems.addAll(Arrays.asList(tweenSystems));
    }
    public void remove(TweenSystem... tweenSystems){
        this.tweenSystems.removeAll(Arrays.asList(tweenSystems));
    }
    public void setMode(int mode){
        this.mode=mode;
    }

    public int getMode() {
        return mode;
    }

    public CopyOnWriteArrayList<TweenSystem> getTweenSystems() {
        return tweenSystems;
    }

    public int getNowTween() {
        return nowTween;
    }

    public TweenActuator(){

    }
    protected class Lintener implements TweenListener {

        @Override
        public void start() {

        }

        @Override
        public void finish() {
            nowTween++;
            tweenSystems.get(nowTween-1).removeTweenListener(this);
            if (nowTween<=tweenSystems.size()-1)
                tweenSystems.get(nowTween).start();
            else if (loop&(mode==Sequential)) {
                tweenActuator.start();
            }
        }

        @Override
        public void pause() {

        }

        @Override
        public void stop() {

        }
    }
    public void start(){
        nowTween=0;
        if (mode==Synchronization){
            for (TweenSystem tweenSystem:tweenSystems){

                tweenSystem.start();
            }
        }else {
            for (TweenSystem tweenSystem:tweenSystems){
                tweenSystem.addTweenListener(new Lintener());
            }
            tweenSystems.get(nowTween).start();
        }
    }

    public void stop(){
        loop=false;
        if (mode==Synchronization){
            for (TweenSystem tweenSystem:tweenSystems){
                tweenSystem.stop();
            }
        }else {
            tweenSystems.get(nowTween).stop();
        }

    }

    public void removeALL(){
        tweenSystems.removeAll(tweenSystems);
    }


}
