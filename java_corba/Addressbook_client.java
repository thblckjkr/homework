import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

import java.util.Scanner;; // Streamer, to take input from keyboard

public class Addressbook_client {
    ORB orb;
	public static void main(String[] args) {
		// Generate a instance of the class
		Addressbook_client that = new Addressbook_client();

		try{
			// create and initialize the ORB
			that.orb = ORB.init(args, null);
		} catch(Exception e){
			e.printStackTrace();
		}

		// start UX interface if necessary
        that.UXi();
	}

	public void UXi(){
		Scanner stream = new Scanner(System.in);
		String request, response = "";
        do{
            System.out.println("\nCual es el correo de la persona que buscas?");
            request = stream.nextLine();
            try{
				response = this.query(request);    
            }catch(Exception e){
                System.out.println("UX:error:" + e.getMessage() );
            }
            this.showData(response);
        }while(true);
	}

	/**
     * Muestra los datos en pantalla del paquete recibido del servidor. En formato amigable al usuario
     * @param data
     */
    public void showData(String data){
        if(data.equals("NOT_FOUND")){
            System.out.println("No se ha encontrado el registro\n");
            return ;
		}
		
		if(data.equals("CONN_ERROR")){
            System.out.println("Ha habido un error de comunicacion\n");
            return ;
		}
		
        System.out.println("Los datos de la persona son:");
        System.out.print("Nombre:");
        System.out.print(data );
    }

    /**
     * Muestra los datos del servidor en formato compatible con consola
     * @param data
     * @param console
     */
    public void showData(String data, Boolean console){
        System.out.println(data);
    }

	public Boolean init(){
		return true;
	}

	public String query(String mail){
        try{
            // get the root naming context
            org.omg.CORBA.Object objRef = this.orb.resolve_initial_references("NameService");
                
            // Use NamingContextExt instead of NamingContext, part of the Interoperable naming Service.  
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            AddressbookService Addressbook = AddressbookServiceHelper.narrow(ncRef.resolve_str("AddressbookService"));
            return Addressbook.search(mail);
        }catch(Exception e){}
        return "NOT_FOUND";
	}
}
