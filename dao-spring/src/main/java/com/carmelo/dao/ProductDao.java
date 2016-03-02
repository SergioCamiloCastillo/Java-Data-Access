package com.carmelo.dao;

import com.carmelo.model.Product;

/**
 *
 * @author Carmelo Marin Abrego
 */
public interface ProductDao {
    public void insert(Product product);
    public void update(Product product);
    public void delete(Integer id);
    public Product read(Integer id);
}
