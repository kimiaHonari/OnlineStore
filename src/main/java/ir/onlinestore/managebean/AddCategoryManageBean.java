package ir.onlinestore.managebean;

import ir.onlinestore.model.Categories;
import ir.onlinestore.service.CategorieServices;
import ir.onlinestore.service.ProductServices;
import sun.security.krb5.internal.crypto.Des;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kimia on 1/18/2017.
 */
@ManagedBean(name="AddCategory")
@ViewScoped
public class AddCategoryManageBean implements Serializable {

    @ManagedProperty("#{categorietservice}")
    CategorieServices categorieServices;
    private long CategoryId;
    private String Name;
    private Map<String,String> categories = new HashMap<String, String>();
    private String Description;

    private int state;
    private long ParentCategory;



    private String selection;



    public Map<String, String> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, String> categories) {
        this.categories = categories;
    }

    public CategorieServices getCategorieServices() {
        return categorieServices;
    }

    public void setCategorieServices(CategorieServices categorieServices) {
        this.categorieServices = categorieServices;
    }

    public long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(long categoryId) {
        CategoryId = categoryId;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getParentCategory() {
        return ParentCategory;
    }

    public void setParentCategory(long parentCategory) {
        ParentCategory = parentCategory;
    }


   public void ListCategory(){
       List<Categories> categoryList=categorieServices.getAllParentCategory();
System.out.println("size : "+categoryList.size());
       categories = new HashMap<String, String>();
       for(int i=0;i<categoryList.size();i++){

           categories.put(String.valueOf(categoryList.get(i).getCategoryId()),String.valueOf(categoryList.get(i).getCategoryId()));
       }
   }
    @PostConstruct
    public void init() {

        ListCategory();



    }
    public String getSelection() {
        return selection;
    }
    public void setSelection(String selection) {
        this.selection = selection;
        System.out.println(selection);
    }
    public void AddCategories(){

        System.out.println(selection);
        Categories check=categorieServices.getById(CategoryId);
        if(check==null) {
            if (!(Name.equals("") && Description.equals(""))) {
                if (!selection.equals("")) {
                    ParentCategory = Long.parseLong(selection, 10);
                    Categories parentCategory = categorieServices.getById(ParentCategory);
                    Categories newCategory = new Categories();
                    newCategory.setCategoryId(CategoryId);
                    newCategory.setName(Name);
                    newCategory.setCreated_at(new java.util.Date());
                    newCategory.setDescription(Description);
                    newCategory.setState(state);
                    newCategory.setUpdate_at(new Date());
                    newCategory.setParentCategory(parentCategory);
                    categorieServices.save(newCategory);

                } else {
                    Categories newCategory = new Categories();
                    newCategory.setCategoryId(CategoryId);
                    newCategory.setName(Name);
                    newCategory.setCreated_at(new java.util.Date());
                    newCategory.setDescription(Description);
                    newCategory.setState(state);
                    newCategory.setParentCategory(categorieServices.getById(0));

                    newCategory.setUpdate_at(new Date());
                    categorieServices.save(newCategory);

                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Category  added Successfully."));
            }
            Name="";
            Description="";
            state=0;
            CategoryId=0;


        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Category Id is used.choose another ID."));

        }

    }

}
