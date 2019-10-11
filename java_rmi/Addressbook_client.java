import java.rmi.*;
public class Addressbook_client {
	public static void main(String[] args) {
		String hostName = "localhost:1000", serviceName = "Addressbook_service";
		String who = "Teo";
		Boolean ux = false;

		if(args.length == 3){
			// If three args, then run a single client instance (usefull for tests)
		    hostName = args[0];
		    serviceName = args[1];
		    who = args[2];
		}else if(args.length == 1){
			// If just one arg, then run a single client (tests)
		    who = args[0];
		}else if(args.length == 2){
			// Just override hostname, and run on UX (Default)
			hostName = args[0];
			serviceName = args[1];
			UX = true;
		}

		// Todo. Classify (cambiarlo a clases, pues)
		System.setProperty("java.security.policy","file:./security.policy");
		System.setProperty("java.rmi.server.hostname","127.0.0.1");
		
        System.setSecurityManager(new RMISecurityManager());

		try{
		    Addressbook addr = (Addressbook)Naming.lookup("rmi://"+hostName+"/"+serviceName);
		    System.out.println(addr.search(who));
		}catch(Exception e){
		    e.printStackTrace();
		}
	}
}
