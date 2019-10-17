
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
public class HelloWorldClient {
   public static void main(String[] args) {
      try{
        // create and initialize the ORB
       ORB orb = ORB.init(args, null);
	    
        // get the root naming context
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	        
        // Use NamingContextExt instead of NamingContext, part of the Interoperable naming Service.  
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

        // resolve the Object Reference in Naming
        HelloWorldService helloWorld = 	HelloWorldServiceHelper.narrow(ncRef.resolve_str("HelloWorldService"));

        System.out.println(helloWorld.sayHello("Raj"));
      }catch(Exception e){}
  }
}
