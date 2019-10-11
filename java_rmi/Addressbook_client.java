import java.rmi.*;
public class Addressbook_client {
	public static void main(String[] args) {
		String hostName = "localhost:1000";
		String serviceName = "Addressbook_service";
		String who = "Teo";

	/*	if(args.length == 3){
		    hostName = args[0];
		    serviceName = args[1];
		    who = args[2];
		}else if(args.length == 1){
		    who = args[0];
		}*/

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
