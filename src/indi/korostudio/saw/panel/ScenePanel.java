package indi.korostudio.saw.panel;

import indi.korostudio.saw.data.Data;

import javax.swing.*;
import java.awt.*;

public class ScenePanel extends JPanel {
        public ScenePanel(){
            setLayout(null);
            setBounds(Data.mainRectangle);
            setBackground(Color.white);
            setVisible(true);
            System.out.println(this);
            Data.mainFrame.add(this);
        }
}
