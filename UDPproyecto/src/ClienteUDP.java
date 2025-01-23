import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        final String SERVIDOR = "localhost";
        final int PUERTO = 9090;

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccionServidor = InetAddress.getByName(SERVIDOR);
            Scanner scanner = new Scanner(System.in);
            String mensaje;

            while (true) {
                System.out.print("Ingrese un mensaje: ");
                mensaje = scanner.nextLine();

                byte[] bufferMensaje = mensaje.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(bufferMensaje, bufferMensaje.length, direccionServidor, PUERTO);
                socket.send(paqueteEnvio);

                byte[] bufferRespuesta = new byte[1024];
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
                socket.receive(paqueteRespuesta);
                String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                System.out.println("Respuesta del servidor: " + respuesta);

                if (respuesta.equals("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
