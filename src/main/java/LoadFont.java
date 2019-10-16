import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class LoadFont {
    public static Font loadFont(String fontFileName, float fontSize) {
        try {
            File file = new File(fontFileName);
            FileInputStream aixing = new FileInputStream(file);
            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
            Font dynamicFontPt = dynamicFont.deriveFont(1, fontSize);
            aixing.close();
            return dynamicFontPt;
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Font("宋体", Font.PLAIN, 14);
        }
    }
    public static Font titleFont() {
        String root = System.getProperty("user.dir");//项目根目录路径
        Font font = LoadFont.loadFont(root + "/font/title.ttf", 100f);//调用
        return font;//返回字体
    }
}