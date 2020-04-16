package client;
 
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import server.IServer;
 
public class ClientDriver {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = in.nextLine().trim();
 
            IServer chatServer = (IServer)Naming.lookup("testing://localhost/test");
            ClientImp chatClient= new ClientImp(name, chatServer);
 
            while (true) {
                String msg = in.nextLine().trim();
                msg = "[" + chatClient.getName() + "] " + msg;
                chatServer.broadcastMessage(msg);
            }
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            System.out.println("[System] Server failed: " + e);
        }
    }
}

//IServer chatServer = (Iserver)Naming.lookup("testing://localhost/test");
