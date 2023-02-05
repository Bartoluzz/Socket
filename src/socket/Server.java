
package socket;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    ServerSocket server = null;
    Socket socketClient = null;
    
    DataInputStream in;
    DataOutputStream out;
    
    int porta = 2000;
    String ip;
    String hostname;
    
 /*   
    public void info(){
        try {

            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
 */   
    public void messagge(){
        try {
            System.out.println("[3] - In attesa del messaggio...");
            String letto = in.readLine();
            System.out.println("[4] - Messaggio ricevuto...");
            String risposta = null;
            if(letto.equals("ciao")){
                risposta = "ciao a te";
            }else{
                risposta = "supercalifragisticespiralidoso";
            }
            out.writeBytes(risposta +"\n"); //prende una stringa come parametro (vedere cosa combina)
            socketClient.close(); //chiudo la connessione dopo l'invio del messaggio
            } 
        catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Socket start(){
        try {
            ip = Inet4Address.getLocalHost().getHostAddress();
            hostname = InetAddress.getLocalHost().getHostName();
            
            System.out.println("[0] - Inizializzo il server... ");
            System.out.println("[0.1] - Indirizzo IP : "+ ip);
            System.out.println("[0.2] - Hostname : " + hostname);            
            server = new ServerSocket(porta); //inizializzo il server 
            System.out.println("[1] - Server in ascolto... ");
            socketClient = server.accept(); //ascolto la porta
            System.out.println("[2] - Connessione accettata ... ");
            server.close(); //serve per evitare connessioni multiple
            //System.out.println("[3] - Connessione terminata....");
            
            in = new DataInputStream(socketClient.getInputStream());
            out = new DataOutputStream(socketClient.getOutputStream());
            
            }
        
        catch (BindException err){
            System.err.println("Porta gia in uso");
        }        
        
        catch (IOException err) {
            err.printStackTrace();
        }
        return socketClient;
        
    }
    
    public static void main(String[] args) {
        
        Server s = new Server();
        s.start();
        s.messagge();
        //s.info();
    }
    
    
}

