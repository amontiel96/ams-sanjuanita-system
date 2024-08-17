/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.*;
import Vista.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;
/**
 *
 * @author Arturo
 */
public class jClsBackup implements ActionListener{
    JIFrmRespaldo frm;
    ClsMBackup datos=new ClsMBackup();
public jClsBackup(JIFrmRespaldo _frm){
    this.frm=_frm;
    this.frm.jbtnEnviar.setActionCommand("enviar");
    this.frm.jbtnEnviar.addActionListener(this);
    
}
public void frm_load(){
    this.frm.setVisible(true);
}
    @Override
    public void actionPerformed(ActionEvent ae) {
        String btn=ae.getActionCommand();
        switch(btn){
            case "enviar":
                enviar();
                if(this.datos.crearBackup()){
                    JOptionPane.showMessageDialog(null,"El Backup se programo con exito...");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Erro al crear el Backup...");
                }
                break;
        }
    }
    public void enviar(){
        this.datos.setNombre(this.frm.jtxtNombre.getText());
        this.datos.setTipo((String) this.frm.jcmbTipo.getSelectedItem());
    }
    
}
