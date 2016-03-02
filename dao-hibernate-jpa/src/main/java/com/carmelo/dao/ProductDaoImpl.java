package com.carmelo.dao;

import com.carmelo.ProductManager;
import com.carmelo.model.Product;
import javax.persistence.EntityManager;

/**
 *
 * @author Carmelo Marin Abrego
 */
public class ProductDaoImpl implements ProductDao {

    @Override
    public void insert(Product product) {
        EntityManager em = ProductManager.getEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Product product) {
        EntityManager em = ProductManager.getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = ProductManager.getEntityManager();
        Product product = em.find(Product.class, id);
        try {
            if (product != null) {
                em.getTransaction().begin();
                em.remove(product);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public Product read(Integer id) {
        EntityManager em = ProductManager.getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

}
