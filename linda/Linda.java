package DistribuidoLINDA;

import java.net.ServerSocket;
import java.net.Socket;

public class Linda {
	private final int PUERTO = 1234;
	protected ServerSocket serverSocket;
	protected Socket clientSocket;

	/*
	 * Pre: -- 
	 * Post: Este metodo gestiona las acciones del servidor Linda.
	 */
	public void startServer() {
			try {
				serverSocket = new ServerSocket(PUERTO);
				while (true) {
					System.out.println("Esperando..."); 
					clientSocket = serverSocket.accept();
					System.out.println("Cliente en linea");
	                ClientHandler clientSock = new ClientHandler(clientSocket);
	                new Thread(clientSock).start();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

}
