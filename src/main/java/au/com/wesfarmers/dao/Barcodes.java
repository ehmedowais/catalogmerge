package au.com.wesfarmers.dao;

import javax.persistence.Column;

public class Barcodes extends CSVEntity {
    @Column(name = "SUPPLIER_ID")
    private int supplierId;
    @Column(name = "SKU")
    private String sku;
    @Column(name = "BARCODE")
    private String barcode;


    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


    public int getSupplierId() {
        return supplierId;
    }

    public String getSku() {
        return sku;
    }

    public String getBarcode() {
        return barcode;
    }
}
