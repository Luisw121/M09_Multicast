package mp9.uf3.udp.Tasca3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class ServerMulticast {
    public static void main(String[] args) {
        try {
            InetAddress groupAddress = InetAddress.getByName("224.0.11.111");
            int port = 5557;
            MulticastSocket multicastSocket = new MulticastSocket(port);
            multicastSocket.joinGroup(groupAddress);

            String[] palabras = {"adios", "hola", "como estas", "buenos dias", "buenas noches", "buenas tardes"};

            while (true) {
                Random random = new Random();
                int indiceAleatorio = random.nextInt(palabras.length);
                String palabraAleatoria = palabras[indiceAleatorio];
                byte[] palabraEnviada = palabraAleatoria.getBytes();

                DatagramPacket packet = new DatagramPacket(palabraEnviada, palabraEnviada.length, groupAddress, port);
                multicastSocket.send(packet);

                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
