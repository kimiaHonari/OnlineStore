package ir.onlinestore.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

/**
 * Created by kimia on 1/2/2017.
 */

@Entity
public class Product implements Serializable{
    @Id
    private long id;
    private String Name;
    @Column(length = 300)
    private String Description;
    private double price;
    private String color;
    private int stock;
    private int off;
    private Date Created_at;
    private Date Update_at;
    private int state;
    @ManyToOne(fetch=FetchType.EAGER)
    private Categories category;
    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Images image;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.product", cascade=CascadeType.ALL)
    private Collection<OrderDetails> orderDetails;

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getOff() {
        return off;
    }

    public void setOff(int off) {
        this.off = off;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }



    public Images getImage() {
        return image;
    }

    public void setImage(Images image) {
        this.image = image;
    }

    public Collection<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Collection<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String Exist(){
        if (stock>0)
            return "Exist";
        else
            return "NotExist";
    }
}
