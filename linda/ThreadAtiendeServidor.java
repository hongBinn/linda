package linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadAtiendeServidor extends Thread {
    public DataOutputStream outCliente; 
    public DataInputStream inCliente; 
    public DataOutputStream outServidor; 
    public DataInputStream inServidor; 

	public ThreadAtiendeServidor(Socket csServidor, Socket csCliente) {
        try {
			this.outCliente = new DataOutputStream(csCliente.getOutputStream());
			this.inCliente = new DataInputStream(csCliente.getInputStream());
			this.outServidor = new DataOutputStream(csServidor.getOutputStream());
			this.inServidor = new DataInputStream(csServidor.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public void run() {
    
	}
}
