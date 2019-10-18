# Compile common interface
echo "Generating corba things"
idlj -fall Addressbook.idl || exit

# Compile everything on current directory to corba
echo "Compiling java"
javac *.java || exit

killall orbd
killall java

# Start something (?)
sleep 2s
echo "Starting background shit"
orbd -ORBInitialPort 1070 -ORBInitialHost localhost &

# Run server on background
sleep 2s
echo "Starting server"
java Addressbook_server -ORBInitialPort 1070 -ORBInitialHost localhost &

sleep 2s
# Run client
echo "Starting Client"
java Addressbook_client -ORBInitialPort 1070 -ORBInitialHost localhost
