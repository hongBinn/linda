package linda;

import java.io.IOException;
import linda.Servidor3;

public class MainServer3 {
	public static void main(String[] args) throws IOException {
        Servidor3 serv = new Servidor3(); 
        System.out.println("Iniciando servidor\n");
        serv.start();
    }
}