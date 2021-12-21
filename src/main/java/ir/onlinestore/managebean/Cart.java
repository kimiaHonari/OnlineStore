package ir.onlinestore.managebean;

import ir.onlinestore.model.Product;

/**
 * Created by kimia on 1/20/2017.
 */
public class Cart {
    private Product product;
     int number;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public double Calculate(){
        return number*product.getPrice()*((100-product.getOff())/100);
    }
}
