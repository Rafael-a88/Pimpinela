import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(4040)) {
            System.out.println("Servidor iniciado. Esperando conexión...");
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado.");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            String mensaje;
            boolean continuar = true;

            while (continuar && (mensaje = entrada.readLine()) != null) {
                System.out.println("Cliente: " + mensaje);

                switch (mensaje) {
                    case "¿Quién es?":
                        salida.println("Soy yo");
                        break;
                    case "¿Qué vienes a buscar?":
                        salida.println("A ti");
                        break;
                    case "Ya es tarde":
                        salida.println("¿Por qué?");
                        break;
                    case "Porque ahora soy yo la que quiere estar sin ti":
                        salida.println("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta");
                        continuar = false;
                        break;
                    default:
                        salida.println("Error");
                }
            }

            System.out.println("Cerrando conexión...");
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
