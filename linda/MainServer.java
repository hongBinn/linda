package linda;

import java.io.IOException;

import linda.Servidor;

public class MainServer {
	public static void main(String[] args) throws IOException {
        Servidor serv = new Servidor(); 
        System.out.println("Iniciando servidor\n");
        serv.startServer();
    }
}