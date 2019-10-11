# Programa de Addressbook en JAVA con RMI

> Entiendo que puede ser un poco difícil de creer que no haya entregado las tareas anteriores de forma funcional y esta si la entregue... Pero me tomó más tiempo del que me gustaría admitir que la comparación de strings en Java es mas sencilla con String.equals que con un ==
>
> Teo González Calzada. [@thblckjkr] [2019/10/10]

## Decisiones

En vez de la función `.equals`, estoy consciente de que pude haber utilizado `.equalsIgnoreCase` para evitar problemas cuando el case no fuera el mismo. Sin embargo, en el RFC se especifica que los mails son case sensitive, por lo tanto, y tomando en cuenta que puede ser probable que usemos mails en vez de strings arbitrarios, he decidido dejarlo en `.equals`

## Estructura del sistema



## Compiling

Sólo es necesario correr el programa [compile.sh](compile.sh), el cual compila y ejecuta el programa

```sh
./compile.sh
```

Si solo se requiere inicar el programa sin compilar, se puede copiar y pegar este codigo en terminal
```sh
serviceName="Addressbook_service"
ip="127.0.0.1"
port="1000"

# Put the rmiregistry and the server in a background process
sudo rmiregistry $port &
java Addressbook_server $ip:$port $serviceName &

echo "Running client in 2 seconds (server startup time)"
sleep 2s
java Addressbook_client $ip:$port $serviceName
```

## Errores conocidos

Los errores posibles son que el cliente reciba un `CON_ERROR` y `NOT_FOUND`. Tambien es posible que el cliente no pueda conectarse al servicio si el servidor no esta corriendo. El cual mostrara un mensaje de error apropiado.

> Es posible que si un usuario tiene el nombre de *CON_ERROR* o *NOT_FOUND* nos de un falso error, debido a como funciona el programa. Ahora que lo pienso, podria ser evitado si simplemente se devolviera una clase en vez de un String como tal. Pero por el momento no tengo tiempo para investigar la implementacion de ello.