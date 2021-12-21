package ir.onlinestore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by kimia on 1/2/2017.
 */
@Entity
public class Brand implements Serializable {
    @Id
    private long BrandNumber;
    private String Name;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "brand")
    private Collection<Product> products;

    public long getBrandNumber() {
        return BrandNumber;
    }

    public void setBrandNumber(long brandNumber) {
        BrandNumber = brandNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
}
