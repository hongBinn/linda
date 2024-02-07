package linda;

import java.io.IOException;

public class MainServer3 {
	public static void main(String[] args) throws IOException {
		 Server3 serv = new Server3(); //Se crea el servidor

		 System.out.println("Iniciando servidor\n");
		 serv.startServer(); //Se inicia el servidor
	 }
}