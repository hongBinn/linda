package DistribuidoLINDA;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private Socket server;
	private Socket serverReserva;
	private ArrayList<String> enEspera1 = new ArrayList<String>();
	private ArrayList<String> enEspera1Reserva = new ArrayList<String>();

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
	}

	/*
	 * Pre: -- 
	 * Post: Este metodo se encarga de insertar una instruccion en el servidor adecuado
	 */
	public String insertarInstruccion(Socket servidorDestino, DataOutputStream out, String instruccion) {
		String mensaje = "";
		try {
			DataInputStream inServer = new DataInputStream(servidorDestino.getInputStream());
			DataOutputStream outServer = new DataOutputStream(servidorDestino.getOutputStream());
			outServer.writeUTF(instruccion);
			mensaje = inServer.readUTF();
			System.out.println(mensaje);
		} catch (Exception e) {}
		return mensaje;
	}

	/*
	 * Pre: -- 
	 * Post: Este metodo se encarga de insertar las instrucciones que tiene un servidor 
	 * 		 en espera una vez este se levante.
	 */
	public void insertarEspera(ArrayList<String> listaEnEspera, Socket servidorDestino) {
		try {
			for (int i = 0; i < listaEnEspera.size(); i++) {
				DataInputStream inServer = new DataInputStream(servidorDestino.getInputStream());
				DataOutputStream outServer = new DataOutputStream(servidorDestino.getOutputStream());
				outServer.writeUTF(listaEnEspera.get(i));
				String mensaje = inServer.readUTF();
				System.out.println(mensaje);
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			out.writeUTF("BIENVENIDO AL SERVICIO LINDA");
			while (true) {
				boolean server1 = false;
				boolean server1Reserva = false;
				String instruccion = in.readUTF();
				String partes[] = instruccion.split("\"");
				if (partes.length <= 7) {
					String resultado = "";
					try {
						server = new Socket("localhost", 1235);
						server1 = true;
						if (!enEspera1.isEmpty()) {
							insertarEspera(enEspera1, server);
							enEspera1.clear();
						}
						resultado = insertarInstruccion(server, out, instruccion);
					} catch (Exception e) {
						enEspera1.add(instruccion);
					}
					try {
						serverReserva = new Socket("localhost", 1236);
						server1Reserva = true;
						if (!enEspera1Reserva.isEmpty()) {
							insertarEspera(enEspera1Reserva, serverReserva);
							enEspera1Reserva.clear();
						}
						resultado = insertarInstruccion(serverReserva, out, instruccion);
					} catch (Exception e) {
						enEspera1Reserva.add(instruccion);
					}
					if (!server1 && !server1Reserva) {
						out.writeUTF("El servidor al que intenta acceder no esta operativo");
					} else {
						out.writeUTF(resultado);
					}
				} else if (partes.length <= 11 && partes.length >= 8) {
					try {
						server = new Socket("localhost", 1237);
						out.writeUTF(insertarInstruccion(server, out, instruccion));
					} catch (Exception e) {
						out.writeUTF("El servidor al que intenta acceder no esta operativo");
					}
				} else if (partes.length == 13) {
					try {
						server = new Socket("localhost", 1238);
						out.writeUTF(insertarInstruccion(server, out, instruccion));
					} catch (Exception e) {
						out.writeUTF("El servidor al que intenta acceder no esta operativo");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
