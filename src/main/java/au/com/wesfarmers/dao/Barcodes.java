package au.com.wesfarmers.dao;

import javax.persistence.Column;
import javax.persistence.Id;

public class Barcodes extends CSVEntity {
//    @Id
//    @Column(name = "SUPPLIER_ID")
    private int supplierId;
//    @Id
//    @Column(name = "SKU")
    private String sku;
//    @Id
//    @Column(name = "BARCODE")
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
