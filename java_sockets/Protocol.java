import java.io.*;

public class Protocol{
    public String CreateMessage(String data, Boolean query) throws Exception{
        data = data.trim();
        if (data.length() > 255)
            throw new Exception("Too much data");

        String msg = new String();
        msg = (query) ? "Q" : "R";

        int size = data.length();
        char csize = (char)size;

        msg = msg + csize + data;

        if (!this.validate(msg, query))
            throw new Exception("Estas intentando enviar un mensaje no valido. Solo se permite de la a-Z y 0-9");
        
        return msg;
    }

    /**
    * Validates if the query is complaining with the protocol
    * 
    */
    public Boolean validate(String data, Boolean query){
        // Fast check with regex. Check if the information is well formed
        // \W means anything that is not included in \w. So, we are (hopeffully) testing for everything using both of them

        if (!data.matches("Q(.*)") && query)
            return false;

        if (!data.matches("R(.*)") && !query)
            return false;


        // Validate the size (Sort of checksum)
        int size = (int)data.charAt(1);
        String info = new String(data.substring(2));
        if (size != info.length())
            return false;
        
        return true;
    }

    public String getData(String data){
        data = data.trim();
        return new String(data.substring(2));
    }
}