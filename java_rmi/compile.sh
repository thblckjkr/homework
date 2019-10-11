#!/bin/bash
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
sudo rmiregistry 1000 &
java Addressbook_server &

echo "Running client in 2 seconds (server startup time)"
sleep 2s
java Addressbook_client