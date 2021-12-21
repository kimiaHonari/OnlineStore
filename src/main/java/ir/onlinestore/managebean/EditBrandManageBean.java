package ir.onlinestore.managebean;

import ir.onlinestore.model.Brand;
import ir.onlinestore.model.Categories;
import ir.onlinestore.service.BrandServices;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
/**
 * Created by kimia on 1/19/2017.
 */
@ManagedBean(name="EditBrand")
@ViewScoped
public class EditBrandManageBean implements Serializable{
    @ManagedProperty("#{brandservice}")
    BrandServices brandServices;

    private long BrandNumber;
    private long BrandNumberSearch;
    private String Name;

    public long getBrandNumberSearch() {
        return BrandNumberSearch;
    }

    public void setBrandNumberSearch(long brandNumberSearch) {
        BrandNumberSearch = brandNumberSearch;
    }

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
System.out.println("brand init");
       // RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Echoes in eternity."));
       /* FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful",  "Your message: " + "hi") );
        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));*/


    }
    public void Search(){


        System.out.println("search function");
        if (BrandNumberSearch!=0){
            Brand search=brandServices.getById(BrandNumberSearch);
            BrandNumber=BrandNumberSearch;
            Name=search.getName();



            /*context.execute("PF('dlg1').show();");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "Message body");
            RequestContext.getCurrentInstance().showMessageInDialog(message)*/
        }
    }
    public void EditBrands(){

        Brand check=null;
        System.out.println("brand numbres "+BrandNumber+" "+BrandNumberSearch);
        if(BrandNumber!=BrandNumberSearch)
           check=brandServices.getById(BrandNumber);
        if(check==null){
            if (!(Name.equals("") && BrandNumber != 0)) {
                Brand newBrand=new Brand();
                newBrand.setName(Name);
                newBrand.setBrandNumber(BrandNumber);
                brandServices.update(newBrand);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Brand  update Successfully."));

            Name="";
            BrandNumber=0;
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Brand Id is used.choose another ID."));
        }

    }


}
