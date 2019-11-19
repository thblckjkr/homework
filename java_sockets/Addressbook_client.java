/*
* I'm just making a proper docs of this program
* This was just to make things easier to me and a friend, but i think it does not hurt to share
* @thblckjkr [2019/09/07]
*/
// Se importan los paquetes

// https://docs.oracle.com/javase/10/docs/api/java/io/package-summary.html
import java.net.*;  // DatagramSocket, DatagramPAcket, SocketException, InetAddress

// https://docs.oracle.com/javase/10/docs/api/java/io/package-summary.html
import java.io.*;   // IoException (?)
import java.util.Scanner;

/*
* Cliente de tarea 5
* Si es llamado con argumentos, este hara una simple peticion al servidor y terminara sin mas interaccion del usuario
* Orden de los argumentos:
*  [Servidor] [Nombre a buscar]
*/
public class Addressbook_client
{
    Protocol proto = new Protocol();
    String serverAddress = "";
    int serverPort = 5678;

    /**
     * Funcion principal.
     * En base a los argumentos ejecuta un programa de consola comatible con usuarios o no
     * @param args
     */
    public static void main(String args[]){
        // Change a this for that. A simple spell, but quite unbreakable
        Addressbook_client that = new Addressbook_client();
        try{
            String data, response;
            if (args.length > 0){
                that.serverAddress = args[0];
                data = that.proto.CreateMessage(args[1], true);
                response = that.exchangeMessage(data);
                that.showData(response, true);
            }else{
                that.UX();
            }
        }catch(Exception e){
            System.out.println("main:error:" + e.getMessage() );
        }
    }
    
    /**
     * Entrada principal del sistema cuando un usuario lo llama
     */
    public void UX(){
        String server, request, data, response = "";
        Scanner stream = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de consulta de correos");
        System.out.println("Para empezar, escribe la direccion del servidor");
        server = stream.nextLine();

        serverAddress = server;

        do{
            System.out.println("Cual es el nombre de la persona que buscas?");
            request = stream.nextLine();
            try{
                data = this.proto.CreateMessage(request, true);
                response = this.exchangeMessage(data);
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
        if(data == "ERROR"){
            System.out.println("Ha habido un error de comunicacion");
            return ;
        }

        System.out.println("Los datos de la persona son:\n");
        System.out.print("Nombre:");
        System.out.print(data + "\n");
    }

    /**
     * Muestra los datos del servidor en formato compatible con consola
     * @param data
     * @param console
     */
    public void showData(String data, Boolean console){
        data = this.proto.getData(data);
        System.out.println("Data received:" + data);
        if (!this.proto.validate(data, false)){
            System.out.println("BAD_DATA");
            return ;
        }

        if(data == "ERROR"){
            System.out.println("ERR_CONN");
            return ;
        }
        
        System.out.println(data);
    }

    /**
     * Funcion para establecer una conexion de sockets con el servidor, enviarle la cadena y recibir la respuesta
     * @param data Los datos a enviar
     * @return [String] La informacion que el servidor responde, ERROR en caso de excepcion
     */
    public String exchangeMessage(String data){
        String response;
        DatagramSocket aSocket = null;
        try{
            // This class receives a well constructed request
            // Intentamos inicializar el socket
            aSocket = new DatagramSocket();
                
            // Mensaje [argumento 0]
            byte []m = data.getBytes("utf-8"); // Convertir el texto a enviar a Bytes en UTF-8, porque el DatagramPacket espera un arreglo de bytes como argumento
            int tam = data.length(); // Tamaño del mensaje a enviar

            // IP del Host [argumento 1]
            InetAddress aHost = InetAddress.getByName(this.serverAddress); // Convertir string a un objeto especial para almacenar la IP
            // Puerto en el que corre el servidor

            // Paquete a enviar
            DatagramPacket request = new DatagramPacket(m, tam, aHost, this.serverPort); // Aqui se arma el mensaje

            // Enviar mensaje
            aSocket.send(request);

            // Buffer de respuesta
            byte[] buffer = new byte[257]; // Creamos un buffer en el que recibiremos la respuesta

            // Almacenamos la respuesta en un buffer
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            // Almacenamos la respuesta del socket en el que enviamos el mensaje, en el paquete reply
            aSocket.receive(reply);

            // Regresamos la respuesta del servidor
            response = new String(reply.getData());
            response = response.trim();
            
            return response;
        }
        catch (SocketException e){
            // Si el socket falla, nos informa
            System.out.println("Socket: " + e.getMessage());
            return "ERROR";
        }
        catch (IOException e){
            // Si falla... Algo, nos informa
            System.out.println("IO: " + e.getMessage());
            return "ERROR";
        }
        finally
        {
            // Si se pudo inicializar el socket, lo destruye al finalizar
            if(aSocket != null)
                aSocket.close();
        }
    }

}