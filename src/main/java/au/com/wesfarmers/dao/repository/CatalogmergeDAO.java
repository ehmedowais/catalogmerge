package au.com.wesfarmers.dao.repository;

import au.com.wesfarmers.dao.Barcodes;
import au.com.wesfarmers.dao.BarcodesA;
import au.com.wesfarmers.dao.BarcodesB;
import au.com.wesfarmers.dao.CSVEntity;
import au.com.wesfarmers.dao.CatalogA;
import au.com.wesfarmers.dao.CatalogB;
import au.com.wesfarmers.dao.Catalogs;
import au.com.wesfarmers.dao.Suppliers;
import au.com.wesfarmers.dao.SuppliersA;
import au.com.wesfarmers.dao.SuppliersB;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;



@Transactional
@Repository
public class CatalogmergeDAO {
    private final static Logger LOGGER = Logger.getLogger(CatalogmergeDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    public void addAll(List<CSVEntity> listOfSuppliers) {
        for(CSVEntity entity:listOfSuppliers) {
            if(!isRecordExist(entity)) {
                entityManager.persist(entity);
            }
        }
    }

    public List<Catalogs> getCatalogs(String source) {
        String hql = "FROM Catalog"+source.toUpperCase();
        List<Catalogs> catalogs = new ArrayList<>();
        try {
            catalogs = entityManager.createQuery(hql).getResultList();
        }catch(Exception ex){
            LOGGER.error(ex.getMessage(),ex);
        }
        return catalogs;

    }
    public List<Catalogs> getFinalResult() {
        String query=   "SELECT CatA.SKU, CatA.DESCRIPTION, 'A' SOURCE FROM CATALOG_A CatA  " +
                        "UNION "+
                        "SELECT CatB.SKU, CatB.DESCRIPTION, 'B' SOURCE FROM CATALOG_B CatB  " +
                           "WHERE CatB.SKU NOT IN( " +
                               "SELECT DISTINCT B.SKU FROM BARCODE_B B " +
                               "INNER JOIN BARCODE_A A "+
                               "ON A.SUPPLIER_ID = B.SUPPLIER_ID "+
                               "AND A.BARCODE = B.BARCODE) ORDER BY 2 ASC ";
         List<Object[]> objects = entityManager.createNativeQuery(query).getResultList();
         List<Catalogs> mergedCatalog = new ArrayList();
         for(Object[] object: objects) {
            mergedCatalog.add(new Catalogs(object[0].toString(),object[1].toString(),object[2].toString()));
         }

        return mergedCatalog;

    }
    public boolean isRecordExist(CSVEntity entity) {
        boolean isExists = false;
        if(entity != null && (entity instanceof BarcodesA | entity instanceof BarcodesB))
            isExists = (null != entityManager.find(entity.getClass(), (Barcodes)entity)) ? true: false;
        if(entity != null && (entity instanceof SuppliersA | entity instanceof SuppliersB))
            isExists = (null != entityManager.find(entity.getClass(), ((Suppliers) entity).getId())) ? true : false;
        if(entity != null && (entity instanceof CatalogA | entity instanceof CatalogB))
            isExists = (null != entityManager.find(entity.getClass(), ((Catalogs) entity).getSku())) ? true : false;
         return isExists;
    }
}
