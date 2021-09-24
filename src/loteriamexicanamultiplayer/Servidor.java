/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriamexicanamultiplayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Angelica Figueroa Muñiz
 */
public class Servidor {

    static int PORT = 9999;
    
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket servidor = new ServerSocket(PORT);
            Socket cliente;
            System.out.println("Server waiting...");
            
            while (true) {
                cliente = servidor.accept();
                Handler handler = new Handler(cliente);
                Thread conexion = new Thread(handler);
                conexion.start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught: " + e);
        }

    }
    
}
