package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class ServerDriver {
    public static void main (String[] args){
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Input your name: ");
            String name = in.nextLine().trim();

            LocateRegistry.createRegistry(1800);

            ServerImp chatServer = new ServerImp(name);
            String url = "testing://localhost/test";
            Naming.rebind(url, chatServer);
            System.out.println("[Server] System is Ready... ");

            while (true){
                String msg = in.nextLine().trim();
                msg = "[" + chatServer.getName() +"] " + msg;
                chatServer.broadcastMessage(msg);
            }
        } catch (RemoteException |MalformedURLException e){
            System.out.println("[System] Server failed: " + e);
        }
    }
}

//LocateRegistry.createRegistry(1800);