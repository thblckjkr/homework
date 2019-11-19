# Import of libraries
import socket               # Import main socket library
from Utils import Protocol  # General program utilities
from Utils import UI

class Socket:
	def init(self, port):
		self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		self.s.bind(('localhost', port))
		self.s.listen(5)		#become a server socket, maximum 5 connections
	def work(self):
		while True:
			connection, address = self.s.accept()
			buf = connection.recv(64)
			connection.send(b'ECHO')
			if len(buf)> 0:
					print (buf)
					connection.close()
					break
    
s = Socket()
s.init(5678)
s.work()