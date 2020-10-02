package pares;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

class Imagenes {

    ImageIcon i;
    int t;

    Imagenes() {
        t = 0;
    }

    Imagenes(ImageIcon i) {
        this.i = i;
        t = 0;
    }
}

/**/
public class Pares extends JFrame implements ActionListener {

    JButton[] botones;
    final int COLUMNAS = 4, FILAS = 5, BOTONES = 20,
            COLUMNAS_M=5,FILAS_M=6,BOTONES_M=COLUMNAS_M*FILAS_M,
            COLUMNAS_D=10,FILAS_D=5,BOTONES_D=COLUMNAS_D*FILAS_D
                    , DIS_VERT = 5, DIS_HORIZ = 5;
    Imagenes[] auxImagenes;
    ImageIcon[] imagenes;
    boolean band = false;
    JButton aux1 = null, aux2 = null;
    Random r;
    ImageIcon defaultImage;
    int parejas = 0,tiros=0;
    int width;
    int height;
    Pares() {
        super("Pares");
        r = new Random();
        hazInterfaz();
        hazEscuchadores();
    }

    private void hazInterfaz() {
        setLayout(new GridLayout(COLUMNAS, FILAS, DIS_VERT, DIS_HORIZ));
        setSize(700, 780);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("Pares.JFIF").getImage());
        botones = new JButton[BOTONES];
        for (int i = 0; i < BOTONES; i++) {
            botones[i] = new JButton();
            add(botones[i]);
        }

        auxImagenes = new Imagenes[BOTONES / 2];
        imagenes = new ImageIcon[BOTONES];

        setVisible(true);
        width=botones[0].getWidth();
        height=botones[0].getHeight();
        obtenerImagenes();
        asignaImagenes();
        ponIconosDefault();
        
        

    }
    
    private void ponIconosDefault(){
        defaultImage = Rutinas.AjustarImagen("Default.JPG", width, height);
        for (int i = 0; i < BOTONES; i++) {
            botones[i].setIcon(defaultImage);
        }
    }

    private void obtenerImagenes() {
        auxImagenes[0] = new Imagenes(Rutinas.AjustarImagen("Imagenes\\Arbol.JPEG", width, height));
        auxImagenes[1] = new Imagenes(Rutinas.AjustarImagen("Imagenes\\Culumpio.JPEG", width, height));
        auxImagenes[2] = new Imagenes(Rutinas.AjustarImagen("Imagenes\\Gato.JPEG", width, height));
        auxImagenes[3] = new Imagenes(Rutinas.AjustarImagen("Imagenes\\Cactus.JPEG", width, height));
        auxImagenes[4] = new Imagenes(Rutinas.AjustarImagen("Imagenes\\Nopal.JPEG", width, height));
        auxImagenes[5] = new Imagenes(Rutinas.AjustarImagen("Imagenes\\Semaforo.JPEG", width, height));
        auxImagenes[6] = new Imagenes(Rutinas.AjustarImagen("Imagenes\\Auto.JPEG", width, height));
        auxImagenes[7] = new Imagenes(Rutinas.AjustarImagen("Imagenes\\Indio.JPEG", width, height));
        auxImagenes[8] = new Imagenes(Rutinas.AjustarImagen("Imagenes\\Perro.JPEG", width, height));
        auxImagenes[9] = new Imagenes(Rutinas.AjustarImagen("Imagenes\\Flor.JPG", width, height));
    }

    private void asignaImagenes() {
        int n;
        if(tiros!=0){
            for(int i=0;i<BOTONES;i++)
                imagenes[i]=null;
        }
        for (int i = 0; i < BOTONES; i++) {
            n = r.nextInt(BOTONES);
            if (imagenes[n] != null) {
                i--;
                continue;
            }
            if (auxImagenes[i / 2].t == 2) {
                i--;
                continue;
            }
            imagenes[n] = auxImagenes[i / 2].i;
        }
        tiros=0;
        parejas=0;
    }

    private void hazEscuchadores() {
        for (int i = 0; i < BOTONES; i++) {
            botones[i].addActionListener(this);
        }
    }

    public static void main(String[] args) {
        new Pares();
    }

    public void actionPerformed(ActionEvent e) {
        if (((JButton) (e.getSource())).getIcon() != defaultImage) {
            return;
        }
        if (!band) {
            aux1 = (JButton) e.getSource();
            band = true;
            for (int i = 0; i < BOTONES; i++) {

                if (aux1 == botones[i]) {
                    aux1.setIcon(imagenes[i]);
                    return;
                }
            }
        }
        aux2 = (JButton) e.getSource();
        band = false;
        for (int j = 0; j < BOTONES; j++) {
            if (aux2 == botones[j]) {
                aux2.setIcon(imagenes[j]);
                break;
            }
        }

        aux1.update(aux1.getGraphics());
        aux2.update(aux2.getGraphics());
        Icon temp1 = aux1.getIcon();
        Icon temp2 = aux2.getIcon();

        tiros++;
        if (aux1.getIcon() == aux2.getIcon()) {
            aux1.setEnabled(false);
            aux2.setEnabled(false);
            aux1.setDisabledIcon(temp1);
            aux2.setDisabledIcon(temp2);
            parejas++;
            if (parejas == 10) {
                JOptionPane.showMessageDialog(null, "Felicidades, HAS GANADO!!\nTiros: "+tiros, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                asignaImagenes();
                ponIconosDefault();
                for(int i=0;i<BOTONES;i++){
                    botones[i].setEnabled(true);
                }
                update(getGraphics());
            }
        } else {

            try {
                Thread.sleep(400);
            } catch (Exception ex) {
            }

            aux1.setIcon(defaultImage);
            aux2.setIcon(defaultImage);
            aux1.update(aux1.getGraphics());
            aux2.update(aux1.getGraphics());
        }
    }

}
