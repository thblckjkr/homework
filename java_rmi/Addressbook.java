import java.rmi.Remote;
import java.rmi.RemoteException;

/*
    El programa no recibe argumentos al ser inicializado
*/
public interface Addressbook extends Remote {
    public String search(String mail) throws RemoteException;
}