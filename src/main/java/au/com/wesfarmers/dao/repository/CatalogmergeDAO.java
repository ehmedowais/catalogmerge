package au.com.wesfarmers.dao.repository;

import au.com.wesfarmers.dao.CSVEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CatalogmergeDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void addAllSuppliers(List<CSVEntity> listOfSuppliers) {
        for(CSVEntity entity:listOfSuppliers)
            entityManager.persist(entity);
    }
}
