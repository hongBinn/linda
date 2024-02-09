package linda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadAtiendeCliente extends Thread {
    private List<List<String>> almacen;
    protected Socket cs;
    public BufferedReader entrada;
    public PrintWriter salida;
    public ThreadAtiendeCliente(Socket cs, BufferedReader entrada, PrintWriter salida, List<List<String>> almacen) {
        this.cs = cs;
        this.entrada = entrada;
        this.salida = salida;
        this.almacen = almacen;
    }
    public void run() {
        try {
            String mensaje = entrada.readLine();
            if(mensaje.startsWith("PostNote")) {
                PostNote(mensaje);
            }else if(mensaje.startsWith("RemoveNote")) {
                PostNote(mensaje);
            }else if(mensaje.startsWith("readNote")) {
                PostNote(mensaje);
            }else{
            }
        }catch(IOException e) {
            e.printStackTrace();
    }
}

public void PostNote(String mensaje) {
    String[] mensajeTabla = mensaje.split("/s");
    List<String> tuple =  new ArrayList<String>();
    for (int i = 0; i < mensajeTabla.length; i++) {
        tuple.add(mensajeTabla[i]);
    }
    almacen.add(tuple);
    salida.write("Tuple añadida: " + tuple);
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