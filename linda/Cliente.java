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
        try {
		    DataInputStream in = new DataInputStream(csClient.getInputStream());
        	// Canal para enviar mensajes (salida)
            DataOutputStream out = new DataOutputStream(csClient.getOutputStream());
            Scanner entrada = new Scanner(System.in);
            System.out.println("INTRODUZCA TU NOMBRE: ");
        	String cliente = entrada.nextLine();
        	out.writeUTF(cliente);
            System.out.println("INICIO COMPRA: " + cliente);
	        System.out.println("BIENVENIDO AL SERVICIO" );
            while(true) {
            	System.out.println(" PostNote: \n"
		        		+	"ReadNote:\n"
		        		+	"RemoveNote: ");
            	String operacion = entrada.nextLine();
	            if (!operacion.startsWith("PostNote") || !operacion.startsWith("ReadNote") ||!operacion.startsWith("RemoveNote")) {
		            System.out.println("Operación: "+ operacion);
	            	out.writeUTF(operacion);
	            }else {
	            	System.out.println("Operación invalido, introduce otra vez.");
	            	continue;
	            }
            	if(operacion.equalsIgnoreCase("END OF SERVICE")) break;
            	mensaje(in);
            }
            csClient.close();
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
		while (true) {
    	    try {
    	    	String info = in.readUTF();
    	        if (info.equalsIgnoreCase("OPERACION TERMINADA")) {
    	        	System.out.println( info );
    	            break;
    	        } else {
    	            System.out.println( info );
    	        }
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	        break;
    	    }
    	}
	}
}
