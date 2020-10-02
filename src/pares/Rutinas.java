package pares;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Rutinas {
	public static ImageIcon AjustarImagen(String ico,int Ancho,int Alto)
    {
        ImageIcon tmpIconAux = new ImageIcon(ico);
        //Escalar Imagen
        ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(Ancho,
        		Alto, Image.SCALE_SMOOTH));//SCALE_DEFAULT
        return tmpIcon;
    } 
    static public String PonBlancos(String Texto, int Tamaño) {
        while (Texto.length() < Tamaño) {
            Texto += " ";
        }
        return Texto;
    }
}
