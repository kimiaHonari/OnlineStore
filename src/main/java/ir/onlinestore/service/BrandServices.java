package ir.onlinestore.service;

import ir.onlinestore.dao.BrandDao;
import ir.onlinestore.dao.CategorieDao;
import ir.onlinestore.model.Brand;
import ir.onlinestore.model.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kimia on 1/18/2017.
 */
@Service("brandservice")
@Transactional
public class BrandServices {
    @Autowired
    BrandDao brandDao;


    public Brand getById(long id){
        return brandDao.getById(id);
    }
    public List<Brand> getAllBrand(){
        return brandDao.getAllBrand();
    }

    public Brand save(Brand c){
        return brandDao.save(c);
    }
    public void update(Brand c) {
        brandDao.update(c);
    }
}
