package com.laioffer.onlineOrder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//@Entity让spring知道这是实体类
//@Table是让spring知道这是个表单，name里是这个表单达名字，如果不写这个默认表单名字是类名，数据库不区分大小写
//Serializable是为了将数据更好的保存，入保存到硬盘以二进制格式，这个序列化就是转换成机器可读模式
@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {

    //版本控制，数据库中版本号会与这个版本号互相检验，如果表单结构发生改变，必须要更新新的版本号，要不会报错，因为
    //实体类结构与数据库中表单结构不一样
    private static final long serialVersionUID = 8734140534986494039L;

    //@Id是表示这是主键的意思
    @Id
    private String email;

    private String authorities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
