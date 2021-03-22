package au.com.wesfarmers.dao;

import javax.persistence.Column;

public class Catalogs extends CSVEntity {
    //@Column(name="SKU")
    private String sku;
    //@Column(name="DESCRIPTION")
    private String description;

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public String getDescription() {
        return description;
    }
}
