import java.rmi.*;
public class RMIClient {
	public static void main(String[] args) {
		String hostName = "localhost:1000";
		String serviceName = "HelloWorldService";
		String who = "Nahitt";
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
		    HelloWorld hello = (HelloWorld)Naming.lookup("rmi://"+hostName+"/"+serviceName);
		    System.out.println(hello.sayHello(who));
		}catch(Exception e){
		    e.printStackTrace();
		}
	}
}
