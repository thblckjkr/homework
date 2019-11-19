# Import of libraries
import re # For protocol checking with regex


class Protocol:
   def CreateMessage(self, data, query):
      if len(data) > 255:
         return False
      
      msg = "Q" if query else "R"

      size = len(data)
      csize = chr(size)

      msg = msg + csize + data

      if (self.Validate(msg, query) != True):
         print("Estas intentando enviar un mensaje no valido. Solo se permite de la a-Z y 0-9")
         return False
      
      return msg
   
   """ 
   * Validates if the query complies with the protocol
   """
   def Validate(self, data, query):
      # Fast check with regex. Check if the information is well formed
      t = re.search("^Q.{0,1}.{1,255}$", data)
      if (t is None and query == True ):
         return False

      t = re.search("^R.{0,1}.{1,255}$", data)
      if (t is None and query != True ):
         return False

      # Validate the size (Sort of checksum)
      size = ord(data[1])
      info = data[2:]
      if (size != len(info)):
         return False

      return True

   def getData(self, data):
      data = data.strip()
      return data.substring[1]