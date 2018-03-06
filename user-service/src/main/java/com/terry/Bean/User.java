package com.terry.Bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;

/***
 * *
 * 名称：     User.
 * 作者：     Terry Tan
 * 创建时间：  on 2017/9/8.
 * 说明：     
 * *
 ***/

@Entity
public class User {

    @Id
    @GenericGenerator(name = "id", strategy = "assigned")
    public String id;
    public String name, sex, img;
    public int age;

}
