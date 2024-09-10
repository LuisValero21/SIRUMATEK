package com.example.sirumatek.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckMySQL {

    // Información de la conexión a la base de datos
    private static final String HOSTNAME = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DATABASE_NAME = "SIRUMATEK";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "basededatos1$";

    // URL de conexión
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME;

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Intentar la conexión
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos: " + DATABASE_NAME);

            // Ejemplo: Crear un Statement para ejecutar una consulta
            Statement statement = connection.createStatement();

            // Puedes añadir aquí las operaciones SQL que necesites
            // Ejemplo: statement.execute("YOUR SQL QUERY");

        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Conexión cerrada.");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
}
