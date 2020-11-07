package indi.korostudio.saw.panel.component;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends AComponent{
    protected BufferedImage image;
    public ImagePanel(){

    }

    public ImagePanel(BufferedImage image){
        this.image=image;
    }

    public BufferedImage setImage(BufferedImage image){
        this.image=image;
        return image;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
    }
}
