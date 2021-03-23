package au.com.wesfarmers.dao;


public class Barcodes extends CSVEntity {
    public Barcodes() {}

    public Barcodes(int supplierId, String sku, String barcode) {
        this.supplierId = supplierId;
        this.sku = sku;
        this.barcode = barcode;
    }
    private int supplierId;

    private String sku;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
        result = prime * result + ((sku == null) ? 0: sku.hashCode());
        result = prime * result + supplierId;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Barcodes other = (Barcodes) obj;
        if (barcode == null) {
            if (other.barcode != null)
                return false;
        } else if (!barcode.equals(other.barcode))
            return false;
        else if (!sku.equals(other.sku))
            return false;
        if (supplierId != other.supplierId)
            return false;
        return true;
    }
}
