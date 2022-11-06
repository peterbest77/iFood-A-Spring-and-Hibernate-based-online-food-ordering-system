package com.laioffer.onlineOrder.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 8436097833452420298L;

    // @GeneratedValue是产生hibernate_sequence,然后自增，各个表的主键id都用这个自增的id
    //这样不会造成id冲突，我为什么必须要用这个？？？？，每个表用自己的id不行吗
    //也可以， 因为这个可以在多线程的时候让数据库解决冲突，而且每个表维护一个id，
    //每次要去数据库查id再自增一比较麻烦
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //一对多，一个cart可以有多个orderItem
    //cascade是干嘛用的？？？
    //mappedBy的意思是干什么的？？ 外键关联让hibernate知道是外键关联
    //cascade是让表带上我要的数据
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    private double totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
