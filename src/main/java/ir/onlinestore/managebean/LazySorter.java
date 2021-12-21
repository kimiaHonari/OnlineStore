package ir.onlinestore.managebean;

/**
 * Created by kimia on 1/19/2017.
 */
import java.util.Comparator;
import java.util.prefs.PreferenceChangeEvent;

import ir.onlinestore.model.Product;
import org.primefaces.model.SortOrder;


public class LazySorter implements Comparator<Product> {

    private String sortField;

    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(Product p1, Product p2) {
        try {
            Object value1 = Product.class.getField(this.sortField).get(p1);
            Object value2 = Product.class.getField(this.sortField).get(p2);

            int value = ((Comparable)value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
