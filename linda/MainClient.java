package linda;

import java.io.IOException;


public class MainClient {
	 public static void main(String[] args) throws IOException {
	      Client cli = new Client(); //Se crea el cliente

	      System.out.println("Iniciando cliente\n");
	      cli.startClient(); //Se inicia el cliente
	  }
}
