/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;



import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.Socket;

/**
 *
 * @author Arturo
 */
public class ClsMBackup {
    private String nombre,tipo,pwd,user,dias,host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }
 public static RandomAccessFile rf;
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean crearBackup(){
        Socket servidor;
        int numClinte=0;
        
        try {
            rf = new RandomAccessFile("conexion.txt", "r");
          
            user=rf.readUTF();
            pwd=rf.readUTF();
            host=rf.readUTF();
            servidor = new Socket(host, 5000);

            DataInputStream datos = new DataInputStream(servidor.getInputStream());
            System.out.println(datos.readLine());
            PrintStream ps = new PrintStream(servidor.getOutputStream());
            ps.println("Gracias por aceptarme");

            ps.println(this.user);
            ps.println(this.pwd);
            ps.println(this.getNombre());
            ps.println(this.getDias());
            ps.println(this.getTipo());
            servidor.close();
            return true;
        } catch (IOException ex) {
            System.out.println("error: " +  ex.getMessage());
            return false;
        }
    }
}

