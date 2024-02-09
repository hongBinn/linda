package linda;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionLindaServer {
	private final int PUERTO = 1234;
    private final String HOST = "localhost"; 
    protected ServerSocket ss; 
    protected Socket cs; 
    
    public ConexionLindaServer(String tipo) throws IOException {
        if(tipo.equalsIgnoreCase("servidor")) {
            ss = new ServerSocket(PUERTO);
        } else {
            cs = new Socket(HOST, PUERTO);
        }
    }
}
