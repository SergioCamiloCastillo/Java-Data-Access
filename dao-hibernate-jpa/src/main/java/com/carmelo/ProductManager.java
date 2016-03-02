package com.carmelo;

import com.carmelo.dao.ProductDao;
import com.carmelo.dao.ProductDaoImpl;
import com.carmelo.model.Product;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Carmelo Marin Abrego
 */
public class ProductManager {

    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        
        ProductDao pd = new ProductDaoImpl();
        
        // obtener el producto con ID = 0
        Product product = pd.read(0);
        
        System.out.println(product);
        
        // incrementar 10 veces el precio del producto
        product.setPrice(product.getPrice().add(BigDecimal.valueOf(10.0)));
        // actualizar el producto
        pd.update(product);
        
        System.out.println(product);
        
        // agregar nuevo producto
        pd.insert(new Product(100, "Arroz", BigDecimal.valueOf(1.50)));   
        
        // eliminar el producto recien agregado
        pd.delete(100);
       
        closeEntityManagerFactory();
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("punit");
        }
        return emf.createEntityManager();
    }
    
    public static void closeEntityManagerFactory(){
        if(emf != null && emf.isOpen()){
            emf.close();
        }
    }

}
