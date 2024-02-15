package linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Servidor extends ConexionLindaClient{
	private List<List<String>> almacen;
    public Servidor() throws IOException {
    	super("servidor");
    }

    public void startServer() throws IOException {
    	try (DataInputStream in = new DataInputStream(cs.getInputStream());
    		DataOutputStream out = new DataOutputStream(cs.getOutputStream())) {
    		System.out.println("Linda en línea");
		    System.out.println("Linda conectado desde " + cs.getInetAddress());
	    	while (true) {
	            ThreadAtiendeServidor threads = new ThreadAtiendeServidor ( cs, almacen);
	            threads.start();
			}
    	}
    }
}
