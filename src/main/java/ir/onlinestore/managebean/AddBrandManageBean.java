package ir.onlinestore.managebean;

import ir.onlinestore.model.Brand;
import ir.onlinestore.service.BrandServices;
import ir.onlinestore.service.CategorieServices;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.directory.SearchResult;
import java.io.Serializable;

/**
 * Created by kimia on 1/18/2017.
 */
@ManagedBean(name="AddBrand")
@ViewScoped
public class AddBrandManageBean implements Serializable{

    @ManagedProperty("#{brandservice}")
    BrandServices brandServices;

    private long BrandNumber;
    private String Name;

    public BrandServices getBrandServices() {
        return brandServices;
    }

    public void setBrandServices(BrandServices brandServices) {
        this.brandServices = brandServices;
    }



    public long getBrandNumber() {
        return BrandNumber;
    }

    public void setBrandNumber(long brandNumber) {
        BrandNumber = brandNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @PostConstruct
    public void init() {





    }
    public void AddBrands(){

        Brand check=brandServices.getById(BrandNumber);
        System.out.println(check);
        if(check==null){
            if (!(Name.equals("") && BrandNumber == 0)) {
                System.out.println("add brand");
                Brand newBrand=new Brand();
                newBrand.setName(Name);
                newBrand.setBrandNumber(BrandNumber);
                brandServices.save(newBrand);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Brand  added Successfully."));
            Name="";
            BrandNumber=0;
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Brand Id is used.choose another ID."));
        }

    }
}
