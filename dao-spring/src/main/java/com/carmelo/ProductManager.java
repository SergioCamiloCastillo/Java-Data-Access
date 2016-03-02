package com.carmelo;

import com.carmelo.dao.ProductDao;
import com.carmelo.model.Product;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Carmelo Marin Abrego
 */
public class ProductManager {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DaoConfiguration.class);
        ProductDao pd = ctx.getBean(ProductDao.class);

        Product product = new Product(888, "Arroz Blanco", 10.55);
        pd.insert(product);
        printProduct(pd, product.getId());
        
        product.setName("Arroz Verde");
        product.setPrice(BigDecimal.valueOf(7.25));
        pd.update(product);
        printProduct(pd, product.getId());

        pd.delete(product.getId());
        System.out.println("\nDELET PODUCT ID = " + product.getId() + "\n");
    }

    private static void printProduct(ProductDao pd, Integer idProduct) {
        Product product = pd.read(idProduct);
        System.out.println("\nPRINT PODUCT :: " + product + "\n");
    }

}
