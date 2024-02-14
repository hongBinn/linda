package linda;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionLindaClient {
    private final int PUERTO = 5051; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    protected ServerSocket ssCliente; //Socket del servidor
    protected Socket csCliente; //Socket del cliente
    
    public ConexionLindaClient(String tipo) throws IOException {//Constructor
        if(tipo.equalsIgnoreCase("linda")) {
            ssCliente = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
            //cs = new Socket(); //Socket para el cliente
        } else {
            csCliente = new Socket(HOST, PUERTO); //Socket para el cliente en localhost en puerto 1234
        }
    }
}
