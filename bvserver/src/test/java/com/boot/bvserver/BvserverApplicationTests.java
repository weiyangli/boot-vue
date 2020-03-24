package com.boot.bvserver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.bvserver.bean.Dpg;
import com.boot.bvserver.bean.EsDemo;
import com.boot.bvserver.bean.Message;
import com.boot.bvserver.bean.MessageType;
import com.boot.bvserver.bean.Question;
import com.boot.bvserver.repository.ChatMessageRepository;
import com.boot.bvserver.service.DemoService;
import com.boot.bvserver.service.MessageService;
import com.boot.bvserver.util.FileConvertUtil;
import com.boot.bvserver.util.IdWorker;
import com.boot.bvserver.util.JsoupUtil;
import com.boot.bvserver.util.Utils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.util.Date;
import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Dpg.class)
public class BvserverApplicationTests {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private FileConvertUtil fileConvertUtil;

    @Autowired
    private JsoupUtil jsoupUtil;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private DemoService demoService;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private Dpg dpg;

    @BeforeClass
    public static void setSystemProperty() {
        Properties properties = System.getProperties();
        // 防止 netty 冲突
        properties.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    public static void main(String[] args) throws Exception{
        String a = String.format("%s%s/%s.%s", "D://bv_upload/file/", Utils.DateToString(new Date(), "yyyyMMdd"), "111111111", "txt");
        File file = new File(a);
        if (!file.exists()) {
            file.mkdirs();
        }
        System.out.println(a);
    }

    @Test
    public void mongodbTest() {
        Message message = new Message();
        message.setId(33332L).setChatId("1_2").setContent("你好111").setTitle("today").setType(1)
        .setValidityDate(DateUtils.addDays(new Date(), 7)).setReceiveId(2l).setUserId(1l)
        .setDate(new Date());
        mongoTemplate.save(message, MessageType.getValueByCode(1));
    }

    @Test
    public void word2Pdf() throws Exception{
        File sourceFile = new File("C:/Users/Public/Desktop/公司级员工教程/【员工教程】域添加教程 For Win7.doc");
        File targetFile = new File("C:/Users/admin/Desktop/3333.pdf");
        fileConvertUtil.ConverterWordToPdf(sourceFile, targetFile);
    }

    @Test
    public void word2Pdf2() throws Exception{
        System.out.println(dpg.getMessage());
    }

}
