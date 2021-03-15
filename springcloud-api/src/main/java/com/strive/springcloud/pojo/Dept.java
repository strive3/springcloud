package com.strive.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 1:20 下午
 * @Description Dept实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式写法
public class Dept implements Serializable {
    private Long dept_no;
    private String dept_name;
    //这个数据是存在哪个数据库的字段
    private String db_source;

    public Dept(String dept_name){
        this.dept_name = dept_name;
    }
}
