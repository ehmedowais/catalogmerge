package au.com.wesfarmers.dao;


public class Catalogs extends CSVEntity {
    public Catalogs() {}
    public Catalogs(String sku,String description, String source) {
        super.setSource(source);
        this.sku = sku;
        this.description = description;
    }
    private String sku;
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

    @Override
    public String toString() {
        return sku+","+description+","+getSource();
    }
}
