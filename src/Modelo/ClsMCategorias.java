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
public class ClsMCategorias {
    private String id,nombre;
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
        String sql="insert into tblcategoria values ('"+ this.getId() +"','"+ this.getNombre() +"')";
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
          String[] titulos={"CLAVE","CATEGORIA"};
          String consulta;
          consulta = "select * from tblcategoria";
          String[] columnas={"VCH_ID_CATEGORIA","VCH_NOM_CATEGORIA",};
          dat= cnn.getDatos(consulta, columnas);
          dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
  }
    public boolean eliminar()
    {
        String sql="delete tblcategoria where vch_id_categoria='"+this.getId()+"'";
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
        String sql="update tblcategoria set vch_nom_categoria='"+this.getNombre()+"' where vch_id_categoria='"+this.getId()+"'";
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
