/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriamexicanamultiplayer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author angelica figueroa
 */
public class Tabla implements Serializable{
    private ArrayList <Personaje> personajes = new ArrayList <> ();
    private int numeroDeTabla;
    private Carta carta = new Carta();

    public Tabla() {
        numeroDeTabla = (int) (Math.random() * 150) + 1;
        carta.crearCartas();
        ArrayList todas = carta.getCartas();
        for(int i = 0; i < 16; i++){
            int index = (int) (Math.random() * todas.size());
            Carta carta = (Carta)todas.get(index);
            Personaje personaje = carta.getPersonaje();
            personajes.add(personaje);
            todas.remove(index);
        }
    }

    public Tabla(ArrayList<Personaje> personajes, int numeroDeTabla) {
        this.personajes = personajes;
        this.numeroDeTabla = numeroDeTabla;
    }
    

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public int getNumeroDeTabla() {
        return numeroDeTabla;
    }

    public void setNumeroDeTabla(int numeroDeTabla) {
        this.numeroDeTabla = numeroDeTabla;
    }
    
    public String getNombrePersonaje(int index){
        Personaje personaje = personajes.get(index);
        String nombre = personaje.getNombre();
        return nombre;
    }

    public String getImagen(int index){
        Personaje personaje = personajes.get(index);
        String imagen = personaje.getImagen();
        return imagen;
    }
    @Override
    public String toString() {
        return "Tabla no." + numeroDeTabla + "\n" + 
                personajes.get(0) + "\t" + personajes.get(1) + "\t" + personajes.get(2) + "\t" +  personajes.get(3) + "\n" + 
                personajes.get(4) + "\t" + personajes.get(5) + "\t" + personajes.get(6) + "\t" + personajes.get(7) + "\n" + 
                personajes.get(8) + "\t" + personajes.get(9) + "\t" + personajes.get(10) + "\t" + personajes.get(11) + "\n" + 
                personajes.get(12) + "\t" + personajes.get(13) + "\t" + personajes.get(14) + "\t" + personajes.get(15);
    }
    
    
}
