# Compile common interface
idlj -fall HelloWorld.idl

# Compile everything on current directory to corba
javac *.java -cp ./corba

killall orbd
killall java

# Start something (?)
sudo orbd -ORBInitialPort 1070 -ORBInitialHost localhost

# Run server on background
java HelloWorldServer -ORBInitialPort 1900 -ORBInitialHost localhost &

# Run client
java HelloWorldClient -ORBInitialPort 1900 -ORBInitialHost localhost
