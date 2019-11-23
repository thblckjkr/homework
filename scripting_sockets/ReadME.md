# Ultima tarea

> Teo Gonzalez Calzada [@thblckjkr] 22/nov/2019

**Notas**

- El puerto puede ser variable facilmente, pero por simplicidad se opto por dejarlo estático en el `5678`

- El cliente de ruby cierra el socket y vuelve a crear uno nuevo para cada peticion. Se que no es lo ideal, pero no encontre alternativas.

- El cliente de Ruby, funciona con el servidor de Python

- El cliente de Python, funciona con el servidor de Ruby *Pero solo una petición a la vez (Las subsecuentes el socket comienza a dar broken pipes.)*

```
Version de ruby    2.6.5
Version de python	 3.7.4
```