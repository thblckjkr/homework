# Programa de Addressbook en JAVA con RMI

> Entiendo que puede ser un poco difícil de creer que no haya entregado las tareas anteriores de forma funcional y esta si la entregue... Pero me tomó más tiempo del que me gustaría admitir que la comparación de strings en Java es mas sencilla con String.equals que con un ==
>
> Teo González Calzada. [@thblckjkr] [2019/10/10]

## Decisiones

En vez de la función `.equals`, estoy consciente de que pude haber utilizado `.equalsIgnoreCase` para evitar problemas cuando el case no fuera el mismo. Sin embargo, en el RFC se especifica que los mails son case sensitive, por lo tanto, y tomando en cuenta que puede ser probable que usemos mails en vez de strings arbitrarios, he decidido dejarlo en `.equals`

## Estructura del sistema



## Compiling

Sólo es necesario correr el programa [compile.sh](compile.sh)

```sh
./compile.sh
```

## Errores conocidos

No