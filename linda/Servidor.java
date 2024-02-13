package linda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import linda.ThreadAtiendeCliente;

public class Servidor extends ConexionLindaServer{
	private List<List<String>> almacen;
	public BufferedReader entrada;
    public PrintWriter salida;
    public Servidor() throws IOException {
    	super("servidor");
    }

    public void startServer() throws IOException {
    	while (true) {
			System.out.println("Esperando...");
			csServidor = ssServidor.accept();
            System.out.println("Cliente en línea");
		    System.out.println("Cliente conectado desde " + csServidor.getInetAddress());
		    ThreadAtiendeCliente thread = new ThreadAtiendeCliente(csServidor,entrada,salida,almacen);
		    thread.start();
		}
    }
}
