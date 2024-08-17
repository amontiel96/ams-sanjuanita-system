/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Vista.*;
import Controlador.*;
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
public class Cls_MVenta {
    String buscar,idventa,idpro,nombre,em="345";
    Integer cantidad,existencia,canti;
    Float precio,cambio,pre;

    public Float getCambio() {
        return cambio;
    }

    public void setCambio(Float cambio) {
        this.cambio = cambio;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }
    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
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

    public int getCanAgregar() {
        return canAgregar;
    }

    public void setCanAgregar(int canAgregar) {
        this.canAgregar = canAgregar;
    }

    public ResultSet getIprove() {
        return iprove;
    }

    public void setIprove(ResultSet iprove) {
        this.iprove = iprove;
    }

    public ResultSet getIdprod() {
        return idprod;
    }

    public void setIdprod(ResultSet idprod) {
        this.idprod = idprod;
    }
int canAgregar;
ResultSet resultado;
MBD cnn=new MBD();
JIFrmVentas frm;
public Cls_MVenta(JIFrmVentas _frm){
    this.frm=_frm;
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
            this.frm.jtblProducto.setModel(table);
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
                model = (DefaultTableModel) this.frm.jtblOrdenVenta.getModel();
               
                Object registro[] = {this.getIdventa(),this.getIdpro(),cant,preU,tProduc};
                model.addRow(registro);

             }
             else{
                 JOptionPane.showMessageDialog(null,"No hay suficientes productos");
             }
       }
            
                
   }
     public void cambio()
    {
        
        this.cambio = Float.parseFloat(this.frm.jtxtPago.getText())-Float.parseFloat(this.frm.lblTotal.getText());
        this.frm.jlblCambio.setText(""+cambio);
    }
     public void cobrar(){

        double subT=0;
         int tam = frm.jtblOrdenVenta.getRowCount();
        for(int i=0; i<tam;i++)
        {
            String s = String.valueOf(frm.jtblOrdenVenta.getValueAt(i, 4));
            double p =Double.parseDouble(s);
            subT = subT + p;
        }
        double iva = subT*0.16;
        double Total = subT+iva;
        frm.lblTotal.setText(String.valueOf(Total));
        }

    ResultSet iprove,idprod;
   jClsCompra co;
    public void guardarVenta() throws SQLException, ClassNotFoundException, Exception{
                canAgregar=frm.jtblOrdenVenta.getRowCount();
                cnn.procesos("insert into tbl_venta (flt_total, vch_id_cliente,vch_id_empleado) values("+Float.parseFloat(this.frm.lblTotal.getText())+",'"+this.frm.jtxtIDCliente.getText()+"','"+this.em+"')");
    }
     public void QutarProducto(int fila)
    {
        
    }
    
    
}
