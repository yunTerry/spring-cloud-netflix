package com.terry.Bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/***
 * *
 * 名称：     Pswd.
 * 作者：     Terry Tan
 * 创建时间：  on 2017/9/8.
 * 说明：     
 * *
 ***/

@Entity
public class Pswd {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    public String id;
    public String username, password;

}
