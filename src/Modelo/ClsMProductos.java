/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.JIFrmProducto;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Arturo
 */
public class ClsMProductos {
    private String id,nombre,ap,am,descripcion,idmarca,idcategoria,buscar;

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }
   ResultSet resultado;
   JIFrmProducto productos;
    MBD cnn=new MBD();
  public ClsMProductos(JIFrmProducto _productos) {
        this.productos = _productos;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(String idmarca) {
        this.idmarca = idmarca;
    }

    public String getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(String idcategoria) {
        this.idcategoria = idcategoria;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
    private int existencia;
    
    public boolean guardarProveedor() 
    {
        String sql="insert into tblproductos values('"+ this.getId() +"','"+ this.getNombre() +"',"+this.getExistencia()+",'"+this.getDescripcion()+"','"+this.getIdmarca()+"','"+this.getIdcategoria()+"')";
      System.out.println(sql);
     boolean band=false;
        try {            
            cnn.procesos(sql);            
            //band=true;
            resultado= cnn.Consulta_SET("SELECT TBLPRODUCTOS WHERE VCH_ID_PRODUCTO='"+this.getId()+"'");
            if (resultado.next())
            {  
                 band=true;   
            }
            else{band=false;}
        } catch (Exception ex) {            
        }
     return band;
  }
     Object[][] dat;
     public DefaultTableModel llenartabla()
  {
      DefaultTableModel dato=null;
      try
      {
          String[] titulos={"CLAVE","NOMBRE","EXISTENCIA","DECRIPCION","MARCA","CATEGORIA","ESTADO","PRECIO MENU","PRECIO MAYOREO"};
          String consulta;
          consulta = "select * from tblproductos";
          String[] columnas={"vch_id_producto","vch_nombre","int_existencia","vch_descripcion","vch_id_marca","vch_id_categoria"};
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
    public boolean BuscarProducto() throws ClassNotFoundException
   {
       boolean ban=false;
       try {
            String[] titulos={"CLAVE","NOMBRE","EXISTENCIA","DECRIPCION","MARCA","CATEGORIA","ESTADO","PRECIO MENU","PRECIO MAYOREO"};
            DefaultTableModel table = new DefaultTableModel();
            resultado = cnn.Consulta_SET("select * from tblproductos where vch_id_producto = '"+this.getBuscar()+"'");
            int filas=0,columna=9,i=0;
            while(resultado.next())
            {
                filas++;
            }
            resultado.close();
            resultado =cnn.Consulta_SET("select * from tblproductos where vch_id_producto = '"+this.getBuscar()+"'");
            java.lang.Object[][] matriz = new String[filas][columna];
            while(resultado.next())
            {
                matriz[i][0]=resultado.getString("vch_id_producto");
                matriz[i][1]=resultado.getString("vch_nombre");
                matriz[i][2]=resultado.getString("int_existencia");
                matriz[i][3]=resultado.getString("vch_descripcion");
                matriz[i][4]=resultado.getString("vch_id_marca");
                matriz[i][5]=resultado.getString("vch_id_categoria");
                matriz[i][6]=resultado.getString("vch_estado");
                matriz[i][7]=resultado.getString("flt_precio_menu");
                matriz[i][8]=resultado.getString("flt_precio_mayo");
                i++;
                if(i==0){
                    ban=false;
                }
                else{
                    ban=true;}
            }
            resultado.close();
            table.setDataVector(matriz,titulos);
            this.productos.jTblProducto.setModel(table);
        } 
        catch (SQLException ex) { ban= false;}
       return ban;
   }
    public void CargarCombos() throws ClassNotFoundException
   {
        try {
            DefaultComboBoxModel modeloCombo1 = new DefaultComboBoxModel();
            resultado= cnn.Consulta_SET("SELECT * FROM TBLCATEGORIA");
            while (resultado.next())
            {  
                modeloCombo1.addElement(resultado.getObject("VCH_NOM_CATEGORIA"));     
            }      
            this.productos.jcmbCategoria.setModel(modeloCombo1);
            resultado.close();
            DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
            resultado= cnn.Consulta_SET("SELECT * FROM TBLMARCA");
            while (resultado.next())
            {  
                modeloCombo2.addElement(resultado.getObject("VCH_NOM_MARCA"));     
            }      
            this.productos.jcmbMarca.setModel(modeloCombo2);
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClsMProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
   }    
}
