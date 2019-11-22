require 'socket'
require_relative "Utils"

p = Protocol.new 

a = p.CreateMessage("Message", true)

puts a

s = TCPSocket.new 'localhost', 5678

while line = s.gets # Read lines from socket
  puts line         # and print them
end

s.close             # close socket when done