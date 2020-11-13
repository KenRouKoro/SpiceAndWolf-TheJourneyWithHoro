package indi.korostudio.saw.tool;

import indi.korostudio.saw.main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tool {
    static public BufferedImage loadImage(String file){
        System.out.println("LOAD:"+Tool.class.getResource("/res"+file));
        URI uri=null;
        try {
            uri = Tool.class.getResource("/res"+file).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        BufferedImage bufferedImage= null;
        try {
            bufferedImage = ImageIO.read(uri.toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bufferedImage;
    }
    static public BufferedImage loadImage(URI uri){
        System.out.println("LOAD:"+uri);
        BufferedImage bufferedImage= null;
        try {
            bufferedImage = ImageIO.read(uri.toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  bufferedImage;
    }
    static final public BufferedImage fillRect(int width,int height,Color color){
        BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d=(Graphics2D) bufferedImage.getGraphics();
        g2d.setColor(color);
        g2d.fillRect(0,0,width,height);
        return bufferedImage;
    }
    static public BufferedImage stringImage(Color color,Font font,String string){
        int i[]=count(string);
        int width=i[0]*font.getSize()+(i[1]+i[2]+i[3]+i[4])*font.getSize()/2;
        BufferedImage test=new BufferedImage(200,200,BufferedImage.TYPE_4BYTE_ABGR);
        test.getGraphics().setFont(font);
        FontRenderContext context= ((Graphics2D)test.getGraphics()).getFontRenderContext();
        LineMetrics lineMetrics = font.getLineMetrics(string, context);


        BufferedImage bufferedImage=new BufferedImage(width, (int) lineMetrics.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d =(Graphics2D) bufferedImage.getGraphics();
        g2d.setFont(font);
        float offset = 0;
        float y = (font.getSize() + lineMetrics.getAscent() - lineMetrics.getDescent() - lineMetrics.getLeading()) / 2;
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.drawString(string,0,y);
        return bufferedImage;
    }
    static public int[] count(String str) {
        /*中文字符 */
        int chCharacter = 0;
        /*英文字符 */
        int enCharacter = 0;
        /*空格 */
        int spaceCharacter = 0;
        /*数字 */
        int numberCharacter = 0;
        /*其他字符 */
        int otherCharacter = 0;
        if (null == str || str.equals("")) {
            return null;
        }

        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if ((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z')) {
                enCharacter ++;
            } else if ((tmp >= '0') && (tmp <= '9')) {
                numberCharacter ++;
            } else if (tmp ==' ') {
                spaceCharacter ++;
            } else if (isChinese(tmp)) {
                chCharacter ++;
            } else {
                otherCharacter ++;
            }
        }
         return new int[]{chCharacter, enCharacter, numberCharacter, spaceCharacter, otherCharacter};
    }
    static public boolean isChinese(char ch) {
        //获取此字符的UniCodeBlock
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
        //  GENERAL_PUNCTUATION 判断中文的“号
        //  CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
        //  HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
}
