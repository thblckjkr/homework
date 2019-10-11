#!/bin/bash
# TODO: Add defaults
# read -p "Escribe la direccion IP del servidor" ip
# read -p "Escribe el puerto que usaremos en el sistema" port
# read -p "Escribe el nombre del servicio a usar" serviceName
serviceName="Addressbook_service"
ip="127.0.0.1"
port="1000"

echo "Killing previous runs"
# Fix, needed sudo on rmiregistry to access ports
sudo killall rmiregistry; sudo killall java

echo "Running servers"
# Put the rmiregistry and the server in a background process
sudo rmiregistry $port &
java Addressbook_server $ip:$port $serviceName &

echo "Running client in 2 seconds (server startup time)"
sleep 3s

# TODO: Automatize checking if response is ok
echo "Empezando pruebas"
java Addressbook_client $ip:$port $serviceName LB9XK
java Addressbook_client $ip:$port $serviceName NZ4P
java Addressbook_client $ip:$port $serviceName NULL
java Addressbook_client $ip:$port $serviceName 0
java Addressbook_client $ip:$port $serviceName String
java Addressbook_client $ip:$port $serviceName CON_ERROR
java Addressbook_client $ip:$port $serviceName NOT_FOUND