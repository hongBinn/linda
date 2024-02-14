package linda;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerLinda extends ConexionLindaServer {
    private ConexionLindaClient lindaCliente;
    public ServerLinda() throws IOException {
        super("linda"); // Call the constructor of ConexionLindaClient
        this.lindaCliente = new ConexionLindaClient("linda");
    }

    public void start() throws IOException {
		System.out.println("Esperando...");
		while (true) {
            try {
            	List<Socket> csServidores = new ArrayList<Socket>();
            	for(int i = 0; i < 4; i++) {
                    csServidor = ssServidor.accept();
		            System.out.println("Servidor en línea");
				    System.out.println("Servidor conectado desde " + csServidor.getInetAddress());
                    csServidores.add(csServidor);
            	}
                while(true) {
                	lindaCliente.csCliente = lindaCliente.ssCliente.accept();
		            System.out.println("Cliente en línea");
				    System.out.println("Cliente conectado desde " + lindaCliente.csCliente.getInetAddress());
                    seleccionarServidor(csServidores);
					
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
		}
    }

	private void seleccionarServidor(List<Socket> csServidores) {
        String mensaje;
		try {
	    	DataOutputStream outCliente = new DataOutputStream(lindaCliente.csCliente.getOutputStream());
	        DataInputStream inCliente = new DataInputStream(lindaCliente.csCliente.getInputStream());
			outCliente.writeUTF("Seleccionar un Servidor por tamaño. \n"
        			+	"Servidor 1: 1 a 3 \n"
        			+	"Servidor 2: 4 a 5 \n"
        			+	"Servidor 3: 6");
			mensaje = inCliente.readUTF();
			ThreadAtiendeServidor threads = null;
	        if(mensaje.toUpperCase().equals("SERVIDOR1")) {
	        	threads = new ThreadAtiendeServidor(csServidores.get(0), lindaCliente.csCliente);
	        }else if(mensaje.toUpperCase().equals("SERVIDOR2")) {
	        	threads = new ThreadAtiendeServidor(csServidores.get(1), lindaCliente.csCliente);
	        }else if(mensaje.toUpperCase().equals("SERVIDOR3")) {
	        	threads = new ThreadAtiendeServidor(csServidores.get(2), lindaCliente.csCliente);
	        }else {
	        	
	        }
			threads.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

