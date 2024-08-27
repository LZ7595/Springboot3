package com.itheima.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private int pages;
    private int pageNum;
    private Long total;
    private List rows;
    private int pageSize;
}
