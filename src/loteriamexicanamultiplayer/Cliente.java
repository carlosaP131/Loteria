/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriamexicanamultiplayer;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import static loteriamexicanamultiplayer.Handler.bonche;
import misClases.Picture;

/**
 *
 * @author Angelica Figueroa Muñiz
 */
public class Cliente implements Serializable{
    
    private Socket cliente = null;
    private String name;
    private int puerto;
    private String ip;
    ObjectInputStream entradaObjetos;
    ObjectOutputStream salidaObjetos;
    private TablaGrafica tabla;
    static Griton griton;
    Timer actualizar;
    Carta sacada;
    int i = 0;
    String presionoLoteria = "NO";
    String esGanador = "NO";

    public Cliente(String name, String ip, int puerto) {
        this.name = name;
        this.ip = ip;
        this.puerto = puerto;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public void actualizarCartas(){//metodo para recibir las cartas desde el handler y actualizar la imagen y frase
       actualizar = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               // System.out.println("i = " + i);
                if(i == 53){
                    actualizar.stop();
                    //i = 0;
                }else{
                try {
                     sacada = (Carta)entradaObjetos.readObject();
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                        Picture p = new Picture(sacada.getPersonaje().getImagen());
                        Image img = p.getImage();
                        Image newI = img.getScaledInstance(199, 318, java.awt.Image.SCALE_SMOOTH);
                        String frase = sacada.getFrase();
                        tabla.actualizarCarta(newI);
                        tabla.actualizarFrase(frase);
                        i++;
                presionoLoteria = tabla.getPresionoLoteria();
                tabla.setPresionoLoteria("NO");
                    try {
                        salidaObjetos.writeObject(presionoLoteria);
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                if(presionoLoteria.matches("SI")){
                    try {
                        salidaObjetos.writeObject(tabla.getMarcadas());
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        esGanador = (String)entradaObjetos.readObject();
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(esGanador.matches("SI")){
                        Ganador winner = new Ganador();
                        winner.setVisible(true);
                        actualizar.stop();
                    }else if(esGanador.matches("NO")){
                        Perdedor loser = new Perdedor();
                        loser.setVisible(true);
                        presionoLoteria = "NO";
                    }
                }
                presionoLoteria = "NO";
            }}
        });
        actualizar.start();
    }
    
    public void conectarse() throws ClassNotFoundException {
        try {
            cliente = new Socket(ip, puerto);
            salidaObjetos = new ObjectOutputStream(cliente.getOutputStream());
            entradaObjetos = new ObjectInputStream(cliente.getInputStream());
            String iniciar = "false";
            salidaObjetos.writeObject(name);
            griton  = (Griton)entradaObjetos.readObject();
            tabla = new TablaGrafica(griton);
            tabla.ponerNombre(name);
            tabla.setVisible(true);
            while(true){
                if(iniciar.matches("false")){
                iniciar = (String) entradaObjetos.readObject();
                System.out.println(iniciar);
                }else if(iniciar.matches("true")){
                    actualizarCartas();
                    iniciar = "corriendo";
                }
            }
        } catch (IOException ex) {
            System.err.println("Error al conectar el cliente\n" + ex.getMessage());
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
        InformacionCliente s =  new InformacionCliente();
        s.setVisible(true);
        String nombre = "";
        String ip = "";
        int puerto = 0000;
        while(nombre.matches("")){
            nombre = s.getName();
            ip = s.getIp();
            puerto = s.getPort();
        }
            nombre = s.getName();
            ip = s.getIp();
            puerto = s.getPort();
        s.dispose();
        Cliente cliente = new Cliente(nombre, ip, puerto);
        cliente.conectarse();
        
        
    }
}
