package com.boot.bvserver.util;

import com.boot.bvserver.bean.Constant;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

public class Utils {

    /**
     * 使用md5的算法进行加密
     */
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code += "0";
        }
        return md5code;
    }

    /**
     * 从 header 或者 cookie 里获取 auth-token
     *
     * @param request HttpServletRequest 对象
     * @return 返回登录生成的 token
     */
    public static String getAuthToken(HttpServletRequest request) {
        String token = request.getHeader(Constant.AUTH_TOKEN_KEY);

        if (token == null) {
            token = getCookie(request, Constant.AUTH_TOKEN_KEY);
        }

        return token;
    }

    /**
     * 解码 URL Safe 的 Base64 编码的字符串 urlBase64Text.
     *
     * @param urlBase64Text URL Safe 的 Base64 编码的字符串
     * @return 返回源字符串
     */
    public static String unbase64UrlSafe(String urlBase64Text) {
        urlBase64Text = urlBase64Text.replace('-', '+');
        urlBase64Text = urlBase64Text.replace('_', '/');
        urlBase64Text = urlBase64Text.replace('*', '=');

        return Utils.unbase64(urlBase64Text);
    }

    /**
     * 获取名字为 name 的 cookie 的值
     *
     * @param request HttpServletRequest 对象
     * @param name    Cookie 的名字
     * @return 返回名字为 name 的 cookie 的值，如果 name 不存在则返回 null
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    /**
     * 解码 Base64 编码的字符串 base64Text.
     *
     * @param base64Text Base64 编码的字符串
     * @return 返回源字符串
     */
    public static String unbase64(String base64Text) {
        return new String(Base64.getDecoder().decode(base64Text), StandardCharsets.UTF_8);
    }

    /**
     * 对字符串 text 进行 URL Safe 的 Base64 编码: +, /, =, 被置换为 -, _, *, 只包含 64 个 URL safe 的字符: 0-9, a-z, A-Z, -, _
     * 注意: Base64.getUrlEncoder() 编码后的 Base64 结果还有 =，不能使用
     * <p>
     * 系统中有一些值使用 BASE64 编码后存储在 COOKIE 中, 当编码后的字符串最后有一个或者两个等号(=)时,
     * 使用 Request.getCookies().getValue() 会丢失等号, 再用 BASE64 解码时产生错误.
     *
     * @param text 要进行编码的字符串
     * @return 返回使用 URL Safe Base64 编码后的字符串
     */
    public static String base64UrlSafe(String text) {
        String base64Text = Utils.base64(text);
        base64Text = base64Text.replace('+', '-');
        base64Text = base64Text.replace('/', '_');
        base64Text = base64Text.replace('=', '*');

        return base64Text;
    }
    /**
     * 对字符串 text 进行 Base64 编码.
     * Base64 有 64 个字符: 0-9, a-z, A-Z, +, /
     * 等号 = 用于补齐.
     *
     * @param text 要进行编码的字符串
     * @return 返回使用 Base64 编码后的字符串
     */
    public static String base64(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 把 name/value 写入 cookie
     *
     * @param response 请求响应的 HttpServletResponse
     * @param name     cookie 的 name
     * @param value    cookie 的 value
     * @param maxAge   cookie 的过期时间，单位为秒，为 0 时删除 cookie
     */
    public static void writeCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    /**
     * 判断请求是否 AJAX 请求
     *
     * @param request HttpServletRequest 对象
     * @return 如果是 AJAX 请求则返回 true，否则返回 false
     */
    public static boolean useAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 删除 cookie
     *
     * @param response 请求响应的 HttpServletResponse
     * @param name     cookie 的 name
     */
    public static void deleteCookie(HttpServletResponse response, String name) {
        writeCookie(response, name, null, 0);
    }

    /**
     * 服务端不存储临时验证码，将图片验证码转 base64
     *
     * @param buffImg
     * @return
     * @throws Exception
     */
    public static String codeImageToBase64(BufferedImage buffImg) throws IOException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();//io流
        ImageIO.write(buffImg, "png", bao);//写入流中
        byte[] bytes = bao.toByteArray();//转换成字节
        BASE64Encoder encoder = new BASE64Encoder();
        String png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
        //删除 \r\n
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");
        return String.format("data:image/png;base64,%s", png_base64);
    }

    /**
     * mongo 日期查询isodate
     *
     * @param dateStr
     * @return
     */
    public static Date dateToISODate(String dateStr){
        //T代表后面跟着时间，Z代表UTC统一时间
        Date date = formatD(dateStr, "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
        String isoDate = format.format(date);
        try {
            return format.parse(isoDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date formatD(String dateStr ,String format)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date ret = null ;
        try {
            ret = simpleDateFormat.parse(dateStr) ;
        } catch (ParseException e) {
            //
        }
        return ret;
    }
   public static void main(String[] args) {
        System.out.println(md5("123456"));
    }
}
