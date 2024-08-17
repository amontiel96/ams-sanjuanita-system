/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Vista.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.util.GregorianCalendar;
import Modelo.*;
import java.util.Calendar;
/**
 *
 * @author Arturo
 */
public class CMPrincipal implements ActionListener{
    JFrmMenuPrincipal MnP;
    private Timer timer;
    int year,mes,dia,hora,minutos,segundos;
    String fecha;
   
   
    
   public CMPrincipal(JFrmMenuPrincipal _frm){
       this.MnP=_frm;
        Calendar cal=new GregorianCalendar();
        Calendar calendario= Calendar.getInstance();
        
        year=cal.get(Calendar.YEAR);
        mes=cal.get(Calendar.MONTH);
        dia=cal.get(Calendar.DAY_OF_MONTH);
        
       
        fecha=""+String.valueOf(dia)+" - "+String.valueOf(mes)+" - "+String.valueOf(year);
        this.MnP.jlblFecha.setText(fecha);
    
   }
    public void Form_Load()
    {
        MnP.setTitle("Plasticos San Juanita - Menu Inicio");
        MnP.setVisible(true);
        this.MnP.setExtendedState(MAXIMIZED_BOTH);
        
        this.MnP.jBtnCategoria.addActionListener(this);
        this.MnP.jBtnCategoria.setActionCommand("Categorias");
        this.MnP.jBtnCliente.addActionListener(this);
        this.MnP.jBtnCliente.setActionCommand("Cliente");
        this.MnP.jBtnCompras.addActionListener(this);
        this.MnP.jBtnCompras.setActionCommand("Compra");
        this.MnP.jBtnEmpleados.addActionListener(this);
        this.MnP.jBtnEmpleados.setActionCommand("Empleado");
        this.MnP.jBtnMarca.addActionListener(this);
        this.MnP.jBtnMarca.setActionCommand("Marca");
        this.MnP.jBtnProducto.addActionListener(this);
        this.MnP.jBtnProducto.setActionCommand("Producto");
        this.MnP.jBtnProveedores.addActionListener(this);
        this.MnP.jBtnProveedores.setActionCommand("Proveedores");
        this.MnP.jBtnTipoEmp.addActionListener(this);
        this.MnP.jBtnTipoEmp.setActionCommand("TipoEmp");
        this.MnP.jBtnVentas.addActionListener(this);
        this.MnP.jBtnVentas.setActionCommand("Ventas");
        this.MnP.jBtnBackups.setActionCommand("backup");
        this.MnP.jBtnBackups.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {

        
        String Boton=ae.getActionCommand();
        switch(Boton)
        {
            case "backup":
                this.MnP.jDPInicio.removeAll();
                JIFrmRespaldo res=new JIFrmRespaldo();
                jClsBackup ba=new jClsBackup(res);
                this.MnP.jDPInicio.add(res);
                ba.frm_load();
                break;
            case "Categorias":
                MnP.jDPInicio.removeAll();

                JIFrmCategorias Categoria=new JIFrmCategorias();
                jClsCategorias ve=new jClsCategorias(Categoria);
                MnP.jDPInicio.add(Categoria);
                ve.fr_load();

                break;
            case "Cliente":
                MnP.jDPInicio.removeAll();
                JIFrmCliente Cliente=new JIFrmCliente();
                jClsCliente cli=new jClsCliente(Cliente);
                MnP.jDPInicio.add(Cliente);
                cli.frm_load();
                break;
            case "Compra":
                try
                {
                    MnP.jDPInicio.removeAll();
                JIFrmCompra Compra=new JIFrmCompra();
                ClsMCompra datos=new ClsMCompra(Compra);
                jClsCompra iniciar=new jClsCompra(Compra,datos);
                MnP.jDPInicio.add(Compra);
                iniciar.frm_load();
                }catch(Exception e){}
                
                break;
            case "Empleado":
                try{
                    MnP.jDPInicio.removeAll();
                JIFrmEmpleado Empleado=new JIFrmEmpleado(MnP,true);
                Modelo.ClsMEmpleado emp=new Modelo.ClsMEmpleado(Empleado);
                jClsEmpleado em=new jClsEmpleado(Empleado,emp);
                MnP.jDPInicio.add(Empleado);
                em.frm_load(); 
                }catch(Exception e){System.out.println(e);}
               
                break;
            case "Marca":
                MnP.jDPInicio.removeAll();
                JIFrmMarca Marca=new JIFrmMarca();
                jClsMarca ms=new jClsMarca(Marca);
                MnP.jDPInicio.add(Marca);
                ms.frm_load();
                break;
            case "Producto":
                try{
                    MnP.jDPInicio.removeAll();
                JIFrmProducto Producto=new JIFrmProducto(MnP,true);
                Modelo.ClsMProductos pro=new Modelo.ClsMProductos(Producto);
                jClsProductos prod=new jClsProductos(Producto,pro);
                MnP.jDPInicio.add(Producto);
                prod.frm_load();
                }catch(Exception e){}
                
                break;
            case "Proveedores":
                MnP.jDPInicio.removeAll();
                JIFrmProveedor Proveedores=new JIFrmProveedor();
                jClsProveedor pr=new jClsProveedor(Proveedores);
                MnP.jDPInicio.add(Proveedores);
                pr.frm_load();
                break;
            case "TipoEmp":
                MnP.jDPInicio.removeAll();
                JIFrmTipoEmp TipoEmp=new JIFrmTipoEmp();
                jClsTipoEmpleado tipo=new jClsTipoEmpleado(TipoEmp);
                MnP.jDPInicio.add(TipoEmp);
                tipo.frm_load();
                break;
            case "Ventas":
                try{
                     MnP.jDPInicio.removeAll();
                JIFrmVentas Ventas=new JIFrmVentas(MnP,true);
                Cls_MVenta dat=new Cls_MVenta(Ventas);
                jClsVentas iniciar=new jClsVentas(Ventas,dat);
                MnP.jDPInicio.add(Ventas);
                iniciar.frm_load();
                }catch(Exception e){}
               
                break;
        }
    }
    
}
