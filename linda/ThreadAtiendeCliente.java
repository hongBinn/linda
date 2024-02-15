package linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ThreadAtiendeCliente extends Thread {
    public DataOutputStream outCliente; 
    public DataInputStream inCliente; 
    public DataOutputStream outServidor; 
    public DataInputStream inServidor;
	private List<Socket> servidores; 

	public ThreadAtiendeCliente(List<Socket> csServidores, Socket csCliente) {
        try {
			this.outCliente = new DataOutputStream(csCliente.getOutputStream());
			this.inCliente = new DataInputStream(csCliente.getInputStream());
			this.servidores = csServidores;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public ThreadAtiendeCliente() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
        String mensaje;
		try {
			outCliente.writeUTF("Seleccionar un Servidor por tamaño. \n"
        			+	"Servidor 1: 1 a 3 \n"
        			+	"Servidor 2: 4 a 5 \n"
        			+	"Servidor 3: 6");
			outCliente.writeUTF("MENSAJE FIN");
			mensaje = inCliente.readUTF();
	        if(mensaje.toUpperCase().equals("SERVIDOR1")) {
	        	outServidor = new DataOutputStream(servidores.get(0).getOutputStream());
	        	inServidor = new DataInputStream(servidores.get(0).getInputStream());
				outCliente.writeUTF("CONECTADO A SERVIDOR 1");
				System.out.println("Cliente conectado a servidor 1");
				mensaje(inServidor, outCliente);
			}else if(mensaje.toUpperCase().equals("SERVIDOR2")) {
	        }else if(mensaje.toUpperCase().equals("SERVIDOR3")) {
	        }else {
	        	
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Pre:---
	 * Post:Mostrar TODOS los mensajes que enviar por Servidor.
	 * @param in
	 * @param out 
	 */
	private void mensaje(DataInputStream in, DataOutputStream out) {
		try {
			while (true) {
		    	String info = in.readUTF();
		    	out.writeUTF(info);;
		        if (info.equalsIgnoreCase("MENSAJE FIN")) {
		            break;
		        }   
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
