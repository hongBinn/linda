package linda;

import java.io.IOException;

public class MainServidorReplica {
	public static void main(String[] args) throws IOException {
        Servidor serv = new Servidor(); 
        System.out.println("Iniciando servidor\n");
        serv.startServer();
    }
}
