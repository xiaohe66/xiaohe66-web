package com.xiaohe66.web.common.auth;

import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.ParamFinal;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.Check;
import com.xiaohe66.web.common.util.WebUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
public class AuthCodeHelper {

    /**
     * 验证码字符集
     */
    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * 验证码超时毫秒数5分钟
     */
    private static final long AUTH_CODE_TIME_OUT_MS = 300000;

    private static final Random RAN = new Random();

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
    private static AuthCode createAuthCode(int width, int height, int size, int lines, Color fontColor,
                                    Integer fontSize, String fontName){
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

        AuthCode authCode = new AuthCode(image,stringBuilder.toString(),new Date());
        WebUtils.setSessionAttr(ParamFinal.SESSION_AUTH_CODE_KEY,authCode);
        return authCode;
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
    public static AuthCode createAuthCode() {
        return createAuthCode(80,40,4,6,Color.WHITE,30,null);
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

    public static boolean verify(String code) {

        AuthCode authCodeObj = WebUtils.getSessionAttr(ParamFinal.SESSION_AUTH_CODE_KEY);

        Check.notEmptyCheck(code,authCodeObj);

        //超时时间
        long difference = System.currentTimeMillis()-authCodeObj.getCreateTime().getTime();

        if(difference >= AUTH_CODE_TIME_OUT_MS){
            throw new XhException(CodeEnum.AUTH_CODE_TIME_OUT);
        }

        if(code.equalsIgnoreCase(authCodeObj.getCode())){
            WebUtils.removeSessionAttr(ParamFinal.SESSION_AUTH_CODE_KEY);
            return true;
        }

        return false;
    }

    public static boolean verifyNotClearSession(String code) {

        AuthCode authCodeObj = WebUtils.getSessionAttr(ParamFinal.SESSION_AUTH_CODE_KEY);

        Check.notEmptyCheck(code,authCodeObj);

        //超时时间
        long difference = System.currentTimeMillis()-authCodeObj.getCreateTime().getTime();

        if(difference >= AUTH_CODE_TIME_OUT_MS){
            throw new XhException(CodeEnum.AUTH_CODE_TIME_OUT);
        }

        return code.equalsIgnoreCase(authCodeObj.getCode());
    }

}
