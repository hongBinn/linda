package linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadAtiendeCliente extends Thread {
    private List<List<String>> almacen;
    public DataOutputStream outCliente; 
    public DataInputStream inCliente; 

    public ThreadAtiendeCliente(Socket cs, List<List<String>> almacen) {
        try {
			this.outCliente = new DataOutputStream(cs.getOutputStream());
			this.inCliente = new DataInputStream(cs.getInputStream());
	        this.almacen = almacen;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void run() {
        try {
        	String mensaje = inCliente.readUTF();
            if(mensaje.startsWith("PostNote")) {
                PostNote();
            }else if(mensaje.startsWith("RemoveNote")) {
            	RemoveNote();
            }else if(mensaje.startsWith("readNote")) {
            	readNote();
            	
            }else{
            }
        }catch(IOException e) {
            e.printStackTrace();
	    }
	}

	public void PostNote() {
		try {
			outCliente.writeUTF("Introduce la tupla que quieres guardar./n"
					+ "Separar los contenidos con espacio.");
			String mensaje = inCliente.readUTF();
			guardarTupla(mensaje);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
	private void guardarTupla(String mensaje) {
		List<String> clientetupla = mensajeAtupla(mensaje);
	    almacen.add(clientetupla);
	    try {
			outCliente.writeUTF("Tuple añadida: ");
		    for (int i = 0; i < clientetupla.size(); i++) {
	    		outCliente.writeUTF(clientetupla.get(i));
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void RemoveNote() {
		try {
			outCliente.writeUTF("Introduce la tupla que quieres eliminar./n"
					+ "Separar los contenidos con espacio.");
			String mensaje = inCliente.readUTF();
			eliminarTupla(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void eliminarTupla(String mensaje) {
		if (!almacen.isEmpty()) {
	    	List<String>tuple = null;
	    	for(int i = 0; i < almacen.size(); i++ ) {
	    		for(int j = 0; j < almacen.get(i).size(); j++ ) {
		    		if(almacen.get(i).get(j) == mensaje) {
		    			tuple = almacen.get(i);
		    			almacen.remove(almacen.get(i));
		    		}
		    	}
	    	}	       
	        System.out.println("Tupla Eliminada: " + tuple);
	    } else {
	        System.out.println("No tuples available.");
	    }
	}
	
	public void readNote() {
		try {
			outCliente.writeUTF("Introduce la tupla que quieres leer./n"
					+ "Separar los contenidos con espacio.");
			String mensaje = inCliente.readUTF();
			leerTupla(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
	private void leerTupla(String mensaje) {
		for (List<String> tupla : almacen) {
	        if (foundTuple(tupla, mensaje)) {
	            System.out.println("Tupla Encontrada: " );
	            for (int i = 0; i < tupla.size(); i++) {
		    		try {
						outCliente.writeUTF(tupla.get(i));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
	        }
	    }
	    System.out.println("No hemos encontrado la tupla dicha.");
	}
	
	public boolean foundTuple(List<String> tuple, String mensaje) {
		List<String> clientetupla = mensajeAtupla(mensaje);
	    if (tuple.size() != clientetupla.size()) {
	        return false;
	    }
	    for (int i = 0; i < tuple.size(); i++) {
	        String mismaTupla = clientetupla.get(i);
	        if (!mismaTupla.startsWith("?") && !mismaTupla.equals(tuple.get(i))) {
	            return false;
	        }
	    }
	    return true;
	}
	
	private List<String>  mensajeAtupla(String mensaje) {
		String[] mensajeTabla = mensaje.split("/s");
	    List<String> tupla =  new ArrayList<String>();
	    for (int i = 0; i < mensajeTabla.length; i++) {
	        tupla.add(mensajeTabla[i]);
	    }
		return tupla;
	}
}