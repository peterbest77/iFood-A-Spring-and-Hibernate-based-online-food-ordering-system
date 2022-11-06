package com.laioffer.onlineOrder.dao;

import com.laioffer.onlineOrder.entity.MenuItem;
import com.laioffer.onlineOrder.entity.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Restaurant> getRestaurants(){
    try(Session session = sessionFactory.openSession()) {
        //session写里面自动close session
        //返回Restaurant List只能用这个，setResultTransformer是去重，因为在Restaurant实体类中会和MenuItemList进行leftJoin
        //这会产生每个MenuItem都有个restaurant与之对应，所以会重复，所以要去重？？？？？
        return session.createCriteria(Restaurant.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();

    }catch (Exception ex) {
        ex.printStackTrace();
    }
    return new ArrayList<>();
    }

    public List<MenuItem> getAllMenuItem(int restaurantId){
        try (Session session = sessionFactory.openSession()){
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if (restaurant != null) {
                return restaurant.getMenuItemList();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();


    }
    public MenuItem getMenuItem(int menuItemId){
        try (Session session = sessionFactory.openSession()){
            return session.get(MenuItem.class, menuItemId);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
