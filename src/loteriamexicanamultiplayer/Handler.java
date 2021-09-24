/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriamexicanamultiplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Angelica Figueroa Muñiz
 */
public class Handler implements Runnable{
    
    Socket cliente;
    BufferedReader reader;
    PrintWriter writer;
    ObjectInputStream objectIn;
    ObjectOutputStream objectOut;
    Griton griton;
    static Bonche bonche;
    static ArrayList <Tabla> jugadores = new ArrayList();
    Carta sacada;
    String iniciar = "false";
    String presionoLoteria = "NO";
    Timer correr;
    private ArrayList <String> sacadas = new ArrayList();
    Carta laQueSale;
    String esGanador = "NO";
    String nombre;
    static String nombres = "Jugadores: ";
    static String ganador = "Ganador: ";
    FileWriter escritor;
    PrintWriter enArchivo;
    PrintWriter winner;
    
    public Handler(Socket cliente) {
        this.cliente = cliente;
    }

    public static Bonche getBonche() {
        return bonche;
    }

    public static void setBonche(Bonche bonche) {
        Handler.bonche = bonche;
    }

    int i = 0;
    public void correrBonche(){//metodo para sacar y enviar cartas del handler al cliente
        correr = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (i == 53 || esGanador.matches("SI")) {
                    //bonche.setUltima(54);
                    correr.stop();
                } else {
                    
                    //Carta c = bonche.obtenerCarta();
                    sacadas.add(laQueSale.getPersonaje().getNombre());
                    try {
                        objectOut.writeObject(laQueSale);
                        i++;
                    } catch (IOException ex) {
                        Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        presionoLoteria = (String) objectIn.readObject();
                    } catch (IOException ex) {
                        Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (presionoLoteria.matches("SI")) {
                        ArrayList<String> lasDelJugador = null;
                        try {
                            lasDelJugador = (ArrayList) objectIn.readObject();
                        } catch (IOException ex) {
                            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (comprobarLoteria(lasDelJugador)) {
                            esGanador = "SI";
                            ganador += nombre;
                            try {
                                winner = new PrintWriter("the-file-name.txt", "UTF-8");
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (UnsupportedEncodingException ex) {
                                Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            winner.write(ganador);
                            winner.close();
                            try {
                                objectOut.writeObject("SI");
                            } catch (IOException ex) {
                                Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            correr.stop();
                        } else {
                            try {
                                objectOut.writeObject("NO");
                            } catch (IOException ex) {
                                Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    presionoLoteria = "NO";
                }
            }
        });
        correr.start();
    }
    
    public boolean comprobarLoteria(ArrayList <String> marcadas){
        if (marcadas == null && sacadas == null) {
            return false;
        }
        else if (marcadas == null && sacadas != null || marcadas != null && sacadas == null || marcadas.size() != 16) {
            return false;
        }else
        return sacadas.containsAll(marcadas);
    }

    @Override
    public void run() {
        try {
            objectOut = new ObjectOutputStream(cliente.getOutputStream());
            objectIn = new ObjectInputStream(cliente.getInputStream());
            enArchivo = new PrintWriter("the-file-name.txt", "UTF-8");
        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }
        System.out.println("Se conecto");
        try {
            nombre = (String)objectIn.readObject();
            objectOut.writeObject(griton);
            nombres += nombre + " ";
            System.out.println(nombres);
            enArchivo.write(nombres);
            enArchivo.close();
            while(true){
            if(iniciar.matches("true")){
                objectOut.writeObject(iniciar);
                correrBonche();
                iniciar = "false";
            }
        }
            
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
