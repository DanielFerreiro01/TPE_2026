# TPE - Programación 3 | TUDAI 2026

## Descripción

El trabajo consiste en resolver un problema de asignación de paquetes a camiones. Dado un conjunto de camiones con capacidad máxima (algunos refrigerados) y un conjunto de paquetes con peso, tipo y urgencia, el objetivo es minimizar el peso total no asignado respetando las restricciones de capacidad y refrigeración.

---

## Parte 1 - Servicios

Se implementa la clase `Servicios` que carga los datos desde archivos CSV y expone tres servicios:

- **Servicio 1:** dado un código de paquete, retorna el paquete correspondiente en O(1).
- **Servicio 2:** dado un booleano, retorna la lista de paquetes que contienen o no alimentos en O(1).
- **Servicio 3:** dado un rango de urgencia, retorna todos los paquetes dentro de ese rango en O(resultado).

---

## Parte 2 - Algoritmos

Se resuelve el problema de asignación usando dos técnicas distintas:

- **Backtracking:** garantiza la solución óptima explorando el espacio de búsqueda con podas de factibilidad y optimalidad. La métrica reportada es la cantidad de estados generados.
- **Greedy:** obtiene una solución en tiempo polinomial priorizando los paquetes de mayor urgencia. No garantiza la solución óptima. La métrica reportada es la cantidad de iteraciones.

---

## Formato de los archivos CSV

**Camiones.csv**
```
<cantidad_de_camiones>
<id>;<patente>;<refrigerado 1/0>;<capacidad_kg>
```

**Paquetes.csv**
```
<cantidad_de_paquetes>
<id>;<codigo>;<peso_kg>;<contiene_alimentos 1/0>;<nivel_urgencia>
```

---

Programación 3 — TUDAI 2026
