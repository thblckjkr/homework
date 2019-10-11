import java.io.*;   // IoException (?)

import java.rmi.*;
/* import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException; */
import java.rmi.server.UnicastRemoteObject;

public class Addressbook_server extends UnicastRemoteObject  implements Addressbook {
	public Addressbook_server() throws RemoteException {
		super();
    }
    
    public String search(String mail){
        // Todo. Implement search as a class and preload the database in memory [just a named array]
        int partno = 1; int partre = 0;
        String line = null;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println(parts[partno] + ":" + mail + ":" + (parts[partno].equals(mail)) );
                if (parts[partno].equals(mail)){
                    return parts[partre];
                }
            }

            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "NOT_FOUND";
    }
    
	public static void main(String[] args) {
        // Todo. Variable with args
        String hostName = "localhost", serviceName = "Addressbook_service";

        // If args, override defaults
		if(args.length == 2){
			hostName = args[0];
			serviceName = args[1];
        }        

        System.setProperty("java.security.policy","file:./security.policy");
        // Cannot thest this on a single macine. This IP needs to change? I think not
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
                    
        System.setSecurityManager(new RMISecurityManager());

		try{
			Addressbook addr = new Addressbook_server();
			Naming.rebind("rmi://"+hostName+"/"+serviceName, addr);
			System.out.println("HelloWorld RMI Server is running...");
		}catch(Exception e){}
	}
}
