package linda;

import java.io.IOException;

public class MainLinda {
	public static void main(String[] args) throws IOException {
        ServerLinda serv = new ServerLinda(); 
        System.out.println("Iniciando Linda\n");
        serv.start();
    }
}
