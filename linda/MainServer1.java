package linda;

import java.io.IOException;

import linda.Servidor1;

public class MainServer1 {
	public static void main(String[] args) throws IOException {
        Servidor1 serv = new Servidor1(); 
        System.out.println("Iniciando servidor\n");
        serv.start();
    }
}