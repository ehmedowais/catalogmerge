package au.com.wesfarmers.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUPPLIERS_B")
public class SuppliersB extends Suppliers {
    @Id
    @Column(name="ID")
    public int getId() {
        return super.getId();
    }

    @Column(name="NAME")
    public String getName() {
        return super.getName();
    }
}
