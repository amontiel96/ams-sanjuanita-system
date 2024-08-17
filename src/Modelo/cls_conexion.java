package Modelo;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * 
 */
public class cls_conexion {
    
    public Connection cnn;
    
    
    public void connectar(String host, String bd,String usuario,String pwd)
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            String dirBd= "jdbc:oracle:thin:@"+host+":1521:"+bd;
            cnn = DriverManager.getConnection(dirBd,usuario,pwd);
            JOptionPane.showMessageDialog(null, "Conectado");
        }catch(ClassNotFoundException | SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(null, "NO conectado"+e.getMessage());
        }
    }
    
}
