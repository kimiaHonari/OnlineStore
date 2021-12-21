package ir.onlinestore.managebean;

import ir.onlinestore.model.Product;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.*;

/**
 * Created by kimia on 1/19/2017.
 */
public class LazyProductModel extends LazyDataModel<Product> {
    private List<Product> products;
    public LazyProductModel(List<Product> datasource){
        products=datasource;
    }
    @Override
    public Product getRowData(String rowKey) {
        for(Product product :products) {
            if(String.valueOf(product.getId()).equals(rowKey))
                return product;
        }

        return null;
    }
    @Override
    public Object getRowKey(Product product) {
        return String.valueOf(product.getId());
    }

    @Override
    public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Product> data = new ArrayList<Product>();

        //filter
        boolean match;
        match=true;
        for(Product product : products) {


            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                match = false;
                if(entry.getKey().equals("getId()")){
                    if(String.valueOf(product.getId()).contains(entry.getValue().toString())){
                        match=true;
                    }
                }
                else if(entry.getKey().equals("getCategory().getName()")){
                    if(product.getCategory().getName().contains(entry.getValue().toString())){
                        match=true;
                    }
                }
                else if(entry.getKey().equals("getBrand().getName()")){
                    if(product.getBrand().getName().contains(entry.getValue().toString())){
                        match=true;
                    }
                }
                else if(entry.getKey().equals("getName()")){
                    if(product.getBrand().getName().contains(entry.getValue().toString())){
                        match=true;
                    }
                }
                else if(entry.getKey().equals("getPrice()")){
                    if(String.valueOf(product.getPrice()).contains(entry.getValue().toString())){
                    match=true;
                    }
                }
                else if(entry.getKey().equals("Exist()")) {
                    if (product.Exist().equals(entry.getValue().toString())) {
                        match = true;
                    }
                }
                else if(entry.getKey().equals("getStock()")) {
                    if (String.valueOf(product.getStock()).equals(entry.getValue().toString())) {
                        match = true;
                    }
                }
                else
                    match=false;
            }

            if(match) {
                data.add(product);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }

}
