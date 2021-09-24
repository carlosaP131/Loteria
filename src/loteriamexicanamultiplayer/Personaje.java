/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriamexicanamultiplayer;

import java.io.Serializable;
import java.util.ArrayList;
import misClases.Picture;

/**
 *
 * @author angelica figueroa
 */
public class Personaje implements Serializable{
    static final String [] NOMBRES = {"El gallo", "El diablito", "La dama", "El catrín", "El paraguas", "La sirena", 
     "La escalera" , "La botella", "El barril", "El árbol", "El melón", "El valiente", "El gorrito", "La muerte", "La pera",
     "La bandera", "El bandolón" , "El violoncello", "La garza", "El pájaro", "La mano", "La bota", "La luna", "El cotorro",
     "El borracho", "El negrito", "El corazón", "La sandía", "El tambor", "El camarón", "Las jaras", "El músico", 
     "La araña", "El soldado", "La estrella", "El cazo", "El mundo", "El apache", "El nopal", "El alacrán", "La rosa", "La calavera",
     "La campana", "El cantarito", "El venado", "El sol", "La corona", "La chalupa", "El pino", "El pescado", "La palma",
     "La maceta", "El arpa", "La rana"};
    
    static final String [] IMAGENES = {"src\\ImagenesLoteria\\1.png",
    "src\\ImagenesLoteria\\2.png", "src\\ImagenesLoteria\\3.png",
    "src\\ImagenesLoteria\\4.png", "src\\ImagenesLoteria\\5.png",
    "src\\ImagenesLoteria\\6.png", "src\\ImagenesLoteria\\7.png",
    "src\\ImagenesLoteria\\8.png", "src\\ImagenesLoteria\\9.png",
    "src\\ImagenesLoteria\\10.png", "src\\ImagenesLoteria\\11.png",
    "src\\ImagenesLoteria\\12.png", "src\\ImagenesLoteria\\13.png",
    "src\\ImagenesLoteria\\14.png", "src\\ImagenesLoteria\\15.png",
    "src\\ImagenesLoteria\\16.png", "src\\ImagenesLoteria\\17.png",
    "src\\ImagenesLoteria\\18.png", "src\\ImagenesLoteria\\19.png",
    "src\\ImagenesLoteria\\20.png", "src\\ImagenesLoteria\\21.png",
    "src\\ImagenesLoteria\\22.png", "src\\ImagenesLoteria\\23.png",
    "src\\ImagenesLoteria\\24.png", "src\\ImagenesLoteria\\25.png",
    "src\\ImagenesLoteria\\26.png", "src\\ImagenesLoteria\\27.png",
    "src\\ImagenesLoteria\\28.png", "src\\ImagenesLoteria\\29.png",
    "src\\ImagenesLoteria\\30.png", "src\\ImagenesLoteria\\31.png",
    "src\\ImagenesLoteria\\32.png", "src\\ImagenesLoteria\\33.png",
    "src\\ImagenesLoteria\\34.png", "src\\ImagenesLoteria\\35.png",
    "src\\ImagenesLoteria\\36.png", "src\\ImagenesLoteria\\37.png",
    "src\\ImagenesLoteria\\38.png", "src\\ImagenesLoteria\\39.png",
    "src\\ImagenesLoteria\\40.png", "src\\ImagenesLoteria\\41.png",
    "src\\ImagenesLoteria\\42.png", "src\\ImagenesLoteria\\43.png",
    "src\\ImagenesLoteria\\44.png", "src\\ImagenesLoteria\\45.png",
    "src\\ImagenesLoteria\\46.png", "src\\ImagenesLoteria\\47.png",
    "src\\ImagenesLoteria\\48.png", "src\\ImagenesLoteria\\49.png",
    "src\\ImagenesLoteria\\50.png", "src\\ImagenesLoteria\\51.png",
    "src\\ImagenesLoteria\\52.png", "src\\ImagenesLoteria\\53.png",
    "src\\ImagenesLoteria\\54.png"};
    
    private String nombre;
    private int numero;
    private String imagen;
    private ArrayList <Personaje> personajes = new ArrayList <Personaje>();

    public Personaje() {
        
    }
    
    public Personaje(String nombre, int numero, String imagen) {
        this.nombre = nombre;
        this.numero = numero;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }
    
    public void crearPersonajes(){
        for(int i = 0; i < NOMBRES.length; i++){
            personajes.add(new Personaje(NOMBRES[i], i + 1, IMAGENES[i]));
        }
    }
    
    @Override
    public String toString() {
        return nombre + ", " + numero + ", " + imagen;
    }
    
}
