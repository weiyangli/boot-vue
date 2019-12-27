package com.boot.bvserver.util;

import com.boot.bvserver.bean.Constant;
import com.boot.bvserver.bean.Mime;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.SimpleTimeZone;

public class Utils {

    private static Logger logger = LoggerFactory.getLogger(Utils.class.getName());

    private static final int DEFAULT_BUFFER_SIZE   = 2097152; // bytes = 2MB
    private static final String MULTIPART_BOUNDARY = "MULTIPART_BYTERANGES";

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

    /**
     * 日期转字符串
     *
     * @param date     日期
     * @param format   日期格式
     * @return
     */
    public static String DateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 使用从 classes 目录下相对路径为 path 的文件创建 InputStream
     *
     * @param path 文件相对 classes 目录的路径
     * @return 返回文件 path 对应的 InputStream
     */
    public static InputStream getStreamRelativeToClassesDirectory(String path) {
        return Utils.class.getClassLoader().getResourceAsStream(path);
    }

    /**
     * 根据文件名 filename 判读它是否图片
     *
     * @param filename 文件名
     * @return 如果文件是图片则返回 true，否则返回 false
     */
    public static boolean isImage(String filename) {
        return Mime.isImage(filename);
    }

    /**
     * 获取图片的大小
     * <p>
     * This solution is very quick as only image size is read from the file and not the whole image.
     * From: https://stackoverflow.com/questions/672916/how-to-get-image-height-and-width-using-java
     * <p>
     * Blows ImageIO.read() completely out of the water, both in terms of CPU time and memory usage.
     *
     * @param path 图片文件的路径
     * @return 返回图片大小的 dimension 对象
     */
    public static Dimension getImageSize(String path) {
        Dimension result = null;
        String suffix = FilenameUtils.getExtension(path);
        Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);

        if (iter.hasNext()) {
            ImageReader reader = iter.next();

            try (ImageInputStream stream = new FileImageInputStream(new File(path))) {
                reader.setInput(stream);
                int width = reader.getWidth(reader.getMinIndex());
                int height = reader.getHeight(reader.getMinIndex());
                result = new Dimension(width, height);
            } catch (IOException e) {
                logger.warn(e.getMessage());
            } finally {
                reader.dispose();
            }
        } else {
            logger.warn("No ImageReader found for given format: " + suffix);
        }

        return result;
    }

    /**
     * 获取请求中的文件名
     * 例如 http://localhost:8080/preview/file/temp/220059763684147200.doc?size=100 得到文件名 220059763684147200.doc
     *
     * @param request HttpServletRequest 对象
     * @return 返回请求的文件名
     */
    public static String getUriFilename(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int last = uri.lastIndexOf(46);
        String filename = com.mzlion.core.io.FilenameUtils.getFilename(uri);

        return filename;
    }

    /**
     * 读取文件到 HttpServletResponse
     *
     * @param path     文件路径
     * @param response HttpServletResponse 对象
     */
    public static void readFileToResponse(String path, HttpServletResponse response) throws IOException {
        readFileToResponse(path, com.mzlion.core.io.FilenameUtils.getFilename(path), getRequest(), response);
    }

    /**
     * 读取文件到 HttpServletResponse
     *
     * @param path     // 文件的路径
     * @param filename // 文件名，因为文件的路径中的文件名是编码过的，而真实的文件名保存在数据库，所以 path 中的文件名很可能不是真正的文件名
     * @param request  // HttpServletRequest 对象
     * @param response // HttpServletResponse 对象
     * @throws IOException 访问文件发生异常时抛出
     */
    public static void readFileToResponse(String path, String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Utils.readFileEndToResponse(path, filename, request, response);
    }

    /**
     * 获取当前线程的 request
     *
     * @return 返回 request
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 读取文件到 response
     *
     * @param path     // 文件的路径
     * @param filename // 文件名，因为文件的路径中的文件名是编码过的，而真实的文件名保存在数据库，所以 path 中的文件名很可能不是真正的文件名
     * @param request  // HttpServletRequest 对象
     * @param response // HttpServletResponse 对象
     * @throws IOException 访问文件发生异常时抛出
     */
    public static void readFileEndToResponse(String path, String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 如果文件不存在则返回 404 页面
        // 2. 如果 header 中有 Range，则校验和处理 Range
        //    2.1 Range 头的格式为 "bytes=n-n,n-n,n-n...". 如果不匹配，则返回 416
        //    2.2 处理 If-Range 头
        //    2.3 如果没有有效的 If-Range 头, 则处理 Range 中的每一部分，使用逗号分隔
        // 3. 设置响应头
        //    3.1 使用文件名获取 content type 和 content disposition
        //    3.2 初始化 response，设置响应头
        // 4. 根据 ranges 读取文件到 response
        //    4.1 ranges 为空或者只有一个元素并为 fullRange 时，则读取整个文件 (fullRange 为处理 If-Range 头得到的)
        //    4.2 ranges 只有一个元素并不为 fullRange 时，读取文件的部分，范围由 ranges.get(0) 指定
        //    4.3 ranges 有多个元素时，读取文件的多个部分，范围由 ranges 指定

        // [1] 如果文件不存在则返回 404 页面
        if (!Files.exists(Paths.get(path))) {
            logger.warn("文件 {} 不存在", path);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        filename = StringUtils.isBlank(filename) ? FilenameUtils.getName(path) : filename; // 传入的文件名为空时取 path 中的文件名
        long        length    = Files.size(Paths.get(path));      // 文件的长度
        Range       fullRange = new Range(0, length - 1, length); // 整个文件的 range
        String      range     = request.getHeader("Range");
        List<Range> ranges    = new ArrayList<>();

        // [2] 如果 header 中有 Range，则校验和处理 Range
        if (range != null) {
            // [2.1] Range 头的格式为 "bytes=n-n,n-n,n-n...". 如果不匹配，则返回 416
            //       Safari: Range: bytes=0-1
            //               Range: bytes=0-17611749
            //       Chrome: Range: bytes=0-
            //               Range: bytes=458752-
            if (!range.matches("^bytes=\\d*-\\d*(,\\d*-\\d*)*$")) {
                response.setHeader("Content-Range", "bytes */" + length); // Required in 416.
                response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                return;
            }

            // [2.2] 处理 If-Range 头
            String ifRange = request.getHeader("If-Range");
            if (ifRange != null && !ifRange.equals(FilenameUtils.getName(path))) {
                try {
                    long ifRangeTime = request.getDateHeader("If-Range"); // Throws IAE if invalid.
                    if (ifRangeTime != -1) {
                        ranges.add(fullRange);
                    }
                } catch (IllegalArgumentException ignore) {
                    ranges.add(fullRange);
                }
            }

            // [2.3] 如果没有有效的 If-Range 头, 则处理 Range 中的每一部分，使用逗号分隔
            if (ranges.isEmpty()) {
                for (String part : range.substring(6).split(",")) {
                    // Assuming a file with length of 100, the following examples returns bytes at:
                    // 50-80 (50 to 80), 40- (40 to length=100), -20 (length-20=80 to length=100).

                    // Chrome always starts its first video request with the following: Range: bytes=0-
                    // Safari 的第一个请求为 Range: bytes=0-1
                    // 参考: https://stackoverflow.com/questions/3303029/http-range-header

                    if ("0-".equals(part)) {
                        ranges.add(new Range(0, 1, length));
                    } else {
                        long start = Range.sublong(part, 0, part.indexOf("-"));
                        long end   = Range.sublong(part, part.indexOf("-") + 1, part.length());

                        if (start == -1) {
                            start = length - end;
                            end = length - 1;
                        } else if (end == -1 || end > length - 1) {
                            end = length - 1;
                        }

                        // Check if Range is syntactically valid. If not, then return 416.
                        if (start > end) {
                            response.setHeader("Content-Range", "bytes */" + length); // Required in 416.
                            response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                            return;
                        }

                        // Add range.
                        ranges.add(new Range(start, end, length));
                    }
                }
            }

            // Range 调试信息
            logger.debug("Range: {}", range);
            logger.debug(ranges.toString());
        }

        // [3] 设置响应头
        // [3.1] 使用文件名获取 content type 和 content disposition
        String contentType = Mime.getContentType(filename);
        String disposition = "inline";
        if (!contentType.startsWith("image")) {
            // Expect for images, determine content disposition. If content type is supported by
            // the browser, then set to inline, else attachment which will pop a 'save as' dialogue.
            String accept = request.getHeader("Accept");
            disposition = (accept != null && HttpUtils.accepts(accept, contentType)) ? "inline" : "attachment";
        }

        filename = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1); // 解决文件名乱码问题

        // [3.2] 初始化 response，设置响应头
        response.reset(); // Initialize response.
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Disposition", disposition + ";filename=\"" + filename + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("ETag", filename);

        // [4] 根据 ranges 读取文件到 response
        try (RandomAccessFile input = new RandomAccessFile(path, "r"); OutputStream output = response.getOutputStream()) {
            if (response.isCommitted()) { return; } // Chrome 访问视频和音频第一次探测时很可能到这里就已经提交了 response.

            if (ranges.isEmpty() || ranges.get(0) == fullRange) {
                // [4.1] ranges 为空或者只有一个元素并为 fullRange 时，则读取整个文件 (fullRange 为处理 If-Range 头得到的)
                logger.debug("返回整个文件: {}", path);

                response.setHeader("Content-Range", "bytes " + fullRange.start + "-" + fullRange.end + "/" + fullRange.total);
                response.setHeader("Content-Length", String.valueOf(fullRange.length));
                Range.copy(input, output, length, fullRange.start, fullRange.length);
            } else if (ranges.size() == 1) {
                // [4.2] ranges 只有一个元素并不为 fullRange 时，读取文件的部分，范围由 ranges.get(0) 指定
                Range r = ranges.get(0);
                logger.debug("返回文件的一个部分 : from ({}) to ({}), Path: {}", r.start, r.end, path);

                response.setHeader("Content-Range", "bytes " + r.start + "-" + r.end + "/" + r.total); // Content-Range: bytes 0-17611749/17611750
                response.setHeader("Content-Length", String.valueOf(r.length));
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206.
                Range.copy(input, output, length, r.start, r.length); // Copy single part range.
            } else {
                // [4.3] ranges 有多个元素时，读取文件的多个部分，范围由 ranges 指定
                response.setContentType("multipart/byteranges; boundary=" + MULTIPART_BOUNDARY);
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206.

                // Cast back to ServletOutputStream to get the easy println methods.
                ServletOutputStream sos = (ServletOutputStream) output;

                // Copy multi part range.
                for (Range r : ranges) {
                    logger.debug("返回文件的多个部分: from ({}) to ({}), Path: ", r.start, r.end, path);

                    // Add multipart boundary and header fields for every range.
                    sos.println();
                    sos.println("--" + MULTIPART_BOUNDARY);
                    sos.println("Content-Type: " + contentType);
                    sos.println("Content-Range: bytes " + r.start + "-" + r.end + "/" + r.total);
                    Range.copy(input, output, length, r.start, r.length); // Copy single part range of multi part range.
                }

                // End with multipart boundary.
                sos.println();
                sos.println("--" + MULTIPART_BOUNDARY + "--");
            }
        }
    }

    private static class Range {
        long start;  // 开始位置
        long end;    // 结束位置
        long length; // 读取长度
        long total;  // 总的长度

        /**
         * Construct a byte range.
         *
         * @param start Start of the byte range.
         * @param end   End of the byte range.
         * @param total Total length of the byte source.
         */
        public Range(long start, long end, long total) {
            this.start  = start;
            this.end    = end;
            this.length = end - start + 1; // 从 0 开始，所以长度需要加 1
            this.total  = total;
        }

        @Override
        public String toString() {
            return String.format("Range{ start=%d, end=%d, length=%d, total=%d }", start, end, length, total);
        }

        /**
         * 把指定范围内字符串转换为数字
         *
         * @param text       // 字符串
         * @param beginIndex // 开始下标
         * @param endIndex   // 结束下标
         * @return 返回数字，如果范围无效则返回 -1
         */
        public static long sublong(String text, int beginIndex, int endIndex) {
            String substring = text.substring(beginIndex, endIndex);
            return (substring.length() > 0) ? Long.parseLong(substring) : -1;
        }

        private static void copy(RandomAccessFile input, OutputStream output, long inputSize, long start, long length) throws IOException {
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int read;

            if (inputSize == length) {
                // Write full range.
                while ((read = input.read(buffer)) > 0) {
                    output.write(buffer, 0, read);
                    output.flush();
                }
            } else {
                input.seek(start);
                long toRead = length;

                while ((read = input.read(buffer)) > 0) {
                    if ((toRead -= read) > 0) {
                        output.write(buffer, 0, read);
                        output.flush();
                    } else {
                        output.write(buffer, 0, (int) toRead + read);
                        output.flush();
                        break;
                    }
                }
            }
        }
    }

    private static class HttpUtils {
        /**
         * Returns true if the given accept header accepts the given value.
         *
         * @param acceptHeader The accept header.
         * @param toAccept The value to be accepted.
         * @return True if the given accept header accepts the given value.
         */
        public static boolean accepts(String acceptHeader, String toAccept) {
            String[] acceptValues = acceptHeader.split("\\s*(,|;)\\s*");
            Arrays.sort(acceptValues);

            return Arrays.binarySearch(acceptValues, toAccept) > -1
                    || Arrays.binarySearch(acceptValues, toAccept.replaceAll("/.*$", "/*")) > -1
                    || Arrays.binarySearch(acceptValues, "*/*") > -1;
        }

        /**
         * Returns true if the given match header matches the given value.
         *
         * @param matchHeader The match header.
         * @param toMatch The value to be matched.
         * @return True if the given match header matches the given value.
         */
        public static boolean matches(String matchHeader, String toMatch) {
            String[] matchValues = matchHeader.split("\\s*,\\s*");
            Arrays.sort(matchValues);
            return Arrays.binarySearch(matchValues, toMatch) > -1
                    || Arrays.binarySearch(matchValues, "*") > -1;
        }
    }
}
