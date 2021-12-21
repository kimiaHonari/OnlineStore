package ir.onlinestore.managebean;


import ir.onlinestore.model.Brand;
import ir.onlinestore.model.Categories;
import ir.onlinestore.model.Images;
import ir.onlinestore.model.Product;
import ir.onlinestore.service.BrandServices;
import ir.onlinestore.service.CategorieServices;
import ir.onlinestore.service.ProductServices;
import sun.security.krb5.internal.crypto.Des;

import javax.annotation.PostConstruct;
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
 * Created by kimia on 1/20/2017.
 */
@ManagedBean(name="EditProduct")
@ViewScoped
public class EditProductManageBean implements Serializable{
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
    private long SearchId;
    private String imagePath;

    public ProductServices getProductServices() {
        return productServices;
    }

    public void setProductServices(ProductServices productServices) {
        this.productServices = productServices;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Images getImages() {
        return images;
    }

    public long getSearchId() {
        return SearchId;
    }

    public void setSearchId(long searchId) {
        SearchId = searchId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImages(Images images) {
        this.images = images;
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
    public void Search() {
         if(SearchId!=0){
             Product search=productServices.gerProductById(SearchId);
             id=search.getId();
             Name=search.getName();
             Description=search.getDescription();
             price=search.getPrice();
              color=search.getColor();
              stock=search.getStock();
             off=search.getOff();
             state=search.getState();
             CategorySelection=String.valueOf(search.getCategory().getCategoryId());
             BrandSelection=String.valueOf(search.getBrand().getBrandNumber());
             imagePath=search.getImage().getPath();
         }
    }
    public void upload() {

        if(ImageUpload != null) {

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

            }
        }

    }

    public void EditProducts(){

        Product check=null;
        if(SearchId!=id){check=productServices.gerProductById(id);}
        if(check==null){
            if(Name.equals("")|| Description.equals("")||color.equals("")){


            }
            else{

                if(!CategorySelection.equals("")) {
                    Brand brand = null;
                    if (!BrandSelection.equals("")) {

                        brand = brandServices.getById(Long.parseLong(BrandSelection, 10));
                    }
                    Categories category=categorieServices.getById(Long.parseLong(CategorySelection, 10));
                    upload();
                    Product updateProduct=new Product();
                    updateProduct.setName(Name);
                    updateProduct.setUpdate_at(new Date());
                    updateProduct.setState(state);
                    updateProduct.setCategory(category);
                    updateProduct.setBrand(brand);
                    updateProduct.setColor(color);

                    updateProduct.setDescription(Description);
                    updateProduct.setId(id);
                    updateProduct.setOff(off);
                    updateProduct.setStock(stock);
                    updateProduct.setPrice(price);
                    updateProduct.setImage(images);
                    if(images==null)
                    {
                        //File file = new File("D:\\course\\web ghalami\\FinalProject\\images\\"+Name+".jpg");
                        File file = new File(imagePath);
                        file.delete();

                    }
                    productServices.update(updateProduct);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Product update Successfully."));

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
