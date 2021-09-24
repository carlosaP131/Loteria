/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriamexicanamultiplayer;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.Timer;
import misClases.Picture;

/**
 *
 * @author Angelica Figueroa Muñiz
 */
public class Griton implements Serializable{
    
    private static ArrayList <TablaGrafica> tablas = new ArrayList <>();
    private static final ArrayList <String> sacadas = new ArrayList <String> ();
    //private final ArrayList <String> marcadas = new ArrayList <String> ();
    private Bonche bonche;
    private Timer correr;

    
    public Griton() {
        bonche = new Bonche();
        bonche.barajar();
    }

    public ArrayList <TablaGrafica> getTablas() {
        return tablas;
    }

    public Bonche getBonche() {
        return bonche;
    }

    public void setBonche(Bonche bonche) {
        this.bonche = bonche;
    }

    public Timer getCorrer() {
        return correr;
    }

    public void setCorrer(Timer correr) {
        this.correr = correr;
    }
    
    
    
    public void crearTablas(int cantidad, Griton g){
        bonche.barajar();
        for(int i = 0; i < cantidad; i++){
            TablaGrafica tabla = new TablaGrafica(g);
            tablas.add(tabla);
            tabla.setVisible(true);
        }
       // correrBonche();
    }
    
    public void correrBonche() {
        correr = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (bonche.getUltima() == 0) {
                    bonche.setUltima(54);
                    correr.stop();
                } else {
                    Carta c = bonche.obtenerCarta();
                    sacadas.add(c.getPersonaje().getNombre());
                    Picture p = new Picture(c.getPersonaje().getImagen());
                    Image img = p.getImage();
                    Image newI = img.getScaledInstance(199, 318, java.awt.Image.SCALE_SMOOTH);
                    String f = c.getFrase();
                    for (int i = 0; i < tablas.size(); i++) {
                        tablas.get(i).actualizarCarta(newI);
                        tablas.get(i).actualizarFrase(f);
                    }
                }
            }
        });
        correr.start();
    }
        
//    public void guardarMarcada(String carta){
//        marcadas.add(carta);
//    }
    
    public boolean comprobarLoteria(ArrayList <String> marcadas){
        correr.stop();
        System.out.println(sacadas);
        System.out.println(marcadas);
        if (marcadas == null && sacadas == null) {
            return false;
        }
        else if (marcadas == null && sacadas != null || marcadas != null && sacadas == null || marcadas.size() != 16) {
            return false;
        }else
        return sacadas.containsAll(marcadas);
        
    }

    
}
