package ir.onlinestore.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kimia on 1/2/2017.
 */
@Entity
public class Images implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String path;
    private String Name;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "image")
    private Product product;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
