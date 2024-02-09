package linda;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionLindaClient {
    private final int PUERTO = 1234; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    protected ServerSocket ssClient; //Socket del servidor
    protected Socket csClient; //Socket del cliente
    
    public ConexionLindaClient(String tipo) throws IOException {//Constructor
        if(tipo.equalsIgnoreCase("servidorlinda")) {
            ssClient = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
            //cs = new Socket(); //Socket para el cliente
        } else {
            csClient = new Socket(HOST, PUERTO); //Socket para el cliente en localhost en puerto 1234
        }
    }
}
