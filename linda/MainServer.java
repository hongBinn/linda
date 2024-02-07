package linda;

import java.io.IOException;

public class MainServer {
	 public static void main(String[] args) throws IOException {
		 Server1 serv = new Server1(); //Se crea el servidor

		 System.out.println("Iniciando servidor\n");
		 serv.startServer(); //Se inicia el servidor
	 }
}