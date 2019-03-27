package m3UF3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorEj2 {
	public static void main(String argv[]) {
		ServerSocket socketEscolta;
		boolean fi = false;
		String missatge = "";
		
		Producto producto1 = new Producto("REF", "Refresco", 2);
		Producto producto2 = new Producto("PAT", "Patates fregides", 3);
		Producto producto3 = new Producto("BOC", "Entrepà", 1);
		Producto producto4 = new Producto("REF", "Refresco", 2);
		Producto producto5 = new Producto("ENS", "Ensaladilla", 4);
		Producto producto6 = new Producto("CAF", "Cafè", 1);



		try {
			socketEscolta = new ServerSocket(6008);
			while(1>0) {
				Socket socketClient = socketEscolta.accept();
				// Flujo de entrada del cliente
				DataInputStream in = new DataInputStream(socketClient.getInputStream());	
				// Flujo de salida del cliente
				DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());	
				fi = false;

				while (!fi) {
					// Leemos mensaje
					missatge = in.readUTF();
					if (missatge.startsWith("HELLO")) {
						System.out.println(missatge);
						// Se escribe el mensaje que a recibido del cliente
						out.writeUTF(missatge);
						fi = true;
					}
					else {
						// Se escribe este mensaje al cliente
						System.out.println("Error, has de saludar.");
						out.writeUTF("Error, has de saludar.");
					}
					
				}

				fi = false;
				while (!fi) {
					// Lee y escribe el mensaje. Dependiendo de como empiece, escribe una cosa u otra.
					missatge = in.readUTF();
					
					if (missatge.startsWith("REF")) {
						System.out.println("REF " + producto1.getPrecio());
						out.writeUTF("REF " + producto1.getPrecio());
					}						
					else if (missatge.startsWith("PAT")) {
						System.out.println("PAT "+ producto2.getPrecio());
						out.writeUTF("PAT "+ producto2.getPrecio());
					}
					else if (missatge.startsWith("CRO")) {
						System.out.println("CRO "+ producto3.getPrecio());
						out.writeUTF("CRO"+ producto3.getPrecio());
					}
					else if (missatge.startsWith("BOC")) {
						System.out.println("BOC "+ producto4.getPrecio());
						out.writeUTF("BOC "+ producto4.getPrecio());
					}
					else if (missatge.startsWith("ENS")) {
						System.out.println("ENS "+ producto5.getPrecio());
						out.writeUTF("ENS "+ producto5.getPrecio());
					}
					else if (missatge.startsWith("CAF")) {
						System.out.println("CAF "+ producto6.getPrecio());
						out.writeUTF("CAF "+ producto6.getPrecio());
					}
					else if (missatge.startsWith("fi")) {
						// En este caso, se cierra el flujo de entrada y el de salida. Acaba la conexión.
						System.out.println(missatge);
						out.writeUTF(missatge);
						in.close();
						out.close();
						socketClient.close();
						fi = true;
					} 
					else {
						System.out.println(missatge);
						out.writeUTF("ERROR. Recuerda que debes escribir la referencia de tu producto en Mayúscula. Sino, este no disponemos de este producto");
					}
				}
			}
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
