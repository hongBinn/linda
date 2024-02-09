package linda;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionLindaServer {
	private final int PUERTO = 1234;
    private final String HOST = "localhost"; 
    protected ServerSocket ssServidor; 
    protected Socket csServidor; 
    
    public ConexionLindaServer(String tipo) throws IOException {
        if(tipo.equalsIgnoreCase("servidor")) {
            ssServidor = new ServerSocket(PUERTO);
        } else {
            csServidor = new Socket(HOST, PUERTO);
        }
    }
}
