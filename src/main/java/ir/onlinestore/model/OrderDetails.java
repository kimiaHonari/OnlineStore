package ir.onlinestore.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kimia on 1/2/2017.
 */
@Entity
@Table(name = "order_product")
@AssociationOverrides({
        @AssociationOverride(name = "pk.order",
                joinColumns = @JoinColumn(name = "ORDER_ID")),
        @AssociationOverride(name = "pk.product",
                joinColumns = @JoinColumn(name = "PRODUCT_ID")) })
public class OrderDetails implements Serializable{


    private int count;
    @EmbeddedId
    private GroupId pk = new GroupId();
    @Transient
    public Orders getOrder() {
        return getPk().getOrder();
    }

    public void setOrder(Orders order) {
        getPk().setOrder(order);
    }

    @Transient
    public Product getProduct() {
        return getPk().getProduct();
    }

    public void setProduct(Product product) {
        getPk().setProduct(product);
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public GroupId getPk() {
        return pk;
    }

    public void setPk(GroupId pk) {
        this.pk = pk;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        OrderDetails that = (OrderDetails) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }

}
