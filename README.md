# 🚨 Sistema de Alertas

## 🌍 Contexto

Una **Central de Monitoreo** detecta eventos naturales (lluvias, granizo, calor extremo, etc.) y debe notificar automáticamente a las entidades registradas (hospitales 🏥, bomberos 🚒, municipalidades 🏛️). Cada entidad decide cómo reaccionar ante la alerta, y esta reaccion que puede variar dinámicamente.

## 📢 Emitir Alerta

Una alerta tiene las siguientes características:

- **Tipo**: Descripción del evento (ej: "lluvia" ☔, "calor extremo" 🔥)
- **Nivel**: Valor numérico del **1 al 10** (1 = leve, 10 = catastrófico)
- **Crítica**: Será crítica si el nivel es **≥ 8**

### Funcionalidades de la Central de Monitoreo:

1. **Registro de alertas**:

   - Al emitir una alerta (recibiendo `tipo` y `nivel`), la central:
     - Valida que el nivel esté entre 1 y 10. Si no, lanza una excepción: `"Nivel de alerta incorrecto"`
     - Guarda la alerta en un **registro de alertas**, el registro contiene la alerta en si misma y la cantidad de entidades notificadas de la alerta.
     - Notifica la alerta a todas las entidades registradas

2. **Gestión de entidades**:

   - Puede agregar o eliminar entidades en tiempo real

3. **Cantidad de alertas notificadas**:

   - Responde el total de notificaciones enviadas a partir del registro de alertas

## 📩 Recibir Alerta

Cada entidad:

- Tiene un nombre que no puede variar. Por ejemplo: "Hospital Alemán"
- Registra todas las alertas recibidas
- calcula el **riesgo** según su **comportamiento actual** (que puede cambiarse dinámicamente e inicialmente el comportamiento es Riedgo Crítico)

### 🔄 Comportamientos Predefinidos:

1. **Riesgo Crítico** ⚠️:

   - El riesgo es igual al **nivel de la última alerta recibida**
   - Pero si la última alerta es **crítica (≥ 8)**, el riesgo es **10**

2. **Riesgo Promedio** 📊:

   - Calcula el riesgo como el **promedio** de los niveles de todas las alertas recibidas
   - _Ejemplo_: Si recibió alertas con niveles [3, 5, 2], el riesgo es `(3+5+2)/3 = 3.33`

3. **Riesgo Acumulativo** 📈:
   - Suma los niveles de las **alertas críticas** recibidas en la última hora
   - _Ejemplo_: Si hubo 2 alertas críticas (niveles 8 y 9), el riesgo es `8 + 9 = 17`

## 🔍 Tests

### Set Up

- Crear la Central
- Crear 2 entidades, (e1, e2) (con el comportamiento por defecto)
- Añadir entidades a la entidades receptoras de alertas.

### Test #1 - dadoElSetUp_alAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo

- dado el SetUp incial
- Se Agregan 2 alertas, calor con un valor de 6 y lluvia con un nivel de 8
- Se verfica que:

  - Cada entidad haya recibido ambas alertas
  - Que el riesgo de cada entidad sea 10

### Test #2 - dadoElSetUp_alCambiarElComportamientoYAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo

- dado el SetUp incial
- cambiar el comportamiento a Riesgo Promedio de la entidad e1
- Se Agregan 2 alertas, calor con un valor de 6 y lluvia con un nivel de 8
- Se verifica que:

  - Cada entidad haya recibido ambas alertas
  - Que el riesgo de la entidad e1 sea igual a 7
  - Que el riesgo de la entidad e2 sea igual a 10

### Test #3 - dadoElSetUp_alAgregarAlertasQuitarUnaEntidadYAgregaNuevaAlerta_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo

- dado el SetUp incial
- Agregar 2 alertas, calor con un valor de 6 y lluvia con un nivel de 8
- Quitar de las entidades receptoras de alertas a la entidad e1
- Agregar 1 nueva alerta (granizo, 7)
- Se verifica que:

  - La entidad e1 tiene 2 alertas y riesgo 10.
  - La entidad e2 tiene 3 alertas y riesgo 7.
  - La cantidad de Alertas enviadas a las entidades es 5.

### Test #4 - Excepción:

Si se intenta emitir una alerta con nivel 0, 11, o negativo, el sistema debe lanzar una excepción con el mensaje "Nivel de alerta incorrecto".

## 🎯 Bonus / Punto 10

La entidad debe tener la capacidad de responder:

- Inventar algun comportamiento de Riesgo que maneje algun tipo de estado interno.
- La alerta de mayor nivel, si hay 2 con el mismo nivel debe responder la primera que ocurrio.
