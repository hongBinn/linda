package linda;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionLindaClient {
    private final int PUERTO_CLIENTE = 5050;
    private final int PUERTO_SERVIDOR = 5000;
    private final String HOST = "localhost";
    protected ServerSocket ssCliente;
    protected ServerSocket ssServidor;
    protected Socket csCliente;
    protected Socket csServidor;
    
    public ConexionLindaClient(String tipo) throws IOException {//Constructor
        if(tipo.equalsIgnoreCase("linda")) {
        	ssCliente = new ServerSocket(PUERTO_CLIENTE);
        	ssServidor = new ServerSocket(PUERTO_SERVIDOR);
        } else if (tipo.equalsIgnoreCase("servidor")) {
        	csServidor = new Socket(HOST, PUERTO_SERVIDOR);
        } else {
        	csCliente = new Socket(HOST, PUERTO_CLIENTE);
        }
    }
}
