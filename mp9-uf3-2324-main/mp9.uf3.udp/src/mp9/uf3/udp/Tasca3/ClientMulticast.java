package mp9.uf3.udp.Tasca3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;

public class ClientMulticast {
    public static void main(String[] args) {
        try {
            InetAddress groupAddress = InetAddress.getByName("224.0.11.111");
            int port = 5557;
            MulticastSocket multicastSocket = new MulticastSocket(port);
            multicastSocket.joinGroup(groupAddress);

            Map<String, Integer> palabrasContador = new HashMap<>();

            byte[] receivedData = new byte[1024];
            DatagramPacket packet = new DatagramPacket(receivedData, receivedData.length);

            System.out.println("Cliente Multicast esta en linea. Esperando palabras..");

            while (true) {
                multicastSocket.receive(packet);
                String palabraRecibida = new String(packet.getData(), 0, packet.getLength());

                palabrasContador.put(palabraRecibida, palabrasContador.getOrDefault(palabraRecibida, 0) + 1);

                System.out.println("Palabra recibida: " + palabraRecibida);
                System.out.println("Contador actual: " + palabrasContador.get(palabraRecibida));

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
