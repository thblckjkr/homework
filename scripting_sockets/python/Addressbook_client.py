# Import of libraries
import socket               # Import main socket library
from Utils import Protocol  # General program utilities
from Utils import UI

class Socket:
	def __init__(self, protocol):		
		self.pr = protocol

	def init(self, ip, port):
		self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		self.s.connect((ip, port)) 

	def exchangeMessage(self, message):

		self.s.send( message.encode('utf-8') )
		x = self.s.recvmsg(254)

		return self.pr.GetData( x[0].decode('utf-8') )

"""
	Main code
"""

p = Protocol()
s = Socket(p)
ui = UI()

ui.show("Bienvenido al sistema de consulta de correos")
server = ui.ask("Para empezar, escribe la direccion IP del servidor [127.0.0.1]")

server = "127.0.0.1" if server == "" else server

s.init(server, 5678)

while(True):
	request = ui.ask("Cual es el correo de la persona que buscas?")
	data = p.CreateMessage(request, True)
	response = s.exchangeMessage(data)
	if response == "NOT_FOUND":
		ui.show("Mail not found", 'warning')
	else:
		ui.show(response)

	cont = ui.askYesNo("Deseas continuar con la ejecución")
	if (cont == False):
		break