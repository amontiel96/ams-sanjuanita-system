/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.JIFrmEmpleado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Arturo
 */
public class ClsMEmpleado {
    private String id,nombre,ap,am,direccion,email,telefono,pwd,tipo;
     String[][] datoscom ;
    ResultSet resultado;
    JIFrmEmpleado empleado;
    public ClsMEmpleado(JIFrmEmpleado _empleado){
        this.empleado=_empleado;
    }
    MBD cnn=new MBD();
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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

   

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    
public void CargarCombos() throws ClassNotFoundException
   {
        try {
            DefaultComboBoxModel modeloCombo1 = new DefaultComboBoxModel();
            resultado= cnn.Consulta_SET("SELECT * FROM TBL_TIPO");
            while (resultado.next())
            {  
                modeloCombo1.addElement(resultado.getObject("VCH_TIPO"));     
            }      
            this.empleado.jcmbTipo.setModel(modeloCombo1);
            resultado.close();     
           
        } catch (SQLException ex) {
            Logger.getLogger(ClsMProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   ResultSet idtipo;
 
      public boolean guardarProveedor() throws ClassNotFoundException, SQLException 
       {
            boolean band=false;
           if(this.getAm().equals("")){
               JOptionPane.showMessageDialog(null, "no debe dejar campos vacios");
           }
           else{
               this.idtipo = cnn.Consulta_SET("SELECT vch_id_tipo FROM tbl_tipo WHERE vch_tipo='"+this.getTipo()+"'");
           
            idtipo.next();
        String sql="begin sp_ins_empl ('"+this.getNombre()+"','"+this.getAp()+"','"+this.getAm()+"','"+this.getTelefono()+"','"+this.getEmail()+"','"+this.getDireccion()+"','"+this.getId()+"','"+this.getPwd()+"','"+this.idtipo.getString("vch_id_tipo")+"'); commit; end;";
      System.out.println(sql);
    
        try {            
            cnn.procesos(sql); 
            band=true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            band=false;
        }
           }
        
     return band;
  }
            
             
     Object[][] dat;
     public DefaultTableModel llenartabla()
  {
      DefaultTableModel dato=null;
      try
      {
          String[] titulos={"id","nombre","ap","am","telefono","email","direccion"};
          String consulta;
          consulta = "select tblempleado.vch_id_empleado,tbldato.vch_nombre,tbldato.vch_app,tbldato.vch_apm,tbldato.vch_telefono,tbldato.vch_email,tbldato.vch_dir from tblempleado inner join tbldato ON tbldato.num_id_dato = tblempleado.num_id_dato;";
          System.out.println(consulta);
          String[] columnas={"tblempleado.vch_id_empleado,tbldato.vch_nombre,tbldato.vch_app,tbldato.vch_apm,tbldato.vch_telefono,tbldato.vch_email,tbldato.vch_dir"};
          dat= cnn.getDatos(consulta, columnas);
          dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
  }
      public void Llenar_JTable_empleados() throws ClassNotFoundException
    {
        try {
            String[] titulos={"CLAVE","NOMBRE","A PATERNO", "A MATERNO", "TELEFONO","EMAIL","DIRECCION"};
            DefaultTableModel table = new DefaultTableModel();
            resultado =cnn.Consulta_SET("select tblempleado.vch_id_empleado,tbldato.vch_nombre,tbldato.vch_app,tbldato.vch_apm,tbldato.vch_telefono,tbldato.vch_email,tbldato.vch_dir from tblempleado inner join tbldato ON tbldato.num_id_dato = tblempleado.num_id_dato;");
            int filas=0,columna=10,i=0;
            while(resultado.next())
            {
                filas++;
            }
            resultado.close();
            resultado = cnn.Consulta_SET("select tblempleado.vch_id_empleado,tbldato.vch_nombre,tbldato.vch_app,tbldato.vch_apm,tbldato.vch_telefono,tbldato.vch_email,tbldato.vch_dir from tblempleado inner join tbldato ON tbldato.num_id_dato = tblempleado.num_id_dato;");
            java.lang.Object[][] matriz = new String[filas][columna];
            while(resultado.next())
            {
                matriz[i][0]=resultado.getString("vch_id_empleado");
                matriz[i][1]=resultado.getString("vch_nombre");
                matriz[i][2]=resultado.getString("vch_app");
                matriz[i][3]=resultado.getString("vch_apm");
                matriz[i][4]=resultado.getString("vch_telefono");
                matriz[i][5]=resultado.getString("vch_email");
                matriz[i][6]=resultado.getString("vch_dir");

                i++;
            }
            resultado.close();
            table.setDataVector(matriz,titulos);
            this.empleado.jTable1.setModel(table);
        } 
        catch (SQLException ex) { }
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
  
    public DefaultComboBoxModel CargarTipos()
    {
        DefaultComboBoxModel CargarComboCategoria=new DefaultComboBoxModel();
        try
        {
          resultado= cnn.consultar("select vch_id_tipo, vch_tipo from tbl_tipo order by vch_id_tipo");
            while(resultado.next())
            {
                CargarComboCategoria.addElement(resultado.getString("tipo"));
            }
        }
        catch(SQLException ex){}
        return CargarComboCategoria;
    }
            
}
