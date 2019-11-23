require 'socket'				# Import main socket library
require_relative "Utils"	# General program utilities

class Socket
	def initialize(protocol)
		@pr = protocol
	end

	def init(ip, port)
		@ip = ip
		@port = port
		# @s = TCPSocket.new ip, port
	end

	def exchangeMessage(message)
		@s = TCPSocket.new @ip, @port
		@s.send(message, 0)
		x = @s.recvmsg

		@s.close
		return @pr.getData( x[0] )
	end
end

p = Protocol.new 
s = Socket.new p
ui = UI.new

ui.show("Bienvenido al sistema de consulta de correos")
server = ui.ask("Para empezar, escribe la direccion IP del servidor [127.0.0.1]")

if server == "\n"
	server = "127.0.0.1"
end

s.init(server, 5678)

while(true)
	request = ui.ask("Cual es el correo de la persona que buscas?")
	data = p.CreateMessage(request, true)
	response = s.exchangeMessage(data)

	if response == "NOT_FOUND"
		ui.show("Mail not found", 'warning')
	else
		ui.show(response, 'success')
	end

	cont = ui.askYesNo("Deseas continuar con la ejecución")
	if (cont == false)
		break
	end
end