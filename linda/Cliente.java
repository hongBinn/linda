package linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Cliente { 
	private final int PUERTO = 1234; 
	private final String HOST = "localhost"; 
	protected ServerSocket serverSocket;
	protected Socket clientSocket; 
	
	/**
	 * Pre: --- 
	 * Post: Este metodo gestiona las acciones de un cliente.
	 */
	public void startClient() {
		Scanner entrada = new Scanner(System.in);
		try {
			clientSocket = new Socket(HOST, PUERTO);
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			String mensaje = in.readUTF();
			System.out.println(mensaje);
			while (true) {
				System.out.println("Que accion quieres realizar?");
				System.out.println(
						"------------------------------------------------------------------------------------------------------");
				System.out.println("1.- PostNote [tupla]");
				System.out.println("2.- RemoveNote [tupla]");
				System.out.println("3.- ReadNote [tupla]");
				String instruccion = entrada.nextLine();
				String partes[] = instruccion.split(" ", 2);
				out.writeUTF(instruccion);
				mensaje = in.readUTF();
				System.out.println(mensaje);
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}