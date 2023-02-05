
package socket;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    
    Socket sendSocket = null;
    
    int porta = 2000;
    
    DataInputStream in;
    DataOutputStream out;
    BufferedReader tastiera;
    
    InetAddress ip;
    String hostname;    
    
    public void messagge(){
        try {
            System.out.print("[0] - Messaggio da inviare : ");
            tastiera = new BufferedReader(new InputStreamReader(System.in)); //faccio tipo uno scanner 
            String messaggio = tastiera.readLine();
            System.out.println("[1] - Invio del messaggio...");
            out.writeBytes(messaggio +"\n");
            System.out.println("[2] - In attesa di risposta...");
            String ricevuta = in.readLine();
            System.out.println("[3] - Risposta del server : " + ricevuta);
        } 
        
        catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Socket conntection(){
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("[0.1] - Indirizzo IP : "+Inet4Address.getLocalHost().getHostAddress());
            System.out.println("[0.2] - Hostname : " + hostname); 
            Socket sendSocket = new Socket(InetAddress.getLocalHost(),porta); //creo un canale logico Client/Server
            in = new DataInputStream(sendSocket.getInputStream());
            out = new DataOutputStream(sendSocket.getOutputStream());            
        } 
              
        catch (UnknownHostException err) { //errore dovuto all'impposibilita di riconoscere l'host
            System.err.println("Errore DNS");
        } 
        
        catch (ConnectException err){
            System.err.println("Errore di connessione");
        }        
        
        catch (Exception err) {
            //err.printStackTrace(); //metodo che reindirizza gli errori al System.err
            System.err.println("Impossibile stabilire una connsessione");
        }
        return sendSocket;
    }

    public static void main(String[] args) {
        
        
        Client c = new Client();
        c.conntection();
        c.messagge();
        
        
    }
    
}
