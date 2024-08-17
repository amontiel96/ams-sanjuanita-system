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
public class ClsMCliente {
     private String id,nombre,ap,am,direccion,email,fecha,telefono;
      String[][] datoscom ;
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

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public MBD getCnn() {
        return cnn;
    }

    public void setCnn(MBD cnn) {
        this.cnn = cnn;
    }
     public boolean guardarProveedor() 
    {
        String sql="begin sp_ins ('"+ this.getNombre() +"','"+ this.getAp() +"','"+this.getAm()+"','"+this.getTelefono()+"','"+this.getEmail()+"','"+this.getDireccion()+"','"+this.getId()+"'); commit; end;";
      System.out.println(sql);
     boolean band;
        try {            
            cnn.procesos(sql);            
            band=true;
        } catch (Exception ex) {
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
          String[] titulos={"nombre","ap","am","telefono","email","direccion","id","pw","tipo"};
          String consulta;
          consulta = "select * from tblempleado";
          String[] columnas={"vch_nombre","vch_app","vch_apm","vch_telefono","vch_email","vch_dir","vch_id_emp","pw","tipo"};
          dat= cnn.getDatos(consulta, columnas);
          dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
  }
     Object[][]busqueda;
     public DefaultTableModel llenarBusqueda(){
         DefaultTableModel dat=null;
         try{
          String[] titulos={"nombre","ap","am","telefono","email","direccion","id","pw","tipo"};
          String consulta;
          consulta = "select * from tblempleado";
          String[] columnas={"vch_nombre","vch_app","vch_apm","vch_telefono","vch_email","vch_dir","vch_id_emp","pw","tipo"};
          busqueda= cnn.getDatos(consulta, columnas);
          dat= new DefaultTableModel(busqueda,titulos);
         }catch(Exception e){
             
         }
             return dat;    
     }
      public String [][] LlenarCombo() 
    {
      String sql="select vch_tipo from tbltipo";
     System.out.println(sql);
     boolean band;
        try {            
          datoscom = cnn.LlenarCombos(sql);            
            band=true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            band=false;
        }
     return datoscom;
  }
    public boolean eliminar()
    {
        String sql="delete tblclientes where vch_id_cliente='"+this.getId()+"'";
        System.out.println(sql);
        boolean band;
        try
        {
            cnn.procesos(sql);
            band=true;
        }
        catch(Exception e)
        {
            band=false;
        }
        return band;
    }
  
    public boolean editar()
    {
        String sql="update tblclientes set vch_nom_cliente='"+this.getNombre()+"' where vch_id_categoria='"+this.getId()+"'";
        System.out.println(sql);
        boolean band;
        try
        {
            cnn.procesos(sql);
            band=true;
        }
        catch(Exception e)
        {
            band=false;
        }
        return band;
    }
    
}
