package au.com.wesfarmers.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(Barcodes.class)
@Table(name = "BARCODE_A")
public class BarcodesA extends Barcodes {
    @Id
    @Column(name = "SUPPLIER_ID")
    public int getSupplierId() {

        return super.getSupplierId();
    }
    @Id
    @Column(name = "SKU")
    public String getSku() {

        return super.getSku();
    }
    @Id
    @Column(name = "BARCODE")
    public String getBarcode() {

        return super.getBarcode();
    }

}
