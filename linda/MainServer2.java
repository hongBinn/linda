package linda;

import java.io.IOException;

public class MainServer2 {
	public static void main(String[] args) throws IOException {
		 Server2 serv = new Server2(); //Se crea el servidor

		 System.out.println("Iniciando servidor\n");
		 serv.startServer(); //Se inicia el servidor
	 }
}