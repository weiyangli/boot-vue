package com.boot.bvserver.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "es-demo")
@Accessors(chain = true)
public class EsDemo {
    @Id
    private Long id;
    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String content;
    private Date date;
    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String level;
    private String url;
    private String price;

    public EsDemo() {

    }
}
