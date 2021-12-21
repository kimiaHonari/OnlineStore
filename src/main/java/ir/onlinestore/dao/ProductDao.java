package ir.onlinestore.dao;

import ir.onlinestore.model.Account;
import ir.onlinestore.model.Brand;
import ir.onlinestore.model.Product;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimia on 1/18/2017.
 */
@Component
@Qualifier("ProductDao")
public class ProductDao extends AbstarctDao<Product> {

    public Product getById(long id) {
        return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
    }

    public List<Product> getAllProduct() {
        return (List<Product>) sessionFactory.getCurrentSession().createCriteria(Product.class).list();
    }

    public List<Product> getAllProductAsc(){
        return (List<Product>) sessionFactory.getCurrentSession().createCriteria(Product.class).addOrder(Order.asc("price")).list();
    }
    public List<Product> getAllProductDes(){
        return (List<Product>) sessionFactory.getCurrentSession().createCriteria(Product.class).addOrder(Order.desc("price")).list();
    }
}
