# Programa de Addressbook en JAVA con RMI

> Teo González Calzada. [@thblckjkr] [2019/10/17]

## Decisiones

En vez de la función `.equals`, estoy consciente de que pude haber utilizado `.equalsIgnoreCase` para evitar problemas cuando el case no fuera el mismo. Sin embargo, en el RFC se especifica que los mails son case sensitive, por lo tanto, y tomando en cuenta que puede ser probable que usemos mails en vez de strings arbitrarios, he decidido dejarlo en `.equals`

## Código

Se incluye el código compilado, para evitar complicaciones.

Los archivos base son

```
Addressbook.idl
Addressbook_client.java
Addressbook_server.java
AddressbookServiceImpl.java
compile.sh
names.txt
```

En este [link](https://github.com/thblckjkr/homework/tree/master/java_corba) se encuentra el código sin compilar, en un repositorio de GitHub.

## Estructura del sistema

Este programa es basicamente un copy-paste de la tarea *RMI*.

## Compiling

Sólo es necesario correr el programa [compile.sh](compile.sh), el cual compila y ejecuta el programa

```sh
./compile.sh
```

## Errores conocidos

Los errores posibles son que el cliente reciba un `CON_ERROR` y `NOT_FOUND`. Tambien es posible que el cliente no pueda conectarse al servicio si el servidor no esta corriendo. El cual mostrara un mensaje de error apropiado.

> Es posible que si un usuario tiene el nombre de *CON_ERROR* o *NOT_FOUND* nos de un falso error, debido a como funciona el programa. Ahora que lo pienso, podria ser evitado si simplemente se devolviera una clase en vez de un String como tal. Pero por el momento no tengo tiempo para investigar la implementacion de ello.