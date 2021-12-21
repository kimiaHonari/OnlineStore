package ir.onlinestore.managebean;

import ir.onlinestore.model.Account;
import ir.onlinestore.model.AccountDetails;
import ir.onlinestore.service.UserServices;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 * Created by kimia on 1/17/2017.
 */
@ManagedBean(name="Register")
@RequestScoped
public class RegisterManageBean {

    @ManagedProperty("#{userservice}")
    UserServices userServices;

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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
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

    private String email;
    private String FirstName;
    private String LastName;
    private String Password;
    private String ConfirmPassword;
    private String MobileNumber;
    private String TelephoneNumber;
    private String Country;
    private String City;
    private String address;




    @PostConstruct
    public void init(){};

    public String register(){
        if(email.equals("") || FirstName.equals("") || LastName.equals("") ||
                MobileNumber.equals("") || TelephoneNumber.equals("") || Country.equals("")
                || City.equals("") || address.equals("") ){

        }
        else{
            if(Password.equals(ConfirmPassword)){
                Account account=new Account();
                account.setIsAdmin(false);
                account.setPassword(Password);
                account.setEmail(email);
                account.setFirstName(FirstName);
                account.setLastName(LastName);
                AccountDetails details=new AccountDetails();
                details.setState(1);
                details.setAddress(address);
                details.setCity(City);
                details.setCountry(Country);
                details.setTelephoneNumber(TelephoneNumber);
                details.setMobileNumber(MobileNumber);
                details.setCredit(100.00);
                details.setAccount(account);
                Boolean success=userServices.Register(account,details);
                if(success){
                    FirstName="";LastName="";email="";City="";Country="";address="";TelephoneNumber="";MobileNumber="";
                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                                    "Account register successfully"));
                }
            }else{

            }
        }
        return "";
    }
}
