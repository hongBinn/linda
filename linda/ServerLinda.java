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


public class ServerLinda extends ConexionLindaClient{
	List<Socket> csServidores = new ArrayList<Socket>();

    public ServerLinda() throws IOException {
    	super("linda");
    }

    public void start() throws IOException {
		System.out.println("Esperando ...");
		try {
			while (true) {
                if (ssServidor.isBound()) {
    				if (csServidores.size() < 4) {
                        cs = ssServidor.accept();
                        System.out.println("Servidor Conectado.");
                        csServidores.add(cs);
    				}                    
    			}
                if (ssCliente.isBound()) {
                	cs = ssCliente.accept();
                    DataOutputStream outCliente = new DataOutputStream(cs.getOutputStream());
                    System.out.println("Cliente Conectado.");
                    outCliente.writeUTF("Estas conectado a LINDA.\n"
                    		+ csServidores.size() + " Servidores en linea.");
                    if(csServidores.size() > 0) {
    		        	ThreadAtiendeCliente threads = new ThreadAtiendeCliente(csServidores, cs);
    		    		threads.start();
                    }
                }
                
			}
		}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

