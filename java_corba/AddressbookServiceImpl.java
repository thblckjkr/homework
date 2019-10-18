import java.io.*; // BufferedReader
import java.rmi.Remote;
import java.rmi.RemoteException;


public class AddressbookServiceImpl extends AddressbookServicePOA {
	public AddressbookServiceImpl() {
		super();
	}

    public String search(String mail){
        // Todo. Implement search as a class and preload the database in memory [just a named array]
        int partno = 1; int partre = 0;
        String line = null;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // System.out.println(parts[partno] + ":" + mail + ":" + (parts[partno].equals(mail)) );
                if (parts[partno].equals(mail)){
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
