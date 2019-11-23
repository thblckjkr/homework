# Import of libraries
require 'socket'					# Import main socket library
require_relative "Utils"		# General program utilities
require_relative "Database"

class Socket
	def initialize(port, db, pr, ui)
		@@ui = ui
		@@db = db
		@@pr = pr

		ui.show("Opening port")

		@@server = TCPServer.new port # Server bound to port 

		ui.show("Ready to accept connections")
	end

	def work()
		while(true)
			client = @@server.accept # Wait for a client to connect
			Thread.start(client) do | connection |
				data = connection.recvmsg
				info = @@pr.getData(data[0])
				
				temp = @@db.Search( info )

				if temp != false
					connection.puts( @@pr.CreateMessage(temp, false) )
				else
					connection.puts( @@pr.CreateMessage( "NOT_FOUND", false ) )
				end
				connection.close()
			end
		end
	end
end

u = UI.new
p = Protocol.new
d = Database.new u
s = Socket.new 5678, d, p, u

# Initialize the socket
s.work()