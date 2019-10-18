import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class Addressbook_server{
    
	public static void main(String[] args) {
	    try{
	        // create and initialize the ORB
	        ORB orb = ORB.init(args, null);
	        // get reference to rootpoa & activate the POAManager
	        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			
	        // create servant and get the CORBA reference of it
	        AddressbookServiceImpl AImpl = new AddressbookServiceImpl();
	        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(AImpl);
			AddressbookService aService = AddressbookServiceHelper.narrow(ref);
			
	        // get the root naming context and narrow it to the NamingContextExt object
	        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
	        // bind the Object Reference in Naming
	        NameComponent path[] = ncRef.to_name("AddressbookService");
	        ncRef.rebind(path, aService);
	        // wait for invocations from clients
	        orb.run();
	      } catch (Exception e) {}
	}
}
