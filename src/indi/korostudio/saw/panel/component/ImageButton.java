package indi.korostudio.saw.panel.component;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageButton extends ImagePanel{
    protected BufferedImage on,click,now,none;
    protected ArrayList <String>cmdList=new ArrayList();
    protected void load(){
        now = none;
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                now=click;
                setImage(now);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                now=none;
                setImage(now);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                now=on;
                setImage(now);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                now=none;
                setImage(now);
            }
        });
        setImage(now);
    }
    public ImageButton(BufferedImage on,BufferedImage click,BufferedImage none){
        this.on=on;
        this.click=click;
        this.none=none;
        load();
    }
    public ImageButton(BufferedImage click,BufferedImage none){
        this.click=click;
        this.none=none;
        on=none;
        load();
    }
    public ImageButton(BufferedImage none){
        this.none=none;
        on=none;
        click=none;
        load();
    }

    public void setOn(BufferedImage on) {
        this.on = on;
    }

    public void setClick(BufferedImage click) {
        this.click = click;
    }

    public void setNone(BufferedImage none) {
        this.none = none;
    }

    public void setNow(BufferedImage now) {
        this.now = now;
    }

    public BufferedImage getClick() {
        return click;
    }

    public BufferedImage getNone() {
        return none;
    }

    public BufferedImage getNow() {
        return now;
    }

    public BufferedImage getOn() {
        return on;
    }
}
