package linda;

import java.io.IOException;
import java.util.List;

public class Servidor extends ConexionLindaServer{
	private List<List<String>> almacen;
    public Servidor() throws IOException {
    	super("servidor");
    }

    public void startServer() throws IOException {
    	System.out.println("Linda en línea");
	    System.out.println("Linda conectado desde " + csServidor.getInetAddress());
    	while (true) {
		    if (csServidor != null) {
	            ThreadAtiendeCliente threads = new ThreadAtiendeCliente ( csServidor, almacen);
	            threads.start();
		    }
		}
    }
}
