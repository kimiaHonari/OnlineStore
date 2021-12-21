package ir.onlinestore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by kimia on 1/2/2017.
 */
@Entity
public class Orders implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long OrderId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    private double amount;

    private Date Created_at;
    private int state;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.order", cascade=CascadeType.ALL)
    private Collection<OrderDetails> orderDetails;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(Date created_at) {
        Created_at = created_at;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Collection<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Collection<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
