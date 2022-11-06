package com.laioffer.onlineOrder.dao;

import com.laioffer.onlineOrder.entity.Cart;
import com.laioffer.onlineOrder.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository告诉spring这是dao层，托管
@Repository
public class CartDao {

    //注入sessionFactory工程
    @Autowired
    private SessionFactory sessionFactory;

    public void removeCartItem(int orderItemId) {
        Session session = null;

        //创建session
        try {
            //创建session
            session = sessionFactory.openSession();
            OrderItem orderItem = session.get(OrderItem.class, orderItemId);
            Cart cart = orderItem.getCart();
            cart.getOrderItemList().remove(orderItem);

            session.beginTransaction();
            session.delete(orderItem);
            session.getTransaction().commit();

        }catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
        finally {
            if (session != null) {
                session.close();
            }

        }
    }

    public void removeAllCartItems(Cart cart) {
        for (OrderItem item : cart.getOrderItemList()) {
            removeCartItem(item.getId());
        }
    }

}
