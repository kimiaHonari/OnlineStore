package ir.onlinestore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by kimia on 1/2/2017.
 */
@Entity
public class Account implements Serializable {
    @Id
    private String email;
    private String FirstName;
    private String LastName;
    private String Password;
    private boolean isAdmin;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "account")
    private AccountDetails accountDetails;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "account")
    private Collection<Orders> orders;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    public Collection<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Orders> orders) {
        this.orders = orders;
    }
}
