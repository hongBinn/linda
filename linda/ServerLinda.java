package linda;

import java.io.IOException;

public class ServerLinda extends ConexionLindaClient{
	
	private ConexionLindaServer lindaServer;

    public ServerLinda() throws IOException {
        super("client"); // Call the constructor of ConexionLindaClient
        lindaServer = new ConexionLindaServer("server");
    }
    
	public void startServer() {
		
	}
}
