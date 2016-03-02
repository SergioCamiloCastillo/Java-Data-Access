package carmelo;

import carmelo.dao.ProductDao;
import carmelo.dao.ProductDaoImpl;
import carmelo.model.Product;

/**
 *
 * @author Carmelo Marin Abrego
 */
public class ProductManager {
    public static void main(String[] args) {
        ProductDao product = new ProductDaoImpl();
        
        // agregar nuevo producto
        product.insert(new Product(100, "Arroz", 1.50));
        
        // obtener el producto con ID = 100 e imprimirlo en pantalla
        Product p = product.read(100);
        System.out.println(p);
        
        // eliminar el producto con ID = 100
        product.delete(100);
    }
}
