package linda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerLinda extends ConexionLindaServer {
    private List<List<String>> almacen;
    private ConexionLindaClient lindaClient;
    public BufferedReader entrada = new BufferedReader(new InputStreamReader(csServidor.getInputStream()));
    public PrintWriter salida = new PrintWriter(csServidor.getOutputStream(),true);
    public ServerLinda() throws IOException {
        super("servidorLinda"); // Call the constructor of ConexionLindaClient
        this.lindaClient = new ConexionLindaClient("client");
        this.almacen = new ArrayList<>();
    }
    public void start() throws IOException {
		while (true) {
			System.out.println("Esperando...");
            System.out.println("Cliente en línea");
		    System.out.println("Cliente conectado desde " + csServidor.getInetAddress());
            try {
                while(true) {
                    csServidor = ssServidor.accept();
                    ThreadAtiendeCliente threads = new ThreadAtiendeCliente ( csServidor, entrada, salida, almacen);
                    threads.start();
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
		}
    }
}

