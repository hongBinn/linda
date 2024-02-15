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
    	try (DataInputStream in = new DataInputStream(csServidor.getInputStream());
    		DataOutputStream out = new DataOutputStream(csServidor.getOutputStream())) {
    		System.out.println("Linda en línea");
		    System.out.println("Linda conectado desde " + csServidor.getInetAddress());
	    	while (true) {
	            ThreadAtiendeServidor threads = new ThreadAtiendeServidor ( csServidor, almacen);
	            threads.start();
	            if(in.readUTF().equalsIgnoreCase("END OF SERVICE")) break;
			}
	    	csServidor.close();
    	}
    }
}
