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
    private ConexionLindaClient lindaClient;
    public BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(csServidor.getInputStream()));
    public PrintWriter salidaServidor = new PrintWriter(csServidor.getOutputStream(),true);
    public ServerLinda() throws IOException {
        super("servidorLinda"); // Call the constructor of ConexionLindaClient
        this.lindaClient = new ConexionLindaClient("client");
    }
    public BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(lindaClient.csCliente.getInputStream()));
    public PrintWriter salidaCliente = new PrintWriter(lindaClient.csCliente.getOutputStream(),true);
    public DataOutputStream outCliente = new DataOutputStream(lindaClient.csCliente.getOutputStream());
    public DataInputStream inCliente = new DataInputStream(lindaClient.csCliente.getInputStream());

    public void start() throws IOException {
		while (true) {
			System.out.println("Esperando...");
            try {
            	List<Socket> csServidores = new ArrayList<Socket>();
            	for(int i = 0; i < 3; i++) {
                    csServidor = ssServidor.accept();
		            System.out.println("Servidor en línea");
				    System.out.println("Servidor conectado desde " + csServidor.getInetAddress());
                    csServidores.add(csServidor);
            	}
                while(true) {
                	lindaClient.csCliente = lindaClient.ssCliente.accept();
                    if (entradaCliente != null) {
			            System.out.println("Cliente en línea");
					    System.out.println("Cliente conectado desde " + lindaClient.csCliente.getInetAddress());
					}
                	outCliente.writeUTF("Seleccionar un Servidor por tamaño. \n"
                			+	"Servidor 1: 1 a 3 \n"
                			+	"Servidor 2: 4 a 5 \n"
                			+	"Servidor 3: 6");
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
			mensaje = inCliente.readUTF();
			ThreadAtiendeServidor threads = null;
	        if(mensaje.toUpperCase().equals("SERVIDOR1")) {
	        	threads = new ThreadAtiendeServidor(csServidores.get(0), lindaClient.csCliente);
	        }else if(mensaje.toUpperCase().equals("SERVIDOR2")) {
	        	threads = new ThreadAtiendeServidor(csServidores.get(1), lindaClient.csCliente);
	        }else if(mensaje.toUpperCase().equals("SERVIDOR3")) {
	        	threads = new ThreadAtiendeServidor(csServidores.get(2), lindaClient.csCliente);
	        }else {
	        	
	        }
			threads.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

