package ir.onlinestore.dao;

import ir.onlinestore.model.Brand;
import ir.onlinestore.model.Categories;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kimia on 1/18/2017.
 */
@Component
@Qualifier("BrandDao")
public class BrandDao extends AbstarctDao<Brand>{
    public Brand getById(long id){
        return (Brand) sessionFactory.getCurrentSession().get(Brand.class,id);
    }
    public List<Brand> getAllBrand(){

        return (List<Brand>)sessionFactory.getCurrentSession().createCriteria(Brand.class).list();
    }
}
