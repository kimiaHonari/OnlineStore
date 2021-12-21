package ir.onlinestore.managebean;

import ir.onlinestore.model.Categories;
import ir.onlinestore.service.CategorieServices;

import javax.annotation.PostConstruct;
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
@ManagedBean(name="EditCategory")
@ViewScoped
public class EditCategoryManageBean implements Serializable {
    @ManagedProperty("#{categorietservice}")
    CategorieServices categorieServices;
    private long CategoryId;
    private long CategoryIdSearch;
    private String Name;
    private Map<String,String> categories = new HashMap<String, String>();
    private String Description;

    private int state;
    private long ParentCategory;

    public long getCategoryIdSearch() {
        return CategoryIdSearch;
    }

    public void setCategoryIdSearch(long categoryIdSearch) {
        CategoryIdSearch = categoryIdSearch;
    }

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

            categories.put(String.valueOf(categoryList.get(0).getCategoryId()),String.valueOf(categoryList.get(0).getCategoryId()));
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
    public void Search(){

           if (CategoryIdSearch!=0){
               Categories search=categorieServices.getById(CategoryIdSearch);
               CategoryId=CategoryIdSearch;
               Name=search.getName();
               Description=search.getDescription();
               state=search.getState();
               long l=search.getParentCategory().getCategoryId();
               if (l!=0)
               selection=String.valueOf(l);
               ListCategory();
           }
    }
    public void EditCategory(){

        Categories check=null;
        if(CategoryId!=CategoryIdSearch){
             check=categorieServices.getById(CategoryId);}
        if(check==null) {
            if (!(Name.equals("") && Description.equals(""))) {
                if (!selection.equals("")) {
                    ParentCategory = Long.parseLong(selection, 10);
                    Categories parentCategory = categorieServices.getById(ParentCategory);
                    Categories newCategory = new Categories();
                    newCategory.setCategoryId(CategoryId);
                    newCategory.setName(Name);

                    newCategory.setDescription(Description);
                    newCategory.setState(state);
                    newCategory.setUpdate_at(new Date());
                    newCategory.setParentCategory(parentCategory);
                    categorieServices.update(newCategory);

                } else {
                    Categories newCategory = new Categories();
                    newCategory.setCategoryId(CategoryId);
                    newCategory.setName(Name);

                    newCategory.setDescription(Description);
                    newCategory.setState(state);
                    newCategory.setParentCategory(categorieServices.getById(0));
                    System.out.println(newCategory.getParentCategory().getCategoryId());
                    newCategory.setUpdate_at(new Date());
                    categorieServices.update(newCategory);

                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Category  update Successfully."));

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
