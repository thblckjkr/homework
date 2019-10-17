import java.io.*;   // IoException (?)

public class Addressbook_server extends UnicastRemoteObject  implements Addressbook {
	public Addressbook_server() throws RemoteException {
		super();
    }
    
	public static void main(String[] args) {
        // Todo. Variable with args
        String hostName = "localhost:1000", serviceName = "Addressbook_service";

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
			System.out.println("Corriendo servidor de direcciones");
		}catch(Exception e){}
	}
}
