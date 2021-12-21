package ir.onlinestore.managebean;

import ir.onlinestore.model.Brand;
import ir.onlinestore.model.Categories;
import ir.onlinestore.model.Images;
import ir.onlinestore.model.Product;
import ir.onlinestore.service.BrandServices;
import ir.onlinestore.service.CategorieServices;
import ir.onlinestore.service.ProductServices;
import ir.onlinestore.service.UserServices;
import sun.security.krb5.internal.crypto.Des;

import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import java.io.*;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kimia on 1/18/2017.
 */
@ManagedBean(name="AddProduct")
@ViewScoped
public class AddProductManageBean implements Serializable{
    @ManagedProperty("#{productservice}")
    ProductServices productServices;
    @ManagedProperty("#{categorietservice}")
    CategorieServices categorieServices;
    @ManagedProperty("#{brandservice}")
    BrandServices brandServices;
    private long id;
    private Part ImageUpload;
    private String Name;
    private String Description;
    private double price;
    private String color;
    private int stock;
    private int off;
    private int state;
    private Map<String,String> categories = new HashMap<String, String>();
    private String CategorySelection;
    private Map<String,String> brands = new HashMap<String, String>();
    private String BrandSelection;
    private Images images;
    public ProductServices getProductServices() {
        return productServices;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategorieServices getCategorieServices() {
        return categorieServices;
    }

    public void setCategorieServices(CategorieServices categorieServices) {
        this.categorieServices = categorieServices;
    }

    public BrandServices getBrandServices() {
        return brandServices;
    }

    public void setBrandServices(BrandServices brandServices) {
        this.brandServices = brandServices;
    }

    public Map<String, String> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, String> categories) {
        this.categories = categories;
    }

    public String getCategorySelection() {
        return CategorySelection;
    }

    public void setCategorySelection(String categorySelection) {
        CategorySelection = categorySelection;
    }

    public Map<String, String> getBrands() {
        return brands;
    }

    public void setBrands(Map<String, String> brands) {
        this.brands = brands;
    }

    public String getBrandSelection() {
        return BrandSelection;
    }

    public void setBrandSelection(String brandSelection) {
        BrandSelection = brandSelection;
    }

    public void setProductServices(ProductServices productServices) {
        this.productServices = productServices;
    }

    public Part getImageUpload() {
        return ImageUpload;
    }

    public void setImageUpload(Part imageUpload) {
        ImageUpload = imageUpload;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getOff() {
        return off;
    }

    public void setOff(int off) {
        this.off = off;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void ListCategory(){
        List<Categories> categoryList=categorieServices.getAllParentCategory();
        System.out.println("size category: "+categoryList.size());
        categories = new HashMap<String, String>();
        for(int i=0;i<categoryList.size();i++){

            categories.put(String.valueOf(categoryList.get(i).getCategoryId()),String.valueOf(categoryList.get(i).getCategoryId()));
        }
    }
    public void ListBrand(){
        List<Brand> brandList=brandServices.getAllBrand();
        System.out.println("size category: "+brands.size());
        brands = new HashMap<String, String>();
        for(int i=0;i<brandList.size();i++){

            brands.put(String.valueOf(brandList.get(i).getBrandNumber()),String.valueOf(brandList.get(i).getBrandNumber()));
        }
    }
    @PostConstruct
    public void init(){
        ListCategory();
        ListBrand();
        images=null;

    }


    public void upload() {
        System.out.println("in upload");
        if(ImageUpload != null) {
            System.out.println("image not null");
            try {
                InputStream input = ImageUpload.getInputStream();
                System.out.println(ImageUpload.getSubmittedFileName());
                File f=new File("d:/course/web ghalami/FinalProject/images/", ImageUpload.getSubmittedFileName());
                f.createNewFile();

                FileOutputStream out=new FileOutputStream(f);
                byte[] Buffer=new byte[1024];
                int length;
                while ((length=input.read(Buffer))>0){
                    out.write(Buffer,0,length);
                }
                out.close();
                input.close();
                images=new Images();
                images.setPath(f.getPath());
                images.setName(ImageUpload.getSubmittedFileName());
            }
            catch (IOException e) {
                // Show faces message?
            }
        }
        else{
            System.out.println("image null");
        }
    }


    public void AddProducts(){
        System.out.println("Add product");
        Product check=null;
        if(id!=0){check=productServices.gerProductById(id);}
        if(check==null){
            if(Name.equals("")|| Description.equals("")||color.equals("")){


            }
            else{
                System.out.println("category selection"+CategorySelection);
                if(!CategorySelection.equals("")) {
                    Brand brand = null;
                    if (!BrandSelection.equals("")) {

                        brand = brandServices.getById(Long.parseLong(BrandSelection, 10));
                    }
                    Categories category=categorieServices.getById(Long.parseLong(CategorySelection, 10));
                    upload();
                    Product newProduct=new Product();
                    newProduct.setName(Name);
                    newProduct.setUpdate_at(new Date());
                    newProduct.setState(state);
                    newProduct.setCategory(category);
                    newProduct.setBrand(brand);
                    newProduct.setColor(color);
                    newProduct.setCreated_at(new Date());
                    newProduct.setDescription(Description);
                    newProduct.setId(id);
                    newProduct.setOff(off);
                    newProduct.setStock(stock);
                    newProduct.setPrice(price);
                    newProduct.setImage(images);
                    if(images!=null)
                    {
                        images.setProduct(newProduct);
                    }
                    productServices.save(newProduct);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Product added Successfully."));

                    Name="";
                    state=0;
                    price=0.0;
                    Description="";
                    off=0;
                    color="";
                    stock=0;
                    id=0;
                    CategorySelection="";
                    BrandSelection="";

                }
                else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Category cant be null."));

                }
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Product Id is used.choose another ID."));

        }

    }
}
