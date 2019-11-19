# Tarea 5. Sockets en Java

> Teo Gonzalez Calzada [@thblckjkr][2019/09/20 8:30pm]

## Documentación

### Aclaraciones previas

Debido a la mera costumbre, la documentación interna de las funciones está en Inglés. No es que las haya copiado directamente de algún lugar. La documentación propia de los métodos utilizados estará en español.

> He de admitir que subestime el tamaño de la tarea y no le di el tiempo correspondiente.

Las pruebas, en vez de ser realizadas de forma manual corriendo el programa. Se corren de forma semiautomatizada por medio de argumentos. Para probar el cliente, es de la siguiente forma

```sh
java Addressbook_client [server] [mail]

# Por lo tanto, se puede probar con los siguientes comandos
java Addressbook_client 127.0.0.1 ASD
java Addressbook_client 127.0.0.1 ASDFASDFADSF
java Addressbook_client 127.0.0.1 1
java Addressbook_client 127.0.0.1 NULL
java Addressbook_client 127.0.0.1 ERROR
java Addressbook_client 127.0.0.1 BOLOTNYY LEONID

java Addressbook_client 127.0.0.1 LB9XK
```

El servidor... no encontre ninguna forma de probarlo que no fuera enviando cadenas falsas desde el cliente... Lo cual no alcance a implementar

Un error conocido y el handler no implementado desde el lado de la interfaz del cliente es que el cliente es incapaz de darle un mensaje adecuado a las respuestas del servidor "INVALID_Q".