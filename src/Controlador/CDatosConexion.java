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
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo
 */
public class CDatosConexion implements ActionListener{
JFrmDatosConexion frm;
FileOutputStream archivo;
DataOutputStream escribe;
ClsMDatosConexion datos=new ClsMDatosConexion();
MBD con=new MBD();
public CDatosConexion(JFrmDatosConexion _frm){
    this.frm=_frm;
    
    this.frm.jbtnConectar.setActionCommand("conectar");
    this.frm.jbtnConectar.addActionListener(this);
}
public void frm_load(){
    this.frm.setVisible(true);
    this.frm.setLocationRelativeTo(null);
}
    @Override
    public void actionPerformed(ActionEvent ae) {
        String btn=ae.getActionCommand();
        switch(btn){
            case "conectar":
                this.datos.setHost(this.frm.jtxtHost.getText());
                this.datos.setBd(this.frm.jtxtBD.getText());
                this.datos.setUser(this.frm.jtxtUser.getText());
                this.datos.setPwd(this.frm.jtxtPwd.getText());
                mtd_creaArchivo();
                if(this.con.conectar()){
                    JFrmMenuPrincipal frm=new JFrmMenuPrincipal();
                    CMPrincipal cp=new CMPrincipal(frm);
                    cp.Form_Load();
                }
                break;
        }
    }
    public void mtd_creaArchivo()
    {
        try
        {
            archivo = new FileOutputStream("conexion.txt",true);
            escribe = new DataOutputStream(archivo);
            escribe.writeUTF(this.datos.getHost());
            escribe.writeUTF(this.datos.getBd());
            escribe.writeUTF(this.datos.getUser());
            escribe.writeUTF(this.datos.getPwd());
            archivo.close();
            escribe.close();
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
}
