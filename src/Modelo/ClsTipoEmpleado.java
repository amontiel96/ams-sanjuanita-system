/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Arturo
 */
public class ClsTipoEmpleado {
    private String id,tipo;
    MBD cnn=new MBD();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean guardarProveedor() 
    {
        String sql="INSERT INTO TBL_TIPO VALUES('"+ this.getId()+"','"+this.getTipo()+"')";
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
          String[] titulos={"CLAVE","TIPO"};
          String consulta;
          consulta = "select * from tbl_tipo";
          String[] columnas={"vch_id_tipo","vch_tipo"};
          dat= cnn.getDatos(consulta, columnas);
          dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
  }
    public boolean eliminar()
    {
        String sql="delete tbl_tipo where vch_id_tipo='"+this.getId()+"'";
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
        String sql="update tbl_tipo set vch_tipo='"+this.getTipo()+"' where vch_id_tipo='"+this.getId()+"'";
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
    
}
