package linda;

import java.io.IOException;
import linda.Servidor2;

public class MainServer2 {
	public static void main(String[] args) throws IOException {
        Servidor2 serv = new Servidor2(); 
        System.out.println("Iniciando servidor\n");
        serv.start();
    }
}