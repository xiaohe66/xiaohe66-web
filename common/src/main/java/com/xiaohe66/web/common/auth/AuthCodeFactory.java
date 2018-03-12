package com.xiaohe66.web.common.auth;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
public class AuthCodeFactory {

    private static AuthCodeFactory authCodeFactory = new AuthCodeFactory();

    /**
     * 验证码字符集
     */
    private final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    /**
     * 字符数量
     */
    private int size = 4;
    /**
     * 干扰线数量
     */
    private int lines = 6;
    /**
     * 宽度
     */
    private int width = 80;
    /**
     * 高度
     */
    private int height = 40;
    /**
     * 字体大小
     */
    private int fontSize = 30;
    /**
     * 字体颜色
     */
    private Color fontColor = Color.WHITE;
    /**
     * 字体
     */
    private String fontName = null;


    private static final Random RAN = new Random();


    public static AuthCodeFactory getInstance(){
        return authCodeFactory;
    }

    /**
     * 根据需要创建验证码对象
     * @param width 宽度
     * @param height 高度
     * @param size 验证码位数
     * @param lines 干扰线条数
     * @param fontColor 字体颜色，如果传入null，则使用默认的白色
     * @param fontSize 字体大小，如果传入null，则使用默认大小30
     * @param fontName 字体名称，如果传入null，则使用默认字体
     * todo:暂时使自定义属性的验证码无法创建，该逻辑待修改
     */
    private AuthCode createAuthCode(int width, int height, int size, int lines, Color fontColor,
                                    Integer fontSize, String fontName){
        this.width = width;
        this.height = height;
        this.size = size;
        this.lines = lines;
        if(fontColor != null) {
            this.fontColor = fontColor;
        }
        if(fontSize != null) {
            this.fontSize = fontSize;
        }
        if(fontName != null) {
            this.fontName = fontName;
        }
        return this.createAuthCode();
    }

    /**
     * 创建默认属性的验证码对象
     * 验证码位数    4
     * 干扰线数量    6
     * 验证码宽度    80
     * 验证码高度    40
     * 字体颜色     白色
     * 字体大小     30
     */
    public AuthCode createAuthCode() {
        StringBuilder stringBuilder = new StringBuilder();
        // 1.创建空白图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 2.获取图片画笔
        Graphics graphic = image.getGraphics();
        // 3.设置画笔颜色白色
        graphic.setColor(fontColor);
        // 4.绘制矩形背景
        graphic.fillRect(0, 0, width, height);
        // 5.画随机字符
        Random ran = new Random();
        for (int i = 0; i < size; i++) {
            // 取随机字符索引
            int n = ran.nextInt(CHARS.length);
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 设置字体大小
            graphic.setFont(new Font(fontName, Font.BOLD + Font.ITALIC, fontSize));
            // 画字符
            graphic.drawString(CHARS[n] + "", i * (width-10) / size + 5, height *2/3);
            // 记录字符
            stringBuilder.append(CHARS[n]);
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

        return new AuthCode(image,stringBuilder.toString(),new Date());
    }

    /**
     * 随机取色
     */
    private Color getRandomColor() {
        return new Color(nextColorVal(),nextColorVal(),nextColorVal());
    }

    private int nextColorVal(){
        return RAN.nextInt(256);
    }

}
