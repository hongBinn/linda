package linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

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
        try (DataInputStream in = new DataInputStream(cs.getInputStream());
        	 DataOutputStream out = new DataOutputStream(cs.getOutputStream())) {
            Scanner entrada = new Scanner(System.in);
	        System.out.println("BIENVENIDO AL SERVICIO" );
            while(true) {
            	mensaje(in);
    	        System.out.println("Introduce operacion:");
            	String operacion = entrada.nextLine();
            	out.writeUTF(operacion);
            	if(operacion.equalsIgnoreCase("END OF SERVICE")) break;
            }
            cs.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}

    /**
	 * Pre:---
	 * Post:Mostrar TODOS los mensajes que enviar por Servidor.
	 * @param in
	 */
	private void mensaje(DataInputStream in) {
		try {
			while (true) {
		    	String info = in.readUTF();
		    	System.out.println( info );
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
