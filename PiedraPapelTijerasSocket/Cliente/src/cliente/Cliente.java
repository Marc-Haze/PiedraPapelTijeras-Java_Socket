/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    final static int PORT = 40080;
    final static String HOST = "localhost";
  
    public static void main(String[] args) {
        try {
            Socket sk = new Socket(HOST, PORT);
            
            enviarMensajesAlServidor(sk);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void enviarMensajesAlServidor(Socket sk) {
        OutputStream os = null; 
        InputStream is = null; 
        try {
        	
            os = sk.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
          
            is = sk.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            
            Scanner sc = new Scanner(System.in);
            String linea;
            int opcion;
   
            while(true){
            	System.out.println("¡MUY BIEN VAMOS A JUGAR A PIEDRA(1), PAPEL(2) O TIJERAS(3)... ESCOGE!");
                System.out.println("Opción: ");
                linea = sc.nextLine();
                opcion = Integer.parseInt(linea);
                while(opcion <= 0 || opcion >= 4){
                	System.out.println("¡MAL, ESCOGE ENTRE 1,2 y 3!");
                	System.out.println("Opción: ");
                    linea = sc.nextLine();
                    opcion = Integer.parseInt(linea);
                }
                bw.write(linea);
                bw.newLine();
                bw.flush();
                linea = br.readLine();
                System.out.println("El Servidor está escogiendo sin hacer trampas...");
                System.out.println(linea);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(os != null) os.close();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
