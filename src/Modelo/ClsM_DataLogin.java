/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Arturo
 */
public class ClsM_DataLogin {
    public String usuario,contraseña;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ResultSet getResultado() {
        return resultado;
    }

    public void setResultado(ResultSet resultado) {
        this.resultado = resultado;
    }
    ResultSet resultado;
    
    public boolean ingresar() 
    {
      boolean badera = false;
      try
      {
         resultado = con.consultar("select * from tblusuarios");
         while(resultado.next())
         {
           if(this.getNombre().equals(resultado.getObject("vchUser")) && this.getContraseña().equals(resultado.getObject("vchPass")))
             {
                 badera=true;
             }
         }
         resultado.close();
      }
      catch(SQLException e)
      {
          JOptionPane.showMessageDialog(null,e.toString());
     }
      return badera;
  }
}
