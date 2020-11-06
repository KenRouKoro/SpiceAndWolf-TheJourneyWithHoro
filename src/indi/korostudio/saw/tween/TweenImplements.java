package indi.korostudio.saw.tween;

import aurelienribon.tweenengine.TweenAccessor;
import indi.korostudio.saw.panel.AComponent;
import indi.korostudio.saw.panel.AlphaPanel;

import java.awt.*;

public class TweenImplements implements TweenAccessor<Component> {
    public static final int X = 1;
    public static final int Y = 2;
    public static final int XY = 3;
    public static final int W = 4;
    public static final int H = 5;
    public static final int WH = 6;
    public static final int XYWH = 7;
    public static final int ALPHA = 8;

    @Override

    public int getValues(Component target, int tweenType, float[] returnValues) {
        /**
         *对对象的不同属性提供设置值和获取值的方法
         *target是要改变属性的对象
         *tweenType自己定义的是改变属性的类型
         *returnValues是属性值的数组，返回值是这个数组的长度
         */

        switch (tweenType) {
            case X:
                returnValues[0] = target.getX();
                return 1;
            case Y:
                returnValues[0] = target.getY();
                return 1;
            case XY:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                return 2;
            case W:
                returnValues[0] = target.getWidth();
                return 1;
            case H:
                returnValues[0] = target.getHeight();
                return 1;
            case WH:
                returnValues[0] = target.getWidth();
                returnValues[1] = target.getHeight();
                return 2;
            case XYWH:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                returnValues[2] = target.getWidth();
                returnValues[3] = target.getHeight();
                return 4;
            case ALPHA:
                AlphaPanel alphaPanel =(AlphaPanel) target;
                returnValues[0] = alphaPanel.getAlpha();
                return 1;
            default: return -1;
        }
    }



    @Override
    public void setValues(Component target, int tweenType, float[] newValues) {
        System.out.println(newValues[0]);
        switch (tweenType) {
            case X:
                target.setLocation(Math.round(newValues[0]), target.getY());
                break;
            case Y:
                target.setLocation(target.getX(), Math.round(newValues[0]));
                break;
            case XY:
                target.setLocation(Math.round(newValues[0]), Math.round(newValues[1]));
                break;
            case W:
                target.setSize(Math.round(newValues[0]), target.getHeight());
                target.validate();
                break;
            case H:
                target.setSize(target.getWidth(), Math.round(newValues[0]));
                target.validate();
                break;
            case WH:
                target.setSize(Math.round(newValues[0]), Math.round(newValues[1]));
                target.validate();
                break;
            case XYWH:
                target.setBounds(Math.round(newValues[0]), Math.round(newValues[1]), Math.round(newValues[2]), Math.round(newValues[3]));
                target.validate();
                break;
            case ALPHA:
                AlphaPanel alphaPanel =(AlphaPanel) target;
                alphaPanel.setAlpha(newValues[0]);
                break;
            default:
                break;
        }

    }

}
