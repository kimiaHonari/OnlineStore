package ir.onlinestore.dao;

import ir.onlinestore.model.Categories;
import ir.onlinestore.model.Product;
import org.apache.log4j.Category;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kimia on 1/18/2017.
 */
@Component
@Qualifier("CategorieDao")
public class CategorieDao extends AbstarctDao<Categories>{
    public Categories getById(long id){
        return (Categories) sessionFactory.getCurrentSession().get(Categories.class,id);
    }
    public List<Categories> getAllCategory(){

        return (List<Categories>)sessionFactory.getCurrentSession().createCriteria(Categories.class).list();
    }
    public List<Categories> getAllParentCategory(){
        Categories parent=getById(0);
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Categories.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(Restrictions.eq("parentCategory",parent));
        return (List<Categories>)criteria.list();
    }
}
