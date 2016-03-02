package com.carmelo.dao;

import com.carmelo.model.Product;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carmelo Marin Abrego
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Product product) {
        em.persist(product);
    }

    @Override
    @Transactional
    public void update(Product product) {
        em.merge(product);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Product read(Integer id) {
        return em.find(Product.class, id);
    }

}
