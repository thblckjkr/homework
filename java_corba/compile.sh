# Compile common interface
echo "Generating corba things"
idlj -fall Addressbook.idl || exit

# Compile everything on current directory to corba
echo "Compiling java"
javac *.java || exit

killall orbd
killall java

## orbd needs this (?)
sudo su

# Start something (?)
echo "Starting background shit"
sudo orbd -ORBInitialPort 1070 -ORBInitialHost localhost &

# Run server on background
echo "Starting server"
java HelloWorldServer -ORBInitialPort 1070 -ORBInitialHost localhost &

# Run client
echo "Starting Client"
java HelloWorldClient -ORBInitialPort 1070 -ORBInitialHost localhost
