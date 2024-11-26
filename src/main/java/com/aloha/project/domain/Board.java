package com.aloha.project.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
    private int no;        
    private String title;
    private String writer;
    private String content;
    private Date createdAt;
    private Date updatedAt;
}
