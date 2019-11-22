# Import of libraries

class Protocol
	def CreateMessage(data, query)
		if data.length > 255
			return false
		end
		
		if query
			msg = "Q"
		else
			msg = "R"
		end

		size = data.length
		csize = size.chr

		msg = msg + csize + data

		if (self.class.Validate(msg, query) != true)
			puts "Estas intentando enviar un mensaje no valido. Solo se permite de la a-Z y 0-9"
			return false
		end

		return msg
	end

	#
	# Validates if the query complies with the protocol
	#
	def self.Validate(data, query)
		# Fast check with regex. Check if the information is well formed
		if (not /^Q.{0,1}.{1,255}$/.match(data) and query == true )
			return false
		end
		
		if (not /^R.{0,1}.{1,255}$/.match(data) and query != true )
			return false
		end

		# Validate the size (Sort of checksum)
		size = data[1].ord()
		info = data[2..-1]
		if (size != info.length)
			return false
		end

		return true
		end
	end

	def getData(data)
		data = data.strip()
		return data.substring[1]
	end


# Teo Gonzalez Calzada [@thblckjkr] 2019/04/07
# Class to act as an interface to the user
=begin
class UI:
	@colors = {
		'header' : '\033[95m', 'info' : '\033[94m', 'success' : '\033[92m', 'warning' : '\033[93m',
		'error' : '\033[91m', 'ENDC' : '\033[0m', 'bold' : '\033[1m', 'underline' : '\033[4m'
	}
	def initialize(self):
		self.show("Inicializando interfaz...\n", 'info')
	end

	def show(message, type = 'info'):
		color = @colors.get(type, "Invalid month")
		print (color + message + self.colors['ENDC'])
	end

	def askYesNo(message):
		self.show(message, 'info')
		message = "Presiona: \n [0] para NO\n [1] para SI\n Tu respuesta: "
		while(true):
			temp = input(message)
			if temp != '0' and temp != '1':
				self.show("No has seleccionado un valor coherente", 'warning')
			else:
				return int(temp)
			end
		end
	end

	def askNumber(message, t ='int'):
		self.show(message, 'info')
		message = ""
		while(true):
			temp = input("")
			try:
				if t == 'float':
					xtemp = float(temp)
				else:
					xtemp = int(temp)
				
				return xtemp
			except ValueError:
				self.show("No has seleccionado un valor coherente", 'warning')
				end

	def ask(message):
		self.show(message, 'info')
		message = ""
		temp = input("")
		return temp
=end
