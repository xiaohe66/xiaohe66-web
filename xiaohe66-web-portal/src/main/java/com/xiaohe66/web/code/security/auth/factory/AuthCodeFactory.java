package com.xiaohe66.web.code.security.auth.factory;

import com.xiaohe66.web.code.security.auth.entity.EmailAuthCode;
import com.xiaohe66.web.code.security.auth.entity.ImgAuthCode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
public class AuthCodeFactory {

    /**
     * 验证码字符集
     */
    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static final Random RAN = new Random();

    public static ImgAuthCode createImgAuthCode() {
        String codeStr = createCodeStr();
        return new ImgAuthCode(createCodeImg(codeStr),codeStr,new Date());
    }

    public static EmailAuthCode createEmailAuthCode(String targetEmail){
        String codeStr = createCodeStr();
        return new EmailAuthCode(targetEmail,codeStr,new Date());
    }


    private static String createCodeStr(){
        return createCodeStr(4);
    }

    private static String createCodeStr(int size){
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < size; i++) {
            int n = ran.nextInt(CHARS.length);
            code.append(CHARS[n]);
        }
        return code.toString();
    }

    /**
     * 根据验证码字符串生成图片
     * @param code 验证码字符数组
     * @param width 宽度
     * @param height 高度
     * @param lines 干扰线条数
     * @param fontColor 字体颜色，如果传入null，则使用默认的白色
     * @param fontSize 字体大小，如果传入null，则使用默认大小30
     * @param fontName 字体名称，如果传入null，则使用默认字体
     * todo:暂时使自定义属性的验证码无法创建，该逻辑待修改
     */
    private static BufferedImage createCodeImg(char[] code, int width, int height, int lines, Color fontColor,
                                               Integer fontSize, String fontName){
        // 1.创建空白图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 2.获取图片画笔
        Graphics graphic = image.getGraphics();
        // 3.设置画笔颜色白色
        graphic.setColor(fontColor);
        // 4.绘制矩形背景
        graphic.fillRect(0, 0, width, height);
        // 5.画验证字符
        Random ran = new Random();
        for (int i = 0; i < code.length; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 设置字体大小
            graphic.setFont(new Font(fontName, Font.BOLD + Font.ITALIC, fontSize));
            // 画字符
            graphic.drawString(code[i] + "", i * (width-10) / code.length + 5, height *2/3);
        }
        // 6.画干扰线
        for (int i = 0; i < lines; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 随机画线
            graphic.drawLine(ran.nextInt(width), ran.nextInt(height),ran.nextInt(width), ran.nextInt(height));
        }

        //画点
        final int divisor = 20;
        for(int i=0;i<width*height/divisor;i++){
            graphic.setColor(getRandomColor());
            graphic.fillOval(ran.nextInt(width),ran.nextInt(height),2,2);
        }

        return image;
    }

    /**
     * 根据验证码字符串生成图片
     * 干扰线数量    6
     * 验证码宽度    80
     * 验证码高度    40
     * 字体颜色     白色
     * 字体大小     30
     */
    private static BufferedImage createCodeImg(String code){
        return createCodeImg(code.toCharArray(),80,40,6, Color.WHITE,30,null);
    }

    /**
     * 随机取色
     */
    private static Color getRandomColor() {
        return new Color(nextColorVal(),nextColorVal(),nextColorVal());
    }

    private static int nextColorVal(){
        return RAN.nextInt(256);
    }

}
