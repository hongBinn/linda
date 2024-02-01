package linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Cliente extends ConexionLindaClient {
	public Cliente() throws IOException {
		super("cliente");
	} //Se usa el constructor para cliente de Conexion
	
	/**
	 * Pre:---
	 * Post:Pide el nombre del cliente, informar como reservar la plaza.
	 * Comprobar el comando y enviar a servidor la plaza que quiere reservar.
	 * serverOut() Mostrar los mensajes que enviado por servidor.
	 */
	public void startClient() {//Método para iniciar el cliente
		try {
			// Canal para recibir mensajes (entrada)
			DataInputStream in = new DataInputStream(cs.getInputStream());
			// Canal para enviar mensajes (salida)
		    DataOutputStream out = new DataOutputStream(cs.getOutputStream());
		}catch(Exception e){
            System.out.println(e.getMessage());
		}
	}
}
