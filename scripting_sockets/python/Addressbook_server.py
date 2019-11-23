# Import of libraries
import socket	# Import main socket library
import atexit	# Execute smething before exit
import multiprocessing	# For multiple sockets
from Utils import *		# General program utilities
from Database import Database

class Socket:
	def __init__(self, port, db, pr, ui):
		self.ui = ui
		self.db = db
		self.pr = pr

		ui.show("Opening port")
		self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		self.s.bind(('localhost', port))
		self.s.listen(5)		#become a server socket, maximum 5 connections
		ui.show("Ready to accept connections")

	def close(self):
		self.connection.close()

	def handle(self):
		try:
			while True:
				buf = self.connection.recv(255)
				if len(buf)> 0:
					self.ui.show("serving connection")

					temp = self.db.Search( self.pr.GetData( buf.decode('utf-8') ) )
					if temp != False:
						self.connection.send( self.pr.CreateMessage(temp, False).encode("UTF-8") )
					else:
						self.connection.send( self.pr.CreateMessage( "NOT_FOUND", False ).encode("UTF-8") )
		except:
			pass
		finally:
			self.connection.close()

	def work(self):
		while True:		
			self.connection, address = self.s.accept()
			process = multiprocessing.Process(
					target=self.handle)
			process.daemon = True
			process.start()

u = UI()
p = Protocol()
d = Database(u)
s = Socket(5678, d, p, u)

# Initialize the socket
s.work()

def exit_handler():
	s.close()

atexit.register(exit_handler)