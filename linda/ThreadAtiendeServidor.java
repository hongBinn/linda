package linda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadAtiendeServidor extends Thread {
    private List<List<String>> almacen;
    public DataOutputStream out; 
    public DataInputStream in; 
    public Socket cs ;

    public ThreadAtiendeServidor(Socket cs, List<List<String>> almacen) {
        try {
        	this.cs = cs;
			this.out = new DataOutputStream(cs.getOutputStream());
			this.in = new DataInputStream(cs.getInputStream());
	        this.almacen = almacen;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void run() {
    	while(true) {
	        try {
				out.writeUTF("SERVIDOR CONECTADO A LINDA");
	        	out.writeUTF("Operacion: \n"
	        			+	"PostNote: PN \n"
	        			+	"ReadNote: RN \n"
	        			+	"RemoveNote: RVN");
				out.writeUTF("MENSAJE FIN");
	        	String mensaje = in.readUTF();
	            if(mensaje.toUpperCase().equals("PN") || mensaje.toUpperCase().equals("POSTNOTE")) {
	                PostNote();
	            }else if(mensaje.toUpperCase().equals("RVN") || mensaje.toUpperCase().equals("REMOVENOTE")) {
	            	RemoveNote();
	            }else if(mensaje.toUpperCase().equals("RN") || mensaje.toUpperCase().equals("READNOTE")) {
	            	readNote();
	            }else{
	            	out.writeUTF("Operacion Incorrecto");
					out.writeUTF("MENSAJE FIN");
	            }
	        }catch(IOException e) {
	            e.printStackTrace();
		    }
    	}
	}

	public void PostNote() {
		try {
			out.writeUTF("Introduce la tupla que quieres guardar./n"
					+ "Separar los contenidos con espacio.");
			String mensaje = in.readUTF();
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
			out.writeUTF("Tuple añadida: ");
		    for (int i = 0; i < clientetupla.size(); i++) {
	    		out.writeUTF(clientetupla.get(i));
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void RemoveNote() {
		try {
			out.writeUTF("Introduce la tupla que quieres eliminar./n"
					+ "Separar los contenidos con espacio.");
			String mensaje = in.readUTF();
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
			out.writeUTF("Introduce la tupla que quieres leer./n"
					+ "Separar los contenidos con espacio.");
			String mensaje = in.readUTF();
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
						out.writeUTF(tupla.get(i));
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