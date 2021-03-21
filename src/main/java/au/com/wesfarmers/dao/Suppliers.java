package au.com.wesfarmers.dao;

import javax.persistence.Column;

public class Suppliers extends CSVEntity {
    @Column(name="ID")
    private int id;
    @Column(name="NAME")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
