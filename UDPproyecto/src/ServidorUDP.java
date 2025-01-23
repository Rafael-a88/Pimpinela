import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
    public static void main(String[] args) {
        final int PUERTO = 9090;

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP en espera de mensajes...");

            byte[] buffer = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(paqueteRecibido);
                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                String respuesta;
                switch (mensaje) {
                    case "¿Quién es?":
                        respuesta = "Soy yo";
                        break;
                    case "¿Qué vienes a buscar?":
                        respuesta = "A ti";
                        break;
                    case "Ya es tarde":
                        respuesta = "¿Por qué?";
                        break;
                    case "Porque ahora soy yo la que quiere estar sin ti":
                        respuesta = "Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta";
                        System.out.println(respuesta);
                        socket.send(new DatagramPacket(respuesta.getBytes(), respuesta.length(), paqueteRecibido.getAddress(), paqueteRecibido.getPort()));
                        return;
                    default:
                        respuesta = "Error";
                        break;
                }

                socket.send(new DatagramPacket(respuesta.getBytes(), respuesta.length(), paqueteRecibido.getAddress(), paqueteRecibido.getPort()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
