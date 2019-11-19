# Import of libraries
import socket               # Import main socket library
from Utils import Protocol  # General program utilities

p = Protocol()

X = p.CreateMessage("Mi mama me mima", True)

"""
clientsocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
clientsocket.connect(('localhost', 8089)) 
  
clientsocket.send('hello')
"""