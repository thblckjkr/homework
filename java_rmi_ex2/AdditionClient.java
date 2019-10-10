import java.rmi.*;
	
public class AdditionClient {
	public static void main (String[] args) {
		AdditionInterface hello;
		try {
		System.setProperty("java.security.policy","file:./security.policy");
		System.setProperty("java.rmi.server.hostname","127.0.0.1");
		
		System.setSecurityManager(new RMISecurityManager());
		
			hello = (AdditionInterface)Naming.lookup("rmi://localhost/ABC");
			int result=hello.add(9,10);
			System.out.println("Result is :"+result);

		}catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
	}
}