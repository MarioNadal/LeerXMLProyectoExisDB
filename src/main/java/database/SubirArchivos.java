package database;

import conexion.Conexion;
import org.xmldb.api.base.XMLDBException;

import java.sql.Connection;
import java.sql.SQLException;

public class SubirArchivos {
    public static void subir() {
        try{
            Conexion.conexion();
        }catch (XMLDBException ex){
            System.out.println("Error en la conexion");
        }
    }
}
