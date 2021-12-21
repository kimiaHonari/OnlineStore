package ir.onlinestore.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kimia on 1/2/2017.
 */
@Entity
public class AccountDetails implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    @OneToOne(fetch = FetchType.EAGER)
    private Account account;
    private String MobileNumber;
    private String TelephoneNumber;
    private String Country;
    private String City;
    private String address;
    private int state;
    private double Credit;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getTelephoneNumber() {
        return TelephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        TelephoneNumber = telephoneNumber;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getCredit() {
        return Credit;
    }

    public void setCredit(double credit) {
        Credit = credit;
    }
}
