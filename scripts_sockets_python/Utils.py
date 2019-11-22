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
	Validates if the query complies with the protocol
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

	# Teo Gonzalez Calzada [@thblckjkr] 2019/04/07
	# Class to act as an interface to the user

class UI:
	colors = {
		'header' : '\033[95m', 'info' : '\033[94m', 'success' : '\033[92m', 'warning' : '\033[93m',
		'error' : '\033[91m', 'ENDC' : '\033[0m', 'bold' : '\033[1m', 'underline' : '\033[4m'
	}
	def __init__(self):
		self.show("Inicializando interfaz...\n", 'info')
		
	def show(self, message, type = 'info'):
		color = self.colors.get(type, "Invalid month")
		print (color + message + self.colors['ENDC'])

	def askYesNo(self, message):
		self.show(message, 'info')
		message = "Presiona: \n [0] para NO\n [1] para SI\n Tu respuesta: "
		while(True):
			temp = input(message)
			if temp != '0' and temp != '1':
				self.show("No has seleccionado un valor coherente", 'warning')
			else:
				return int(temp)

	def askNumber(self, message, t ='int'):
		self.show(message, 'info')
		message = ""
		while(True):
			temp = input("")
			try:
				if t == 'float':
					xtemp = float(temp)
				else:
					xtemp = int(temp)
				
				return xtemp
			except ValueError:
				self.show("No has seleccionado un valor coherente", 'warning')

	def ask(self, message):
		self.show(message, 'info')
		message = ""
		temp = input("")
		return temp