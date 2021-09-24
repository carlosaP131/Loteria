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
public class Carta implements Serializable{
    private static final String [] FRASES = {"El que la cantó a San Pedro.", "Pórtate bien cuatito, si no te lleva el coloradito.",
    "Puliendo el paso, por toda la calle real.", "Don Ferruco en la alameda, su bastón quería tirar.",
    "Para el sol y para el agua.", "Medio cuerpo de señora se divisa en altamar.", "Súbeme paso a pasito, no quieras pegar brinquitos.",
    "La herramienta del borracho.", "Tanto bebió el albañil, que quedó como barril.", "El que a buen árbol se arrima buena sombra le cobija.",
    "Me lo das o me lo quitas.", "Por que le corres cobarde, trayendo tan buen puñal.", "El gorrito que me ponen", "La muerte tilica y flaca.", 
    "El que espera desespera.", "Verde blanco y colorado, la bandera del soldado.", "Tocando su bandolón, está el mariachi Simón.", 
    "Creciendo se fue hasta el cielo, y como no fue violín, tuvo que ser violoncello.", "Al otro lado del río tengo mi banco de arena, donde se sienta mi chata pico de garza morena.",
    "Tu me traes a puros brincos, como pájaro en la rama.", "La mano de un criminal.", "Una bota igual que l'otra.", "El farol de los enamorados.",
    "Cotorro cotorro saca la pata, y empiézame a platicar.", "¡Ah! qué borracho tan necio, ya no lo puedo aguantar.", "El que se comió el azúcar.",
    "No me extrañes corazón, que regresó en el camión.", "La barriga que Juan tenía, era empacho de sandía", "No te arrugues cuero viejo, que te quiero pa'tambor.",
    "Camarón que se duerme, se lo lleva la corriente.", "Las jaras del indio Adán, donde pegan, dan.", "El músico trompa de hule, ya no me quiere tocar.",
    "Atarántamela a palos, no me la dejes llegar.", "Uno, dos y tres el soldado p'al cuartel.", "La guía de los marineros.", "El caso que te hago es poco.",
    "Este mundo es una bola, y nosotros un balón.", "¡Ah Chihuahua! cuánto apache con pantalón y huarache.", "Al nopal lo van a ver, nomás cuando tiene tunas.",
    "El que con la cola pica, le dan una paliza.", "Rosita, Rosaura, ven que te quiero ahora.", "Al pasar por el panteón, me encontré un calaverón.",
    "Tú con la campana y yo con tu hermana.", "Tanto va el cántaro al agua, que se quiebra y te moja las enaguas.", "Saltando va buscando, pero no ve nada.",
    "Solo solo te quedaste, de cobija de los pobres.", "El sombrero de los reyes.", "Rema rema va Lupita, sentada en su chalupita.",
    "Fresco y oloroso, en todo tiempo hermoso.", "El que por la boca muere, aunque mudo fuere.", "Palmero sube a la palma y bájame un coco real.",
    "El que nace pa'maceta, no sale del corredor.", "Arpa vieja de mi suegra, ya no sirves pa'tocar.", "Al ver a la verde rana, qué brinco pegó tu hermana."};
    private String frase;
    private Personaje personaje;
    private ArrayList <Carta> cartas = new ArrayList <Carta>();

    public Carta() {
        
    }
    
    public Carta(String frase, Personaje personaje) {
        this.frase = frase;
        this.personaje = personaje;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }
    
    public void crearCartas(){
        Personaje personaje = new Personaje ();
        personaje.crearPersonajes();
        for(int i = 0; i < FRASES.length; i++){
            cartas.add(new Carta(FRASES[i],personaje.getPersonajes().get(i)));
        }
    }

    @Override
    public String toString() {
        return "Carta no." + personaje.getNumero() + "  [" + personaje.getNombre() + "]  Frase: " + frase;
    }
    
    
    
    
    
}
