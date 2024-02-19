package linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.regex.Pattern;

public class LindaHandler implements Runnable {
	private Socket clientSocket;
	public ArrayList<String> lista;
	public static Semaphore semaphore;

	public LindaHandler(Socket socket, ArrayList<String> lista, Semaphore semaphore) {
		this.clientSocket = socket;
		this.lista = lista;
		LindaHandler.semaphore = semaphore;
	}
	
	/**
	 * Pre: --- 
	 * Post: Este metodo se encarga de leer las tuplas almacenadas y compararlas con la tupla enviada por el usuario. 
	 * 		 Dependiendo de los parametros que envie el usuario se eliminara o se devolvera la tupla.
	 */
	public String leer(String[] parteTupla, boolean eliminar) {
		for (int i = 0; i < this.lista.size(); i++) {
			int variable = 0;
			String comparacion[] = lista.get(i).split("\\s+");
			for (int x = 0; x < comparacion.length; x++) {
				for (int y = 0; y < parteTupla.length; y++) {
					if(comparacion[x].equals(parteTupla[y])||parteTupla[y].toUpperCase().equals("X?")||
							parteTupla[y].toUpperCase().equals("Y?"))variable++;
				}	
			}
			if (variable == parteTupla.length) {
				String result = lista.get(i);
				if(eliminar == true) lista.remove(i);
				return result;
			};
		}
		return "No se ha encontrado una tupla similar";
	}

	/**
	 * Pre:---
	 * Post:Que recibe instrucciones de clientes y las procesa de acuerdo con el tipo de instrucción.
	 */
	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			while (true) {
				String instruccion = in.readUTF();
				String partes[] = instruccion.split("\\s+", 2);
				String partesTuplas[] = partes[1].split("\\s+");
				System.out.println(instruccion);
				if (partes[0].toUpperCase().equals("POSTNOTE")) {
					lista.add(partes[1]);
					System.out.println("Lista actual: ");
					for (int i = 0; i < lista.size(); i++) {
						System.out.println(lista.get(i));
					}
					out.writeUTF("Tupla incluida con exito");
				} else if (partes[0].toUpperCase().equals("READNOTE")) {
					semaphore.acquire(1);
					out.writeUTF(leer(partesTuplas, false));
					semaphore.release(1);
				} else if (partes[0].toUpperCase().equals("REMOVENOTE")) {
					semaphore.acquire(1);
					out.writeUTF(leer(partesTuplas, true));
					semaphore.release(1);
				} else {
					out.writeUTF("Elije una opcion valida");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
