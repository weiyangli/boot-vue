package com.liweiyang.spark.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.liweiyang.spark.controller.DemoControler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * freemarker 模板引擎工具类
 *
 */
@Slf4j
@Component
public class FreemarkerUtil {

    /**fm 引擎版本号**/
    private final static String VERSION = "2.3.0";
    /**模板文件路径**/
    private final static String BASE_PACKAGE_PATH = "/ftl";
    private final static String TARGET_FILE_ADDRESS = "/ftl/template/";

    /**
     * fm 生成目标页面
     *
     * @param ftlTemplate      // 模板名称
     * @param targetFileName   // 生成目标文件名
     * @param params           // 携带参数
     */
    public void generateTemplate(String ftlTemplate, String targetFileName, Object params) {
        log.info("开始生成模板...");
        try {
            //创建fm的配置
            Configuration config = new Configuration( new Version(VERSION));
            //指定默认编码格式
            config.setDefaultEncoding("UTF-8");
            //设置模版文件的路径
            config.setClassForTemplateLoading(FreemarkerUtil.class, BASE_PACKAGE_PATH);
            //获得模版包
            Template template = config.getTemplate(ftlTemplate);
            //生成文件目标文件夹是否存在
            File folder = new File(TARGET_FILE_ADDRESS);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            //定义输出流，注意必须指定编码
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(TARGET_FILE_ADDRESS + targetFileName)),"UTF-8"));
            //生成模版
            Map<String, String> map = new HashMap<>(1);
            // 转 json 禁止 fastjson 循环引用
            map.put("data", JSONObject.toJSONString(params, SerializerFeature.DisableCircularReferenceDetect));
            template.process(map, writer);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof FileNotFoundException) {
                log.error("没有找到模板 {}", ftlTemplate);
            } else {
                log.error("生成目标模板失败 {}", e.getMessage());
            }
        }
        log.info("fm 生成目标文件成功 {}", targetFileName);
    }
}
