package au.com.wesfarmers.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CATALOG_B")
public class CatalogB extends Catalogs {
    @Id
    @Column(name="SKU")
    public String getSku() {

        return super.getSku();
    }
    @Column(name="DESCRIPTION")
    public String getDescription() {

        return super.getDescription();
    }
}
