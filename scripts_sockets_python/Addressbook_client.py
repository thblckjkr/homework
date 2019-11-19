# Import of libraries
import socket               # Import main socket library
from Utils import Protocol  # General program utilities
from Utils import UI

class Socket:

   def init(self, ip, port):
      self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
      self.s.connect((ip, port)) 

   def exchangeMessage(self, message):
      self.s.send(message)
      x = self.s.recvmsg()

      print (x)
      return x

p = Protocol()
s = Socket()
ui = UI()


X = p.CreateMessage("Mi mama me mima", True)

ui.show("Bienvenido al sistema de consulta de correos")
server = ui.ask("Para empezar, escribe la direccion IP del servidor")

s.init(server, 5678)

while(True):
   request = ui.ask("Cual es el nombre de la persona que buscas?")
   data = p.CreateMessage(request, True)
   response = s.exchangeMessage(data)
   ui.show(response)

   cont = ui.askYesNo("Deseas continuar con la ejecución")
   if (not cont):
      exit