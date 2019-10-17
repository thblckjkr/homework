import java.rmi.*;
import java.util.Scanner;; // Streamer, to take input from keyboard

public class Addressbook_client {
	private String hostName = "127.0.0.1:1000", serviceName = "Addressbook_service";

	public static void main(String[] args) {
		String who = "Teo";
		Boolean ux = false;

		// Generate a instance of the class
		Addressbook_client that = new Addressbook_client();
		try{
			that.init();
		} catch(Exception e){
			e.printStackTrace();
		}

		// Grab arguments and proceed as necessary
		if(args.length == 3){
			// If three args, then run a single client instance (usefull for tests)
		    that.hostName = args[0];
		    that.serviceName = args[1];
			who = args[2];
		}else if(args.length == 2){
			// Just override hostname, and run on UX (Default)
			that.hostName = args[0];
			that.serviceName = args[1];
			ux = true;
		}else if(args.length == 1){
			// If just one arg, then run a single client (tests)
		    who = args[0];
		}else{
			// Seria posible el caso de 0 argumentos, iniciar la UX con los datos default del servidor. Pero meh
			System.out.println("Por favor revise el archivo readme.md, para ver como correr el programa");
		}


		// start UX interface if necessary
		if (ux == true){
			that.UXi();
		} else {
			try{
				String response = "";
				response = that.query(who);
				that.showData(response, true);
			}catch(Exception e){
                System.out.println("main:error:" + e.getMessage() );
            }
		}
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
			System.setProperty("java.security.policy","file:./security.policy");
			System.setProperty("java.rmi.server.hostname","127.0.0.1");
			System.setSecurityManager(new RMISecurityManager());

		    Addressbook addr = (Addressbook)Naming.lookup("rmi://"+this.hostName+"/"+this.serviceName);
		    return addr.search(mail);
		}catch(Exception e){
			System.out.println("Parece que el servidor no esta corriendo, o se ha detenido");
		    e.printStackTrace();
		}
		return "CONN_ERROR";
	}
}
