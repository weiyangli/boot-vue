package com.boot.bvserver.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Question {

    @Id
    private Long Id;
    private String title;
    private String content;
    private Date pushDate;
}
