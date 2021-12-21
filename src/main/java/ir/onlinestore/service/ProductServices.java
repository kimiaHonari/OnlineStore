package ir.onlinestore.service;

import ir.onlinestore.dao.ProductDao;
import ir.onlinestore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kimia on 1/18/2017.
 */
@Service("productservice")
@Transactional
public class ProductServices {
    @Autowired
    ProductDao productDao;

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
    public Product gerProductById(long id){
        return productDao.getById(id);
    }
    public Product save(Product save){
        return productDao.save(save);
    }
    public List<Product> getAllProduct() {
    return productDao.getAllProduct();
    }
    public void update(Product p){
        productDao.update(p);
    }
    public List<Product> getAllProductAsc(){
        return productDao.getAllProductAsc();
    }
    public List<Product> getAllProductDesc(){
        return productDao.getAllProductDes();
    }
}
