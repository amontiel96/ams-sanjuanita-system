/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.JIFrmCategorias;
import Vista.JIFrmCategorias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import Modelo.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 *
 * @author Arturo
 */
public class jClsCategorias implements ActionListener, MouseListener {
    JIFrmCategorias frm;
    ClsMCategorias datos=new ClsMCategorias();
    public jClsCategorias(JIFrmCategorias _frm) {
        this.frm = _frm;
        this.frm.jbtnNuevo.setActionCommand("nuevo");
        this.frm.jbtnNuevo.addActionListener(this);
        this.frm.jbtnCerrar.setActionCommand("salir");
        this.frm.jbtnCerrar.addActionListener(this);
        this.frm.jbtnAgregar.setActionCommand("agregar");
        this.frm.jbtnAgregar.addActionListener(this);
        this.frm.jbtnEditar.setActionCommand("editar");
        this.frm.jbtnEliminar.setActionCommand("eliminar");
        this.frm.jbtnEliminar.addActionListener(this);
        this.frm.jbtnEditar.addActionListener(this);
        this.frm.jTblCategorias.addMouseListener(this);
        this.habilitar_botones("vacio");
        this.habilitar_cajas("");
        this.llenartabla();
        this.llenartabla();
        
        this.frm.jtxtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent ke){
                buscar(ke);
            }
           
});
        this.frm.jtxtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent ke){
                id(ke);
            }
        });
        this.frm.jtxtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent ke){
                nombre(ke);
            }
        });
        
    }
    
    public void fr_load() {
        this.frm.setVisible(true);
    }
private void habilitar_cajas(String estado)
    {
        boolean band;
        if(estado.equals("nuevo"))
        {
            this.frm.jtxtID.setEnabled(true);
            band=true;
            this.frm.jtxtID.requestFocus();
        }
        else
        {
            if(estado.equals("editar"))
            {
            this.frm.jtxtID.setEnabled(false);
            this.frm.jtxtNombre.requestFocus();
            band=true;
            }
            else
            {
            this.frm.jtxtID.setEnabled(false);
            band=false;
            }
        }
           
       
    }
     private void habilitar_botones(String estado)
   {
       if(estado.equals("nuevo") || estado.equals("modificar") )
       {
           this.frm.jbtnNuevo.setEnabled(false);
           this.frm.jbtnAgregar.setEnabled(true);
           this.frm.jbtnEditar.setEnabled(false);
           this.frm.jbtnEliminar.setEnabled(false);
           this.frm.jbtnCerrar.setText("Cancelar");
       }
       else
       {
            if(estado.equals("guardar") || estado.equals("eliminar") || estado.equals("datos") )
            {
                this.frm.jbtnNuevo.setEnabled(true);
                this.frm.jbtnAgregar.setEnabled(false);
                this.frm.jbtnEditar.setEnabled(true);
                this.frm.jbtnEliminar.setEnabled(true);
                this.frm.jbtnCerrar.setText("Salir");
            }
            else
            {
                 if(estado.equals("vacio"))
                 {
                    this.frm.jbtnNuevo.setEnabled(true);
                    this.frm.jbtnAgregar.setEnabled(false);
                    this.frm.jbtnEditar.setEnabled(false);
                    this.frm.jbtnEliminar.setEnabled(false);
                    this.frm.jbtnCerrar.setText("Salir");
                 }               
            
            }
       
       }
   }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
       String btn=ae.getActionCommand();
        switch(btn){
           case "nuevo":
              this.habilitar_botones("nuevo");
              this.habilitar_cajas("nuevo");
          break;
            case "agregar":
                try
             {
            enviar();
             if(this.datos.guardarProveedor())
              {
                  JOptionPane.showMessageDialog(null,"Se guardo con exito");
                  this.llenartabla();
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"Error al guardar...");
              }
             }
             catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null, e);
             }
                break;
            case "editar":
                 try
             {
            enviar();
             if(this.datos.editar())
              {
                  JOptionPane.showMessageDialog(null,"Se edito con exito");
                  this.llenartabla();
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"Error al editar");
              }
             }
             catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null, e);
             }
                break;
                case "eliminar":
              try
             {
            enviar();
             if(this.datos.eliminar()==true)
              {
                  JOptionPane.showMessageDialog(null,"Se elimino con exito");
                  this.llenartabla();
              }
             else
              {
                  JOptionPane.showMessageDialog(null,"Error al eliminar");
              }
             }
             catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null, e);
             }
             break;
        }
        
        
        
    }
    private void llenartabla()
    {
           this.frm.jTblCategorias.setModel(datos.llenartabla());
    }
    public void enviar()
     {
     this.datos.setId(this.frm.jtxtID.getText());
             this.datos.setNombre(this.frm.jtxtNombre.getText());
     }
     //validacion buscar
    private void buscar(KeyEvent ke){
        if(this.frm.jtxtBuscar.getText().length()==10)
        {
            ke.consume();
        }
        this.SoloNumeros(ke);
    }
     
     //validacion id
     public void id(KeyEvent ke){
         if (this.frm.jtxtID.getText().length()==10)
        {
            ke.consume();
        }
        this.SoloNumeros(ke);
     }
     //validacion nombre cate
     public void nombre(KeyEvent ke){
          if (this.frm.jtxtNombre.getText().length()==10)
        {
            ke.consume();
        }
        this.SoloLetras(ke);
     }
    
    
     public void SoloNumeros(KeyEvent e) {
        char teclaPulsada = e.getKeyChar();
        if (!Character.isDigit(teclaPulsada)) { 
            e.consume(); 
            }                     
    }
     public void SoloLetras(KeyEvent e) {
        char teclaPulsada = e.getKeyChar();
        if (Character.isDigit(teclaPulsada)) {
            e.consume(); 
            }                      
    }

    @Override
    public void mouseClicked(MouseEvent me) {
          if(me.getButton()==1)
        {
            int fila=this.frm.jTblCategorias.rowAtPoint(me.getPoint());
           // this.habilitar_botones("guardar");
            if(fila>-1)
            {
                this.frm.jtxtID.setText(String.valueOf(this.frm.jTblCategorias.getValueAt(fila,0)));
                this.frm.jtxtNombre.setText(String.valueOf(this.frm.jTblCategorias.getValueAt(fila,1)));
               
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
       
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
}
