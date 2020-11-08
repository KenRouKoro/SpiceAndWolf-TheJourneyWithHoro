package indi.korostudio.saw.tool;

import indi.korostudio.saw.tween.TweenActuator;
import indi.korostudio.saw.tween.TweenSystem;

import java.awt.*;

public class TweenTool {
    final static public TweenSystem SimpleTween(Component component, float time, int mode, float...arg){
        TweenSystem tweenSystem=new TweenSystem();
        tweenSystem.setComponent(component);
        tweenSystem.setMode(mode);
        tweenSystem.setArg(arg);
        tweenSystem.setTime(time);
        return tweenSystem;
    }
    final static public TweenSystem SimpleTween(Component component,int mode,float...arg){
        TweenSystem tweenSystem=new TweenSystem();
        tweenSystem.setComponent(component);
        tweenSystem.setMode(mode);
        tweenSystem.setArg(arg);
        tweenSystem.setTime(1000);
        return tweenSystem;
    }
    final static public TweenActuator SimpleActuator(int mode, TweenSystem...tweenSystems){
        TweenActuator tweenActuator=new TweenActuator();
        tweenActuator.setMode(mode);
        tweenActuator.add(tweenSystems);
        return  tweenActuator;
    }
    final static public TweenActuator SimpleActuator( TweenSystem...tweenSystems){
        TweenActuator tweenActuator=new TweenActuator();
        tweenActuator.add(tweenSystems);
        return  tweenActuator;
    }
}
