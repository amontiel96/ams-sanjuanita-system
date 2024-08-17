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
public class ClsMProveedor {
    private String id,direccion,telefono,cp,email,nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MBD getCnn() {
        return cnn;
    }

    public void setCnn(MBD cnn) {
        this.cnn = cnn;
    }

    public Object[][] getDat() {
        return dat;
    }

    public void setDat(Object[][] dat) {
        this.dat = dat;
    }
    MBD cnn=new MBD();
    
       public boolean guardarProveedor() 
    {
        String sql="insert into tblproveedor values ('"+ this.getId() +"','"+ this.getNombre() +"','"+this.getCp()+"','"+this.getDireccion()+"','"+this.getTelefono()+"','"+this.getEmail()+"')";
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
    public boolean eliminar()
    {
        String sql="delete tblproveedor where vch_id_proveedor='"+this.getId()+"'";
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
        String sql="update tblproveedor set vch_direccion='"+this.getDireccion()+"', vch_tel='"+this.getTelefono()+"' where vch_id_proveedor='"+this.getId()+"'";
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
     Object[][] dat;
     public DefaultTableModel llenartabla()
  {
      DefaultTableModel dato=null;
      try
      {
          String[] titulos={"CLAVE","NOMBRE","APP","APM","TELEFONO","EMAIL"};
          String consulta;
          consulta = "select * from tblproveedor";
          String[] columnas={"VCH_ID_PROVEEDOR","VCH_NOMBRE_PRO","VCH_COD_POS","VCH_DIRECCION","VCH_TEL","VCH_EMAIL"};
          dat= cnn.getDatos(consulta, columnas);
          dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
  }
    
}
