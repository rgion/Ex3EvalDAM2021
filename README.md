# Ex3EvalDAM2021

1. **<span style="text-decoration:underline;">Gestión deportiva.</span>**

Una empresa relacionada con el mundo deportivo de Palma, nos ha solicitado el desarrollo de un programa de gestión deportiva. En este caso, tras recabar los requisitos, el analista nos ha pedido que hagamos lo siguiente:



1. **Clase Persona. (0,75 puntos)** Se caracteriza por tener un nombre y apellido Esta clase implementa el constructor vacío, constructor con todos los atributos, constructor copia, getters/setters y método toString. <span style="text-decoration:underline;">Esta clase nunca será instanciabl</span>e.
2. **Clase Jugador**. **(0,75 puntos)** Un jugador es una especialización de la clase Persona, y además de los atributos de la clase Persona, tiene los atributos dorsal que es único y demarcación<span style="text-decoration:underline;">.</span> Esta clase implementa el constructor vacío, **constructor copia,** getters/setters y método toString (devolverá el toString de la superclase más los atributos de Jugador).
3. El **programa principal** **(0,5 puntos) incluido en la Clase GestionDeportiva, que se caracteriza por tener un menú con tres opciones que veremos a continuación. El main **tendrá un menú que se llamará indefinidamente hasta que el usuario quiera salir de la aplicación. 
    *   **<span style="text-decoration:underline;">insertarJugador.</span>** **(1,5 puntos)** El entrenador, cuando seleccione esta opción le irá pidiendo los datos del jugador a insertar (nombre, apellido, dorsal y demarcación. Una vez creado el jugador lo insertará en la base de datos. (método de la clase Jugador)
    *   **<span style="text-decoration:underline;">crearAlineación</span>**. **(2,5 puntos)** El entrenador, cuando seleccione esta opción le irá pidiendo los dorsales de los jugadores que formarán parte de la alineación. Por simplificar el entrenador introducirá un código que identifique toda la alineación.

        Pedirá los 5 dorsales de los jugadores y los insertará en la tabla de alineaciones. O los inserta todos o ninguno (transacción).(método de la clase GestiónDeportiva)

    *   **consultarAlineación.** **(2,5 puntos)** Dado un código de alineación recuperará todos los datos de la alineación y la mostrará en un txt que deberá ser escrito con BufferWriter (**el fichero debe tener la pinta del que aparece en la ejecución que se adjunta más abajo)**. (método de la clase GestiónDeportiva)
    *   Las excepciones del **método de la transacción** se tratan en el propio método, donde para las excepciones de tipo SQLException imprimiremos el mensaje: SQLException más el código de error que identifica esa excepción. **(0,5 puntos)**
    *   Las **excepciones del resto de métodos** se elevan hasta el método el main, donde para las excepciones de tipo SQLException imprimiremos el mensaje: SQLEception más el código de error que identifica esa excepción. Para el resto de excepciones imprimiremos “error no controlado” + mensaje de la excepción + pila de llamadas. **(0,5 puntos)**
