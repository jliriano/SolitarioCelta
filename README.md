# FEM - PERSISTENCIA DE DATOS

#### Partiendo del proyecto Solitario Celta, se desarrollaron e implementaron las siguientes funcionalidades:
- **Reiniciar partida:** al pulsar el botón "reiniciar" se mostrará un diálogo de confirmación. En caso de respuesta afirmativa se procederá a reiniciar la partida actual.  
- **Guardar partida:** esta opción permite guardar la situación actual del tablero. Sólo es necesario guardar una única partida y se empleará el sistema de ficheros del dispositivo.  
- **Recuperar partida:** si existe, recupera el estado de una partida guardada (si se hubiera modificado la partida actual, se solicitará confirmación).  
- **Guardar puntuación:** al finalizar cada partida se deberá guardar la información necesaria para generar un listado de resultados. Dicho listado deberá incluir -al menos- el nombre del jugador, el día y hora de la partida y el número de piezas que quedaron en el tablero. La información se deberá almacenar en una base de datos.  
- **Mejores resultados:** esta opción mostrará el histórico con los mejores resultados obtenidos ordenados por número de piezas. Incluirá una opción -con confirmación- para eliminar todos los resultados guardados.