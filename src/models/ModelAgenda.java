
/**
 *
 * @author Carina Amairani Díaz Ramírez
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;

    private String nombre;
    private String email;
    private String telefono;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que realiza las siguietnes acciones: 1- Conecta con la base
     * agenda_mvc, 2- Consulta todo los registros de la tabla contactos, 3-
     * Obtiene el nombre y el email y los guarda en las variables globales
     * nombre y email.
     */
    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/agenda_mvc","root","");
            st = conexion.createStatement();
            String sql = "SELECT * FROM contactos;";
            System.out.println(sql);
            rs = st.executeQuery(sql);
            rs.next();
            setValues();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }
    }

    /**
     * Lee los valores del registro seleccionado y los asigna a las variables
     * miembre nombre y email.
     */
    public void setValues() {
        try {
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            telefono = rs.getString("telefono");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error model 102: " + err.getMessage());

        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro() {
        System.out.println("moverPrimerRegistro");
        try {
            rs.first();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            telefono = rs.getString("telefono");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al siguiente
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverSiguienteRegistro() {
        System.out.println("moverSiguienteRegistro");
        try {
            if (!rs.isLast()) {
                rs.next();
                
                nombre = rs.getString("nombre");
                email = rs.getString("email");
                telefono = rs.getString("telefono");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al anterior
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverAnteriorRegistro() {
        System.out.println("moverAnteriorRegistro");
        try {
            if (!rs.isFirst()) {
                rs.previous();
                nombre = rs.getString("nombre");
                email = rs.getString("email");
                telefono = rs.getString("telefono");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la ariable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro() {
        System.out.println("moverUltimoRegistro");
        try {
            rs.last();
           nombre = rs.getString("nombre");
           email = rs.getString("email");
           telefono = rs.getString("telefono");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
    }
    
     /**
     * Método que realiza las siguiente acciones:
     * 1.- Inserta el nombre y email que estan en las cajas de texto a la tabla contactos
     * 2.- Evaluar si se almacenan los registros en la base de datos de no ser asi manda un error
     */
    public void Insertar(){
        System.out.print("Insertar registro");
        try {
           st.executeUpdate("Insert into contactos (nombre,email,telefono)"+" values ('"+ nombre +"','"+ email +"','"+ telefono +"');");
                    JOptionPane.showMessageDialog(null,"Registro guardado Existosamente");
            this.conectarDB();        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Modificar nombre y email del registro deseado
     * 2.- Evaluar si se modifican los datos, de no ser modificados manda un mensaje de error
     */
    public void Modificar(){
        System.out.print("Modificar registro");
        try {
           st.executeUpdate("UPDATE contactos SET email ='"+ email +"',telefono = '"+ telefono +"'  WHERE nombre= '"+ nombre +"';");

                    JOptionPane.showMessageDialog(null,"Registro modificado Existosamente");
            this.conectarDB();        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
        }
    }
    
     /**
     * Método que realiza las siguiente acciones:
     * 1.- Eliminar un registro
     * 2.- Evaluar si se elimina el contacto
     */
    public void Eliminar() {
        System.out.print("Programa accion eliminarRegistro");
         try {
             st.executeUpdate("delete from contactos WHERE nombre= '"+ nombre +"';");
                JOptionPane.showMessageDialog(null,"se elimino exitosamente");
               this.conectarDB();
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
         }
            
      
    }
}
