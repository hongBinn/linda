package linda;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.regex.Pattern;

public class Servidor {
	protected ServerSocket serverSocket; 
	protected Socket clientSocket;
	public ArrayList<String> lista = new ArrayList<String>();
	public Semaphore semaphore = new Semaphore (1);

	/*
	 * Pre: -- 
	 * Post: Este metodo lanza un servidor y gestiona las conexiones recibidas por linda.
	 */
	public void startServer(int opcion) throws IOException {
		
		if (opcion == 1) {
			serverSocket = new ServerSocket(1235); // 1-3
		} else if (opcion == 2) {
			serverSocket = new ServerSocket(1236); // RESERVA
		} else if (opcion == 3) {
			serverSocket = new ServerSocket(1237); // 4-5
		} else if (opcion == 4) {
			serverSocket = new ServerSocket(1238); // 6
		}
		try {
			while (true) {
				System.out.println("Esperando...");
				clientSocket = serverSocket.accept();
				System.out.println("Linda en linea");
				LindaHandler LindaSock = new LindaHandler(clientSocket, lista, semaphore);
				new Thread(LindaSock).start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Pre: -- 
	 * Post: Este metodo se encarga de eliminar la tupla deseada por el usuario.
	 */
	public synchronized static String eliminar(String[] partesTuplas, ArrayList<String> lista, boolean eliminar) {
		String regex = "[A-Z]";
		for (int i = 0; i < lista.size(); i++) {
			boolean borrar = true;
			int variable = 1;
			ArrayList<Boolean> booleanList = new ArrayList<Boolean>();
			String comparacion[] = lista.get(i).split("\"");
			if (comparacion.length == partesTuplas.length) {
				while (true) {
					if (partesTuplas[variable].equals(comparacion[variable]) || partesTuplas[variable].substring(0, 1).equalsIgnoreCase("?") && Pattern.matches(regex, partesTuplas[variable].substring(1, 2))) {
						booleanList.add(true);
					} else {
						booleanList.add(false);
						break;
					}
					//Variable que recorre los numeros impares hasta acabar el analisis.Las variables siempre se encuentran en la posiciones 
					//impares de la tabla.
					variable = variable + 2;
					if (variable >= partesTuplas.length) {
						break;
					}
				}
				for (int j = 0; j < booleanList.size(); j++) {
					if (booleanList.get(j) == false) {
						borrar = false;
						break;
					} 
				}
				if (borrar == true) {
					String eliminada = lista.get(i);
					lista.remove(i);
					return "Tupla " + eliminada + " eliminada";
				} 
			}
		}
		return "No se ha encontrado una tupla similar";
	}

}