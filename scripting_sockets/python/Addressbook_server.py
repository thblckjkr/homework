# Import of libraries
import socket					# Import main socket library
import atexit					# Execute smething before exit
from Utils import Protocol	# General program utilities
from Utils import UI

class Socket:
	def init(self, port):
		self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		self.s.bind(('localhost', port))
		self.s.listen(5)		#become a server socket, maximum 5 connections

	def close(self):
		self.connection.close()

	def work(self):
		while True:
			self.connection, address = self.s.accept()
			buf = self.connection.recv(64)
			self.connection.send(b'ECHO')
			if len(buf)> 0:
					print (buf)
					self.connection.close()
					break

s = Socket()
s.init(5678)
s.work()

def exit_handler():
	s.close()

atexit.register(exit_handler)