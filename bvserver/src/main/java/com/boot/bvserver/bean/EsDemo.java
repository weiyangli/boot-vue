package com.boot.bvserver.bean;

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
    // searchAnalyzer 指定字段搜索时使用的分词器
    @Field(type= FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String content;
    private Date date;
    @Field(type= FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String level;
    private String url;
    private String price;

    public EsDemo() {

    }
}
