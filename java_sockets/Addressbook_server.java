// Se importan los paquetes
// https://docs.oracle.com/javase/10/docs/api/java/io/package-summary.html
import java.net.*;  // DatagramSocket, DatagramPAcket, SocketException, InetAddress

// https://docs.oracle.com/javase/10/docs/api/java/io/package-summary.html
import java.io.*;   // IoException (?)

/*
    El programa no recibe argumentos al ser inicializado
*/
public class Addressbook_server {
    int serverPort = 5678;
    Protocol proto = new Protocol();

    public static void main(String args[]){
        System.out.println("Server is running");
        while(true){
            Addressbook_server s = new Addressbook_server();
            s.receive();
        }
    }


    public void receive(){
        // Crear un objeto (basicamente un manager de sockets)
        DatagramSocket aSocket = null;
        String response, query, mail;
        try {
            // Crea un nuevo socket, que escucha en el puerto 5678
            aSocket = new DatagramSocket(this.serverPort);

            // Buffer donde recibiremos datos
            byte[] buffer = new byte[100];

            while(true){
                // Creas un paquete donde recibes datos
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);

                // Recibes los datos del socket en el paquete
                aSocket.receive(request);
                query = new String(request.getData());
                query = query.trim();

                if( !proto.validate(query, true)){
                    response = "INVALID_Q";
                }

                mail = proto.getData(query);
                response = this.search(mail);
                try{
                    response = proto.CreateMessage(response, false);
                }catch(Exception e){
                    System.out.println("main:error:" + e.getMessage() );
                }

                // Creas un paquete para responder, con los datos del que envia

                DatagramPacket reply = new DatagramPacket(
                    response.getBytes("utf-8"),
                    response.length(),
                    request.getAddress(),
                    request.getPort()
                );

            // Respondes por el mismo socket donde recibiste los datos
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            // Si el socket falla, nos informa
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            // Si falla... Algo, nos informa
            System.out.println("IO: " + e.getMessage());
        }
        finally {
            // Si se pudo inicializar el socket, lo destruye al finalizar
            if(aSocket != null)
                aSocket.close();
        }
    }

    public String search(String mail) {
        int partno = 1; int partre = 0;
        String line = null;
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println(parts[partno] + ":" + mail + ":" + (parts[partno].trim() == mail));
                if (parts[partno].trim() == mail){
                    return parts[partre];
                }
            }

            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "NOT_FOUND";
    }
}
