#!/bin/bash
# Todo. Add defaults
# read -p "Escribe la direccion IP del servidor" ip
# read -p "Escribe el puerto que usaremos en el sistema" port
# read -p "Escribe el nombre del servicio a usar" serviceName
serviceName="Addressbook_service"
ip="localhost"
port="1000"

# Just compile and run the program
echo "Compiling client"
javac Addressbook_client.java || exit
echo "Compiling server"
javac Addressbook_server.java || exit

echo "Creating rmic service"
rmic Addressbook_server

echo "Killing previous runs"
# Fix, needed sudo on rmiregistry to access ports
sudo killall rmiregistry; sudo killall java

echo "Running servers"
# Put the rmiregistry and the server in a background process
sudo rmiregistry $port &
java Addressbook_server $ip:$port $serviceName&

echo "Running client in 2 seconds (server startup time)"
sleep 2s
java Addressbook_client $ip $port