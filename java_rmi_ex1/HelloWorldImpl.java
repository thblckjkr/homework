import java.rmi.*;
/*import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
*/
import java.rmi.server.UnicastRemoteObject;

public class HelloWorldImpl extends UnicastRemoteObject  implements HelloWorld {
	public HelloWorldImpl() throws RemoteException {
		super();
	}
	public String sayHello(String who) throws RemoteException {
		return "Hello "+who+" from your friend RMI Server :-)";
	}
	public static void main(String[] args) {
		String hostName = "localhost:1000";
		String serviceName = "HelloWorldService";
		/*if(args.length == 2){
			hostName = args[0];
			serviceName = args[1];
		}*/
		System.setProperty("java.security.policy","file:./security.policy");
        System.setProperty("java.rmi.server.hostname","127.0.0.1");
                    
        System.setSecurityManager(new RMISecurityManager());

		try{
			HelloWorld hello = new HelloWorldImpl();
			Naming.rebind("rmi://"+hostName+"/"+serviceName, hello);
			System.out.println("HelloWorld RMI Server is running...");
		}catch(Exception e){}
	}
}
