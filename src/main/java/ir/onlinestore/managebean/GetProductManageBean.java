package ir.onlinestore.managebean;

import ir.onlinestore.model.Brand;
import ir.onlinestore.model.Product;
import ir.onlinestore.service.BrandServices;
import ir.onlinestore.service.CategorieServices;
import ir.onlinestore.service.ProductServices;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.flow.FlowScoped;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kimia on 1/19/2017.
 */
@ManagedBean(name="GetProduct")
@FlowScoped("flow")
public class GetProductManageBean implements Serializable {
    @ManagedProperty("#{productservice}")
    ProductServices productServices;
    @ManagedProperty("#{categorietservice}")
    CategorieServices categorieServices;
    @ManagedProperty("#{brandservice}")
    BrandServices brandServices;
    private String exist;
    private LazyDataModel<Product> lazyModel;
    private StreamedContent images;
    private Product selectedCar;
    private Product product;
    private String AddHidden;
    private String DeleteHidden;

    public String getAddHidden() {
        return AddHidden;
    }

    public void setAddHidden(String addHidden) {
        AddHidden = addHidden;
    }

    public String getDeleteHidden() {
        return DeleteHidden;
    }

    public void setDeleteHidden(String deleteHidden) {
        DeleteHidden = deleteHidden;
    }

    public void setImages(StreamedContent image) {
        this.images = image;
    }

    public StreamedContent getImages() {
        return images;
    }

    public Product getProduct() {
        System.out.println("product :" + product);
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }

    public CategorieServices getCategorieServices() {
        return categorieServices;
    }

    public void setCategorieServices(CategorieServices categorieServices) {
        this.categorieServices = categorieServices;
    }

    public ProductServices getProductServices() {
        return productServices;
    }

    public void setProductServices(ProductServices productServices) {
        this.productServices = productServices;
    }

    public BrandServices getBrandServices() {
        return brandServices;
    }

    public void setBrandServices(BrandServices brandServices) {
        this.brandServices = brandServices;
    }

    public LazyDataModel<Product> getLazyModel() {

        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Product> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Product getSelectedCar() {

        System.out.println("selectedCar" + selectedCar);
        return selectedCar;
    }

    public void setSelectedCar(Product selectedCar) {
        this.selectedCar = selectedCar;
    }

    @PostConstruct
    public void init() {
        System.out.println("in product init");
        lazyModel = new LazyProductModel(productServices.getAllProduct());


    }

    public void getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            images = new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
           /*System.out.println("image "+selectedCar.getImage().getName());
            String filename = context.getExternalContext().getRequestParameterMap().get(selectedCar.getImage().getName());
            System.out.println("filename :"+filename);*/
            images = new DefaultStreamedContent(new FileInputStream(new File("d:/course/web ghalami/FinalProject/images/", selectedCar.getImage().getName())));
        }
    }

    public String onRowSelect() {
        if (selectedCar.getStock() > 0) {
            exist = "Exist";
        } else
            exist = "Not Exist";
        product = selectedCar;
        System.out.println("onRowwSelect" + "selectedCar" + selectedCar);
        try {
            getImage();
            System.out.println("images: " + images);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (selectedCar != null)
            return "/OnlineStore/ProductDetails";
        return "";
    }

public void Sort(int i){
    if(i==1){
        lazyModel = new LazyProductModel(productServices.getAllProductAsc());
    }else{
        lazyModel = new LazyProductModel(productServices.getAllProductDesc());
    }
}

    public void Select(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Car Selected", String.valueOf(((Product) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


}
