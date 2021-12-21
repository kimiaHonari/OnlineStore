package ir.onlinestore.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by kimia on 1/2/2017.
 */

@Embeddable
public class GroupId implements Serializable {
    @ManyToOne
    private Orders order;
    @ManyToOne
    private Product product;

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupId that = (GroupId) o;

        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (order != null ? order.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}
