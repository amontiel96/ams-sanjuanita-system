/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Arturo
 */
public class ClsM_Marca {
    private String id,nombre,buscar;

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }
     ResultSet resultado;
    MBD cnn=new MBD();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean guardarProveedor() 
    {
        String sql="begin sp_insertamarca ('"+ this.getId() +"','"+ this.getNombre() +"'); commit; end;";
      System.out.println(sql);
     boolean band;
        try {            
            cnn.procesos(sql);            
            band=true;
        } catch (Exception ex) {
JOptionPane.showMessageDialog(null, ex.getMessage());
            band=false;
        }
     return band;
  }
     Object[][] dat;
     public DefaultTableModel llenartabla()
  {
      DefaultTableModel dato=null;
      try
      {
          String[] titulos={"CLAVE","MARCA"};
          String consulta;
          consulta = "select * from tblmarca";
          String[] columnas={"VCH_ID_MARCA","VCH_NOM_MARCA",};
          dat= cnn.getDatos(consulta, columnas);
          dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
  }
    public boolean eliminar()
    {
        String sql="begin sp_eliminamar('"+this.getId()+"'); commit; end;";
        System.out.println(sql);
        boolean band;
        try
        {
            cnn.procesos(sql);
            band=true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            band=false;
        }
        return band;
    }
    public boolean editar()
    {
        String sql="begin sp_modificamar('"+this.getId()+"','"+this.getNombre()+"'); commit; end;";
        System.out.println(sql);
        boolean band;
        try
        {
            cnn.procesos(sql);
            band=true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            band=false;
        }
        return band;
    }
    public boolean buscar() 
    {
      boolean badera = false;
      try
      {
         resultado = cnn.consultar("select * from tblmarca");
         while(resultado.next())
         {
           if(this.getBuscar().equals(resultado.getObject("VCH_ID_MARCA")))
             {
                 badera=true;
             }
         }
         resultado.close();
      }
      catch(SQLException e)
      {
          JOptionPane.showMessageDialog(null,e.toString());
      }
      return badera;
  }
}
