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
	# Validates the query complies with the protocol
	#
	def self.Validate(data, query)
		# Fast check with regex. Checkthe information is well formed
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

	def getData(data)
		unless data.nil? or data.empty?
			return data[2..-1]
		end
		return "ERROR"
	end
end


# Teo Gonzalez Calzada [@thblckjkr] 2019/04/07
# Class to act as an interface to the user

class UI
	@@colors = {
		"header" => "\033[95m", "info" => "\033[94m", "success" => "\033[92m", "warning" => "\033[93m",
		"error" => "\033[91m", "ENDC" => "\033[0m", "bold" => "\033[1m", "underline" => "\033[4m"
	}

	def initialize()
		self.show("Inicializando interfaz...\n", 'info')
	end

	def show(message, type = 'info')
		color = @@colors.select{ |name, color| name == type}

		t = color[type] + message + @@colors['ENDC']
		puts t
	end

	def askYesNo(message)
		self.show(message, 'info')
		message = "Presiona: \n [0] para NO\n [1] para SI\n Tu respuesta: "
		self.show(message, 'info')
		while(true)
			temp = gets.chomp
			if temp != "0" and temp != "1"
				self.show("No has seleccionado un valor coherente", 'warning')
			else
				if temp == "1"
					return true
				else
					return false
				end
			end
		end
	end

	def ask(message)
		self.show(message, 'info')
		message = ""
		temp = gets
		return temp
	end
end