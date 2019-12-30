package com.boot.bvserver;

import com.boot.bvserver.bean.Message;
import com.boot.bvserver.bean.MessageType;
import com.boot.bvserver.service.MessageService;
import com.boot.bvserver.util.FileConvertUtil;
import com.boot.bvserver.util.Utils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BvserverApplicationTests {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private FileConvertUtil fileConvertUtil;

    @Test
    public void mongodbTest() {
        Message message = new Message();
        message.setId(33332L).setChatId("1_2").setContent("你好111").setTitle("today").setType(1)
        .setValidityDate(DateUtils.addDays(new Date(), 7)).setReceiveId(2l).setUserId(1l)
        .setDate(new Date());
        mongoTemplate.save(message, MessageType.getValueByCode(1));
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
    public void word2Pdf() throws Exception{
        File sourceFile = new File("C:/Users/Public/Desktop/公司级员工教程/【员工教程】域添加教程 For Win7.doc");
        File targetFile = new File("C:/Users/admin/Desktop/3333.pdf");
        fileConvertUtil.ConverterWordToPdf(sourceFile, targetFile);
    }

}
