package linda;

import java.io.IOException;
import java.util.Scanner;

public class MainServidor {
	
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		while (true) {
			System.out.println("Que accion quieres realizar?");
			System.out.println(
					"------------------------------------------------------------------------------------------------------");
			System.out.println("1.- Lanzar Servidor 1 \n"
					+ "2.- Lanzar Servidor 1(Backup)\n"
					+ "3.- Lanzar Servidor 2 \n"
					+ "4.- Lanzar Servidor 3 \n"
					+ "\n Que opcion deseas ejecutar?: \n");
			int numero = reader.nextInt();
			System.out.println("");
			if (numero == 1) {
				Servidor servidor = new Servidor();
				servidor.startServer(numero);
			} else if (numero == 2) {
				Servidor servidor = new Servidor();
				servidor.startServer(numero);
			} else if (numero == 3) {
				Servidor servidor = new Servidor();
				servidor.startServer(numero);
			} else if (numero == 4) {
				Servidor servidor = new Servidor();
				servidor.startServer(numero);
			} else {
				System.out.println("Error, elija una de las opciones validas");
			}
		}
	}
}