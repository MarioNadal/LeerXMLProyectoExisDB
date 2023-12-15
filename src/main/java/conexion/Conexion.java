package conexion;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.Centro;
import org.example.Centros;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;


public class Conexion {
    //clase
    public static void conexion() throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl";
        Collection collection = null;
        String URL = "xmldb:exist://localhost:8080/exist/xmlrpc/db/proyecto";
        String name = "admin";
        String password = "";
        try {
            Class cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);
            collection = DatabaseManager.getCollection(URL, name, password);
            if (collection == null) {
                System.out.println("La colleción no existe.");
            } else {
                System.out.println("Conexión OK.");try{
                    List<String> lineas = Files.readAllLines(Path.of("src/main/resources/CentrosCFGMyS.csv"));
                    lineas.remove(lineas.get(0));
                    ArrayList<Centro> centrosLista = new ArrayList<>();
                    Centros centros = new Centros();
                    for(String linea: lineas){
                        linea = linea.replaceAll("\"", "").replaceAll("\\[", "").replaceAll("\\]", "");
                        String[] centro1 = linea.split(",");

                            Centro centroAux = new Centro();

                            centroAux.setDistancia(centro1[0]);
                            centroAux.setProvincia(centro1[1]);
                            centroAux.setLocalidad(centro1[2]);
                            centroAux.setCodigo(centro1[3]);
                            centroAux.setDenomCorta(centro1[4]);
                            centroAux.setNombre(centro1[5]);
                            centroAux.setDenominacion(centro1[6]);
                            centroAux.setDireccion(centro1[7]);
                            centroAux.setNaturaleza(centro1[8]);
                            centroAux.setTelefono(centro1[9]);
                            centroAux.setCorreoElectronico(centro1[10]);
                            centroAux.setCodigoPostal(centro1[11]);

                            centrosLista.add(centroAux);
                    }
                    centros.setCentros(centrosLista);
                    try{
                        JAXBContext contexto = JAXBContext.newInstance(Centros.class);
                        Marshaller marshaller = contexto.createMarshaller();
                        marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);
                        marshaller.marshal(centros, new FileWriter("src/main/resources/centros.xml"));
                        System.out.println("XML Centros creado");
                    }catch(JAXBException ex){
                        System.out.println("La clase seleccionada no permite usar JAXB");
                    }
                } catch (PatternSyntaxException ex){
                    System.out.println("Error en la lectura del CSV");
                }
                File document = new File("src/main/resources/familias.xml");
                if(!document.canRead()){
                    System.out.println("Error al leer el documento XML.");
                }else{
                    Resource resource = collection.createResource(document.getName(), "XMLResource");
                    //comprobar si es un archivo
                    resource.setContent(document);
                    collection.storeResource(resource);
                }
                File document2 = new File("src/main/resources/proyectosFP.xml");
                if(!document2.canRead()){
                    System.out.println("Error al leer el documento XML.");
                }else{
                    Resource resource = collection.createResource(document2.getName(), "XMLResource");
                    //comprobar si es un archivo
                    resource.setContent(document2);
                    collection.storeResource(resource);
                }
                File document3 = new File("src/main/resources/centros.xml");
                if(!document3.canRead()){
                    System.out.println("Error al leer el documento XML.");
                }else{
                    Resource resource = collection.createResource(document3.getName(), "XMLResource");
                    //comprobar si es un archivo
                    resource.setContent(document3);
                    collection.storeResource(resource);
                }
                collection.close();
            }
        } catch (Exception ex) {
            System.out.println("Error en la conexión de la base de datos eXist " + ex.getMessage());
        }
    }
}
