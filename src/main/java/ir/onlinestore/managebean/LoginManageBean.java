package ir.onlinestore.managebean;

import ir.onlinestore.authentication.SessionUtils;
import ir.onlinestore.model.Account;
import ir.onlinestore.model.Product;
import ir.onlinestore.service.UserServices;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 * Created by kimia on 1/4/2017.
 */
@ManagedBean(name="LoginManageBean")
@SessionScoped
public class LoginManageBean implements Serializable{
    @ManagedProperty("#{userservice}")
    UserServices userServices;

    private List<Cart> CardList;
    private  static final long serialVersionUID = 1094801825228386363L;

    private String pwd="";
    private String msg;
    private String user="";
    private Boolean LoggedIn;
    private Boolean LoggedOut;
    private Boolean access;
private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Cart> getCardList() {
        return CardList;
    }

    public void setCardList(List<Cart> cardList) {
        CardList = cardList;
    }

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    public Boolean getLoggedOut() {
        return LoggedOut;
    }

    public void setLoggedOut(Boolean loggedOut) {
        LoggedOut = loggedOut;
    }

    public Boolean getLoggedIn() {
        return LoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        LoggedIn = loggedIn;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    //validate login
    public String validateUsernamePassword() {
        Account account =userServices.validate(user,pwd);
        if (account!=null) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            session.setAttribute("access",account.isAdmin());
            LoggedIn=true;
            LoggedOut=false;

            if(account.isAdmin()){
                access=true;
                System.out.println("manage");
                return "/OnlineStore/Manage/Product?faces-redirect=true";
            }
            else {access=false;

            return "/OnlineStore/Home?faces-redirect=true";}
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "";
        }
    }

    public UserServices getUserServices() {
        return userServices;
    }

    public void setUserServices(UserServices userServices) {
        this.userServices = userServices;
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        LoggedIn=false;
        LoggedOut=true;
        access=false;
        return "/OnlineStore/Home?faces-redirect=true";
    }
    @PostConstruct
    public void init(){
        System.out.println("login init");
      CardList=new ArrayList<Cart>();
        LoggedIn=false;
        LoggedOut=true;
        access=false;

    }
    public Boolean ExistInCard(long id){
        Boolean flag=false;
        System.out.println("before add size"+CardList.size()+"long :"+id);
        for(int i=0;i<CardList.size();i++){
            if(CardList.get(i).getProduct().getId()==id) {
                flag = true;
                break;
            }
        }
        return flag;
    }



    public void AddCart(Product p){
        Boolean flag=false;
        if(p!=null) {
            Cart cart = new Cart();
            System.out.println("adding card :" + p);
            for (int i = 0; i < CardList.size(); i++) {
                if (CardList.get(i).getProduct().getId() == p.getId()) {
                    flag = true;
                    if(CardList.get(i).getNumber()==CardList.get(i).getProduct().getStock()){
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "you cant order higher than "+CardList.get(i).getNumber()+"."));
                    }else{
                    CardList.get(i).setNumber(CardList.get(i).getNumber() + 1);
                        System.out.println(  CardList.get(i).getNumber());
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Product add to your Carts Successfully."));
                    }
                    break;
                }
            }
            if (!flag) {
                cart.setProduct(p);
                cart.setNumber(1);
                CardList.add(cart);
                System.out.println("size :" + CardList.size() +"numbre "+ cart.getNumber());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Product add to your Carts Successfully."));
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Please select Product."));
        }

    }
    public void DeleteCart(long id){
        Boolean flag=false;
        for(int i=0;i<CardList.size();i++){
            if(CardList.get(i).getProduct().getId()==id) {
                flag=true;
                CardList.remove(CardList.get(i));
                break;
            }
        }
        if(flag){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Product delete from your Carts Successfully."));
        }

        System.out.println("size after delete :"+CardList.size());
    }
    public String Confirm(){
        if(LoggedIn){
            Boolean flag=false;
            for(int i=0;i<CardList.size();i++){
               total+=CardList.get(i).Calculate();
                if(CardList.get(i).getProduct().getStock()<CardList.get(i).number)
                {
                    flag=true;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Product with id : "+CardList.get(i).getProduct().getId()+" doesn't exist in "+CardList.get(i).number+" number."));

                break;
                }
            }
            if(!flag)
            return "/OnlineStore/User/Payment.xhtml?faces-redirect=true";
            else
                return "";
        }else{
            return "/OnlineStore/Login.xhtml?faces-redirect=true";
        }
    }

    public String Pay(){
        HttpSession session = SessionUtils.getSession();
        String id=(String) session.getAttribute("username");
        System.out.println("credit : "+userServices.getCreditById(id));
        if(total>userServices.getCreditById(id)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "your credit is not enough"));

        }
        else{

        }
        return "";
    }
}

