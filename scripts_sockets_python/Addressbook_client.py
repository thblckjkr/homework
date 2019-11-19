# Import of libraries
import socket               # Import main socket library
from Utils import Protocol  # General program utilities
from Utils import UI

class Socket:

   def init(self, ip, port):
      self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
      self.s.connect((ip, port)) 

   def exchangeMessage(self, message):

      self.s.send(message.encode('utf-8'))
      x = self.s.recvmsg(255)

      print (x[0])
      return x[0].decode('utf-8')

"""
   Main code
"""

p = Protocol()
s = Socket()
ui = UI()

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