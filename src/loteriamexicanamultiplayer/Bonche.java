/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriamexicanamultiplayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author angelica figueroa
 */
public class Bonche implements Serializable{
    private ArrayList <Carta> cartas;
    private int ultima = 54;

    public Bonche() {
    Carta carta = new Carta();
    carta.crearCartas();
    cartas = carta.getCartas();
    //barajar();
    }

    public Bonche(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public int getUltima() {
        return ultima;
    }

    public void setUltima(int ultima) {
        this.ultima = ultima;
    }
        
    public void barajar(){
        Collections.shuffle(cartas);
    }
    
    public Carta obtenerCarta(){
        ultima--;
        return cartas.get(ultima);

    }
    
    public void eliminarCarta(){
        
    }

    @Override
    public String toString() {
        return "Bonche{" + "cartas=" + cartas.toString() + '}';
    }
    

    
    
}
