package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloParaAntenderUnCliente extends Thread{
    Socket sk;
    public HiloParaAntenderUnCliente(Socket sk){
        this.sk = sk;
    }

    @Override
    public void run() {
        InputStream is = null; 
        OutputStream os = null; 
        try {
        	
            is = sk.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            
            os = sk.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            Inet4Address ip = (Inet4Address) sk.getInetAddress();
            String laIP = ip.getHostAddress();
            
            while(true){
                System.out.println("Recibiendo Opci�n del Cliente...\n");
                String linea = br.readLine();
                int clientOption = Integer.parseInt(linea);
                System.out.println("Escogiendo Opci�n Aleatoria... Sin hacer trampas.\n");
                int serverOption = new Random().nextInt(3-1+1)+1;
                switch(serverOption) {
	                case 1:System.out.println("�El Server escogi� Piedra("+serverOption+")!\n");break;
	                case 2:System.out.println("�El Server escogi� Papel("+serverOption+")!\n");break;
	                case 3:System.out.println("�El Server escogi� Tijeras("+serverOption+")!\n");break;
                }
                switch(clientOption) {
                	case 1: System.out.print("�El Cliente("+ laIP +") escogi� Piedra("+ clientOption +")!\n");
                		if(serverOption == 1){
                			bw.write("�EL SERVIDOR ESCOGI� PIEDRA - EMPATADOS!\n");
                		}
                		if(serverOption == 2){
                			bw.write("�EL SERVIDOR ESCOGI� PAPEL - PERDISTE!\n");
                		}
                		if(serverOption == 3){
                			bw.write("�EL SERVIDOR ESCOGI� TIJERAS - GANASTE!\n");
                		}
                		break;
                	case 2: System.out.print("�El Cliente("+ laIP +") escogi� Papel("+ clientOption +")!\n"); 
	                	if(serverOption == 1){
	            			bw.write("�EL SERVIDOR ESCOGI� PIEDRA - GANASTE!\n");
	            		}
	            		if(serverOption == 2){
	            			bw.write("�EL SERVIDOR ESCOGI� PAPEL - EMPATADOS!\n");
	            		}
	            		if(serverOption == 3){
	            			bw.write("�EL SERVIDOR ESCOGI� TIJERAS - PERDISTE!\n");
	            		}
	            		break;
                	case 3: System.out.print("�El Cliente("+ laIP +") escogi� Tijeras("+ clientOption +")!\n");
	                	if(serverOption == 1){
	            			bw.write("�EL SERVIDOR ESCOGI� PIEDRA - PERDISTE!\n");
	            		}
	            		if(serverOption == 2){
	            			bw.write("�EL SERVIDOR ESCOGI� PAPEL - GANASTE!\n");
	            		}
	            		if(serverOption == 3){
	            			bw.write("�EL SERVIDOR ESCOGI� TIJERAS - EMPATADOS!\n");
	            		}
	            		break;
                }
                bw.newLine();
                bw.flush();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(HiloParaAntenderUnCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(is != null) is.close();
            } catch (IOException ex) {
                Logger.getLogger(HiloParaAntenderUnCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
}
