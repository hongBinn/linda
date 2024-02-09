package linda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class ServerLinda extends ConexionLindaServer{
    private List<List<String>> almacen;
    

    public ServerLinda() throws IOException {
    	super("servidor");
        this.almacen = new ArrayList<>();
    }
    public void start() throws IOException {
		while (true) {
			System.out.println("Esperando...");
            cs = ss.accept();
            System.out.println("Cliente en línea");
		    System.out.println("Cliente conectado desde " + cs.getInetAddress());
		}
    }
    
    public class ThreadAtiendeCliente extends Thread {
    	public BufferedReader entrada;
    	public PrintWriter salida;
        public void run() {
        	try {
        		entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                salida = new PrintWriter(cs.getOutputStream(),true);
                String mensaje = entrada.readLine();
                if(mensaje.startsWith("PostNote")) {
                	
                }
                
        	}catch(IOException e) {
                e.printStackTrace();
        }
    }
    
    public void PostNote(List<String> tuple) {
        almacen.add(tuple);
        System.out.println("Tuple añadida: " + tuple);
    }

    public List<String> RemoveNote() {
        if (!almacen.isEmpty()) {
            int index = (int) (Math.random() * almacen.size());
            List<String> tuple = almacen.remove(index);
            System.out.println("Tupla Eliminada: " + tuple);
            return tuple;
        } else {
            System.out.println("No tuples available.");
            return null;
        }
    }

    public List<String> readNote(List<String> pattern) {
        for (List<String> tuple : almacen) {
            if (foundTuple(tuple, pattern)) {
                System.out.println("Tupla Encontrada: " + tuple);
                return tuple;
            }
        }
        System.out.println("No hemos encontrado la tupla dicha.");
        return null;
    }

    public boolean foundTuple(List<String> tuple, List<String> tupleBuscada) {
        if (tuple.size() != tupleBuscada.size()) {
            return false;
        }
        for (int i = 0; i < tuple.size(); i++) {
            String mismaTupla = tupleBuscada.get(i);
            if (!mismaTupla.startsWith("?") && !mismaTupla.equals(tuple.get(i))) {
                return false;
            }
        }
        return true;
    }
    }
}

