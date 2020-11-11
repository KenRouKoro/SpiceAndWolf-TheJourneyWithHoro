package indi.korostudio.saw.panel.scene;

import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.panel.AlphaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Scene extends JPanel implements AlphaPanel {

    public static final float Alpha=1f;

    protected float alpha=1f;

    Scene nextScene=null;

    public  Scene(){
        setOpaque(false);
    }
    abstract public void load();
    abstract public void in();
    abstract public void out();

    protected void doNextScene(){
        Data.scenePanel.remove(this);
        Data.scenePanel.add(nextScene);
        nextScene.in();
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void setAlpha(float alpha){
        this.alpha=alpha;
        for (Component component: getComponents()){
            AlphaPanel alphaPanel=(AlphaPanel) component;
            alphaPanel.setAlpha(alpha);
        }
    }
    @Override
    public void paint(Graphics g) {

        Graphics2D g2d=(Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,(alpha)));
        super.paint(g2d);
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public float getAlpha() {
        return alpha;
    }

}
