/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Vista.Splash;

/**
 *
 * @author Arturo
 */
public class CMain {
     public static void main(String[] args)
    {
       
        Splash fr=new Splash();
        CSplash c=new CSplash(fr);
        c.frm_load();
    }
}
