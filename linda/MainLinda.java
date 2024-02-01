package linda;

import java.io.IOException;


public class MainLinda {
	public static void main(String[] args) throws IOException {
	      ServerLinda servLinda = new ServerLinda(); //Se crea el servidor

	      System.out.println("Iniciando servidor\n");
	      servLinda.startServer(); //Se inicia el servidor
	  }
}
