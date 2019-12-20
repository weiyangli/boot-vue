package com.boot.bvserver.util;

import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 生成图片验证码工具类
 *
 */
public class CodeUtil {
    private static int width = 90;// 定义图片的width
    private static int height = 30;// 定义图片的height
    private static int codeCount = 4;// 定义图片上显示验证码的个数
    private static int xx = 15;
    private static int fontHeight = 18;
    private static  int codeY = 22;
    private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    /**
    * 生成一个map集合
    * code为生成的验证码
    * codePic为生成的验证码BufferedImage对象
    * @return
    */
    public static JSONObject generateCodeAndPic() throws IOException{
        // 定义图像 buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Graphics2D gd = buffImg.createGraphics();
        // Graphics2D gd = (Graphics2D) buffImg.getGraphics();
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体。
        gd.setFont(font);
        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);
        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;
        // 随机产生codeCount数字的验证码
        // 这个地方如果是多线程的话可能会存在一个线程使用了上一个线程的随机码画了图然后又用了当前线程的生成的随机码拼接了code
        // 可能导致图中画的字符和生成的不一致
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(36)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);
            // 将产生的四个随机数组合在一起
            randomCode.append(code);
        }
        String codeBase64 = Utils.codeImageToBase64(buffImg);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", String.valueOf(randomCode));
        jsonObject.put("codeBase64", codeBase64);
        return jsonObject;
    }

    public static void main(String[] args) throws Exception {
        JSONObject map = generateCodeAndPic();
        System.out.println(map.get("code"));
        System.out.println(map.get("codeBase64"));
    }
}
