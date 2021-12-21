package ir.onlinestore.service;

import ir.onlinestore.dao.CategorieDao;
import ir.onlinestore.model.Categories;
import org.apache.log4j.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kimia on 1/18/2017.
 */
@Service("categorietservice")
@Transactional
public class CategorieServices {
    @Autowired
    CategorieDao categorieDao;

    public CategorieDao getCategorieDao() {
        return categorieDao;
    }

    public void setCategorieDao(CategorieDao categorieDao) {
        this.categorieDao = categorieDao;
    }

    public Categories getById(long id){
        return categorieDao.getById(id);
    }
    public List<Categories> getAllCategory(){
        return categorieDao.getAllCategory();
    }
    public List<Categories> getAllParentCategory(){
        return categorieDao.getAllParentCategory();
    }
    public Categories save(Categories c){
        return categorieDao.save(c);
    }
    public void update(Categories c){
        categorieDao.update(c);
    }

}
