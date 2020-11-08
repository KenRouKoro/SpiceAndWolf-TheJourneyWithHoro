package indi.korostudio.saw.panel;

import indi.korostudio.saw.data.Data;
import indi.korostudio.saw.tool.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ScenePanel extends JPanel {
    protected BufferedImage test;
        public ScenePanel(){
            setLayout(null);
            setBounds(Data.mainRectangle);
            setBackground(Color.white);
            setVisible(true);
            System.out.println(this);
            Data.mainFrame.add(this);
            test= Tool.stringImage(Color.black,new Font("Times New Roman",Font.BOLD,30),"Test Ver");
        }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(test,0,0,null);
    }
}
