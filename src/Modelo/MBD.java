/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Arturo
 */
public class MBD {
    public static String usuario;
    public static String pwd;
    public static String host;
    public static String bd;
    public static RandomAccessFile rf;
    ClsMDatosConexion dat=new ClsMDatosConexion();
    String url;
    public static Connection con; // conexion
    public Statement stmt;// se crea una sesion
    ResultSet rs;// se almacena los datos de una consulta
    public ResultSetMetaData rsMeta;// obtiene datos de las tablas de bd
    PreparedStatement pstm;// preparar sesiones
   
    public   MBD()
    {
               
    }
    
     public Connection getConnection()
    {
       try
        {
            
           rf = new RandomAccessFile("conexion.txt", "r");
           host=rf.readUTF();
           bd=rf.readUTF();
           usuario=rf.readUTF();
           pwd=rf.readUTF();
            
               
             Class.forName("oracle.jdbc.OracleDriver");// crargamos el driver la poder conecctarse al manejador de la base
            url="jdbc:oracle:thin:@"+host+":1521:"+bd;
             con =DriverManager.getConnection(url,usuario,pwd); //Agregamos el url del  servidor y el usuario y password para la conexion
            
            
       
        }
        catch(Exception ex)
        {
             JOptionPane.showMessageDialog(null,ex.toString());
           
        }
       return con;
    }
    
    public  boolean conectar()
    {
        
       
         try
        {
            
           rf = new RandomAccessFile("conexion.txt", "r");
           host=rf.readUTF();
           bd=rf.readUTF();
           usuario=rf.readUTF();
           pwd=rf.readUTF();
            
               
             Class.forName("oracle.jdbc.OracleDriver");// crargamos el driver la poder conecctarse al manejador de la base
            url="jdbc:oracle:thin:@"+host+":1521:"+bd;
             con =DriverManager.getConnection(url,usuario,pwd); //Agregamos el url del  servidor y el usuario y password para la conexion
            
            
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
       
        
    }
    public ResultSet consultar(String sql)//Hacer consultas
    {
        JOptionPane.showMessageDialog(null, sql);
       try {
           
            stmt =  con.createStatement(); ///creamos la  sesion para el proceso
            JOptionPane.showMessageDialog(null, "si lo hace");
            rs = stmt.executeQuery(sql);  ///se almacena Resulset... del proceso que se ejecutara
        }
       catch (SQLException ex)
       {
            JOptionPane.showMessageDialog(null, "ERROR CONSULTA " + ex.getMessage(), "DATAGRID", JOptionPane.INFORMATION_MESSAGE);
       }
         return rs; //se retorna el valor guardado en resultset
    }
      //Metodo para Insertar, Eliminar, Modificar y Ejecutar Procedimientos Almacenados
      public void procesos(String sql) throws Exception
    {
        try {
            stmt =  con.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex){
           System.out.println("Error de proceso: "+ ex);
        }     
    }
       //Metodo para llenar una tabla
    public Object [][] getDatos(String comand2,String[] colu) {
     
      Object[][] data= new Object[0][0];
          int filas=0;
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
          pstm =con.prepareStatement(comand2);
          rs = pstm.executeQuery();
         while(rs.next()){
             filas++;
         }
          rs.close();
          pstm = con.prepareStatement(comand2);
          rs =  pstm.executeQuery();
          
          data= new Object[filas][colu.length];
         int i = 0,y;
         while(rs.next()){
             for(y=0;y<colu.length;y++)
             {
                 data[i][y] = rs.getString(colu[y]);
             }
            i++;
         }
         rs.close();
         //return data;
          }catch(SQLException e){
              JOptionPane.showMessageDialog(null, e.getMessage());
    }
        return data;
 }   

 public ResultSet Consulta_SET(String consulta) throws ClassNotFoundException, SQLException 
    {  
            try 
            {
                Statement s = con.createStatement();
                rs = s.executeQuery(consulta);
            }
            catch (SQLException e) 
            {
                System.out.println("Error de MySQL: " + e.getMessage());
            } 
            catch (Exception e) 
            {
              System.out.println("Error inesperado: " + e.getMessage());	
            }
            return rs;
    }

    //Metodo para el llenado del combobox
    public String[][] LlenarCombos(String cons)
     {
         JOptionPane.showMessageDialog(null, "hola");
        String [][] datoscombo = new String [0][0];
        int rows=0, cols, f,c;
        try
        {
            
            
	    pstm =con.prepareStatement(cons);
            rs = pstm.executeQuery();
            while(rs.next())
            {
                     
                rows++;
            }
            rs.beforeFirst();
            rsMeta = rs.getMetaData();
            cols = rsMeta.getColumnCount();
            datoscombo = new String[rows][cols];
            f=0;
            while(rs.next())
            {
                for(c=0;c<cols;c++)
                {
                    datoscombo[f][c]=rs.getString(c+1);
                }
                f++;
            }
        }
        catch (SQLException e)
        {
        }
        return datoscombo;
    }
    public int ultimoId(String consulta) throws ClassNotFoundException, SQLException{
    int id=0;
    try 
    {
        stmt =  con.createStatement();
        int registro=0;
        rs =  stmt.executeQuery(consulta);
                
        while(rs.next())
        {
        registro++;
        }
        rs.close();
        rs =  stmt.executeQuery(consulta);
               
        int i=0;
        while(rs.next())
        {
            id=Integer.parseInt(rs.getString(1));
            i++;
        }
              
        } catch (SQLException | NullPointerException ex) {
        System.out.println(ex.getMessage());
           
        }
        catch (NumberFormatException e) {
        }
        return id;
     }
}
