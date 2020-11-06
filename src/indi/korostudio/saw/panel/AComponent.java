package indi.korostudio.saw.panel;

import javax.swing.*;
import java.awt.*;

public class AComponent extends JPanel implements AlphaPanel {
    public static final float Alpha=1f;
    protected float alpha=1f;
    public void setAlpha(float alpha){
        this.alpha=alpha;
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
