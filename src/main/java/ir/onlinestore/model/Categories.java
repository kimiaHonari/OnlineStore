package ir.onlinestore.model;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

/**
 * Created by kimia on 1/2/2017.
 */
@Entity
public class Categories implements Serializable {
    @Id
    private long CategoryId;
    private String Name;
    @Column(length = 300)
    private String Description;

    private Date Created_at;
    private Date Update_at;
    private int state;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categories parentCategory;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "parentCategory")
    private Collection<Categories> ChildCategories;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    private Collection<Product> products;

    public long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(long categoryId) {
        CategoryId = categoryId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(Date created_at) {
        Created_at = created_at;
    }

    public Date getUpdate_at() {
        return Update_at;
    }

    public void setUpdate_at(Date update_at) {
        Update_at = update_at;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Categories getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Categories parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Collection<Categories> getChildCategories() {
        return ChildCategories;
    }

    public void setChildCategories(Collection<Categories> childCategories) {
        ChildCategories = childCategories;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
}
