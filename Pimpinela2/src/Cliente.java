import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4040)) {
            System.out.println("Conectado al servidor.");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            String mensaje, respuesta;

            while (true) {
                System.out.print("Tú: ");
                mensaje = teclado.readLine();
                salida.println(mensaje);

                respuesta = entrada.readLine();
                System.out.println("Servidor: " + respuesta);

                if (respuesta.equals("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta")) {
                    System.out.println("El servidor ha terminado la comunicación.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
