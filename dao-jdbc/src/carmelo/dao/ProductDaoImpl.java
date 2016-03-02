/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carmelo.dao;

import carmelo.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carmelo Marin Abrego
 */
public class ProductDaoImpl implements ProductDao {

    static final String JDBC_DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    static final String DB_URL = "jdbc:hsqldb:hsql://localhost/";
    static final String DB_USER = "SA";
    static final String DB_PASS = "";

    private void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Product product) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            try (Statement stmt = conn.createStatement()) {
                // enviar el comando insert
                stmt.executeUpdate("insert into product values ("
                        + product.getId() + ",'"
                        + product.getName() + "',"
                        + product.getPrice() + ");");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Product empleado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            try (Statement stmt = conn.createStatement()) {
                // enviar el comando delete
                stmt.executeUpdate("delete from product where id = " + id + ";");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
  }

    @Override
    public Product read(Integer id) {
        Connection conn = null;
        Product product = null;

        try {
            registerDriver();
            // abrir la conexion
            conn = DriverManager.getConnection(DB_URL);
            // consulta select (selecciona el producto con ID especificado)
            try (PreparedStatement ps = conn.prepareStatement(
                    "select * from product where id = ?")) {
                // indicar el ID que buscamos
                ps.setInt(1, id);
                // ejecutar la consulta
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // obtener cada una de la columnas y mapearlas a la clase Product
                        product = new Product(id,
                                rs.getString("name"),
                                rs.getDouble("price"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return product;
    }

}
