package ir.onlinestore.managebean;

import ir.onlinestore.authentication.SessionUtils;
import ir.onlinestore.model.Account;
import ir.onlinestore.model.AccountDetails;
import ir.onlinestore.service.UserServices;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by kimia on 1/21/2017.
 */
@ManagedBean(name="AccountManageBean")
@ViewScoped
public class AccountManageBean {
    @ManagedProperty("#{userservice}")
    UserServices userServices;

    private String email;
    private String FirstName;
    private String LastName;
    private String MobileNumber;
    private String TelephoneNumber;
    private String Country;
    private String City;
    private String address;
    private double Credit;
    Account account;
    AccountDetails details;
    public UserServices getUserServices() {
        return userServices;
    }

    public void setUserServices(UserServices userServices) {
        this.userServices = userServices;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public double getCredit() {
        return Credit;
    }

    public void setCredit(double credit) {
        Credit = credit;
    }

    @PostConstruct
    public void init(){
        HttpSession session = SessionUtils.getSession();
        String id=(String) session.getAttribute("username");
        account=userServices.getById(id);
        details=userServices.getDetails(account);
        email=account.getEmail();
        FirstName=account.getFirstName();
        LastName=account.getLastName();
        MobileNumber=details.getMobileNumber();
        TelephoneNumber=details.getTelephoneNumber();
        address=details.getAddress();
        City=details.getCity();
        Country=details.getCountry();
        Credit=details.getCredit();

    }

    public void EditInfo(){
        if(email.equals("") || FirstName.equals("") || LastName.equals("") ||
                MobileNumber.equals("") || TelephoneNumber.equals("") || Country.equals("")
                || City.equals("") || address.equals("") ){

        }
        else{
            account.setEmail(email);
            account.setFirstName(FirstName);
            account.setLastName(LastName);
            details.setMobileNumber(MobileNumber);
            details.setTelephoneNumber(TelephoneNumber);
            details.setAddress(address);
            details.setCity(City);
            details.setCountry(Country);
            details.setCredit(Credit);
            account.setAccountDetails(details);
            userServices.update(account);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                            "Information is edited successfully"));
        }
    }
}
