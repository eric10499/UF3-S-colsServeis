package m3UF3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class clienteEj2 {
	
	public static void main(String argv[]) {

		// Se envia por argumentos la ip
	      
	      
	      BufferedReader teclat = new BufferedReader(new InputStreamReader(System.in));

	      Socket socket;
	      InetAddress address;
	      String missatge="";
	      String missatgeServidor="";

	      try {
	    	  
	         socket = new Socket("localhost",6008);
		// Flujo de entrada del servidor
	         DataInputStream in = new DataInputStream(socket.getInputStream());
		// Flujo de salida del servidor
	         DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	         
		// Se envia mensaje al servidor
	         out.writeUTF("HELLO");
		// Se lee el mensaje del servidor
	         missatgeServidor = in.readUTF();
	         System.out.println(missatgeServidor);
	        
	         do {
	            	missatge = teclat.readLine();
			// Se envia mensaje al servidor
	           	out.writeUTF(missatge);
	        	// Se lee el mensaje del servidor
	            	missatgeServidor = in.readUTF();
	            	System.out.println(missatgeServidor);
	        } while (!missatge.startsWith("fi"));

		// Si el mensaje escrito es diferente a fi, se cierran las conexiones
		
	        in.close();
	        out.close();
	        socket.close();
	      } 
	      catch (Exception e) {
	         System.err.println(e.getMessage());
	         System.exit(1);
	      }
	   }


}
