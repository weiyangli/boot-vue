package com.boot.bvserver.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Accessors(chain = true)
@Document(indexName = "question")
public class Question {

    @Id
    private Long Id;
    private String title;
    @Field(type= FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String questionStem;
    private Date pushDate;
}
