#!/bin/bash
echo "Killing previous runs"
# Fix, needed sudo on rmiregistry to access ports
sudo killall rmiregistry; sudo killall java

echo "Running servers"
# Put the rmiregistry and the server in a background process
sudo rmiregistry $port &
java Addressbook_server $ip:$port $serviceName &

echo "Running client in 2 seconds (server startup time)"
sleep 2s
java Addressbook_client $ip:$port $serviceName LB9XK

# java Addressbook_client $ip:$port $serviceName NZ4P
# java Addressbook_client $ip:$port $serviceName NULL
# java Addressbook_client $ip:$port $serviceName 0
# java Addressbook_client $ip:$port $serviceName String
# java Addressbook_client $ip:$port $serviceName CON_ERROR
# java Addressbook_client $ip:$port $serviceName NOT_FOUND