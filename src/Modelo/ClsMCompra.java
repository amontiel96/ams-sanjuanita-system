/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Controlador.*;

/**
 *
 * @author Arturo
 */
public class ClsMCompra {
 String buscar,idventa,idpro,nombre,idprove,em="345";
int canAgregar;
    public String getIdprove() {
        return idprove;
    }

    public void setIdprove(String idprove) {
        this.idprove = idprove;
    }
Integer cont,existencia;

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public Integer getCont() {
        return cont;
    }

    public void setCont(Integer cont) {
        this.cont = cont;
    }
    public String getIdventa() {
        return idventa;
    }

    public void setIdventa(String idventa) {
        this.idventa = idventa;
    }

    public String getIdpro() {
        return idpro;
    }

    public void setIdpro(String idpro) {
        this.idpro = idpro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
 Integer cantidad;
 Float precio;
ResultSet resultado;
   JIFrmCompra compra;
    MBD cnn=new MBD();
  public ClsMCompra(JIFrmCompra _productos) {
        this.compra = _productos;
    }
 
 
    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
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
          String[] columnas={"vch_id_producto","vch_nombre","int_existencia","vch_descripcion","vch_id_marca","vch_id_categoria","vch_estado","flt_precio_menu","flt_precio_mayo"};
          dat= cnn.getDatos(consulta, columnas);
          dato= new DefaultTableModel(dat,titulos);
      }catch(Exception ex){}
      return dato;
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
            this.compra.jtblProductos.setModel(table);

        } 
        catch (SQLException ex) { ban= false;}
       return ban;
   }
   DefaultTableModel model;
    public void AgregarTablaProducto()
   {

       if(this.getCantidad()>this.getExistencia()){
           JOptionPane.showMessageDialog(null, "No hay producctos suficientes");
       }else{
            if(!(this.getExistencia()<=0)){
                 int cant = this.getCantidad();
                double preU = this.getPrecio();
                double tProduc = cant * preU;
                model = (DefaultTableModel) this.compra.jtblOrden.getModel();
            
                Object registro[] = {this.getIdventa(),this.getIdpro(),cant,preU,tProduc};
                model.addRow(registro);
             }
             else{
                 JOptionPane.showMessageDialog(null,"No hay suficientes productos");
             }
       }
            
   }
     public void cobrar(){

        double subT=0;
         int tam = compra.jtblOrden.getRowCount();
        for(int i=0; i<tam;i++)
        {
            String s = String.valueOf(compra.jtblOrden.getValueAt(i, 4));
            double p =Double.parseDouble(s);
            subT = subT + p;
        }
        double iva = subT*0.16;
        double Total = subT+iva;
        compra.jtxtSubtotal.setText(String.valueOf(subT));
        compra.jtxtIVA.setText(String.valueOf(iva));
        compra.jtxtTotal.setText(String.valueOf(Total));
        }
    public void CargarCombos() throws ClassNotFoundException
   {
        try {
            DefaultComboBoxModel modeloCombo1 = new DefaultComboBoxModel();
            resultado= cnn.Consulta_SET("SELECT * FROM TBLPROVEEDOR");
            while (resultado.next())
            {  
                modeloCombo1.addElement(resultado.getObject("VCH_NOMBRE_PRO"));     
            }      
            this.compra.jcmbProveedor.setModel(modeloCombo1);
            resultado.close(); 
        } catch (SQLException ex) {
            Logger.getLogger(ClsMProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    ResultSet iprove,idprod;
   jClsCompra co;
    public void guardarCompra() throws SQLException, ClassNotFoundException, Exception{
        iprove=cnn.Consulta_SET("SELECT vch_id_proveedor from tblproveedor where vch_nombre_pro='"+this.getIdprove()+"'");
            
            iprove.next();
                canAgregar=compra.jtblOrden.getRowCount();
                cnn.procesos("insert into tblcompra(flt_total,vch_id_empleado,vch_id_proveedor) values("+Float.parseFloat(this.compra.jtxtTotal.getText())+",'"+this.em.toString()+"','"+this.iprove.getString("vch_id_proveedor")+"')");
for(int i=0;i<canAgregar;i++){
          cnn.procesos("begin sp_inserta_compradeta ('"+this.getIdventa()+"',"+Integer.parseInt((String) this.compra.jtblOrden.getValueAt(i, 2))+","+Float.parseFloat((String) this.compra.jtblOrden.getValueAt(i, 4))+",'"+this.compra.jtblOrden.getValueAt(i, 1)+"'); commit; end;");
      }
    }
   
    
   

