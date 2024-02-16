package DistribuidoLINDA;

import java.io.IOException;
import java.util.Scanner;

public class MainServidor {
	
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		while (true) {
			System.out.println("Que accion quieres realizar?");
			System.out.println(
					"------------------------------------------------------------------------------------------------------");
			System.out.println("1.- Lanzar Linda");
			System.out.println("2.- Lanzar Serv1");
			System.out.println("3.- Lanzar Serv1Reserva");
			System.out.println("4.- Lanzar Serv2");
			System.out.println("5.- Lanzar Serv3");
			System.out.print("\n�Que opcion deseas ejecutar?: ");
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
			}else {
				System.out.println("Error, elija una de las opciones validas");
			}
		}
	}
	
}