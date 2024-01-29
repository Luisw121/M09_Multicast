package mp9.uf3.udp.Tasca2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 5555;
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Posa un numero (o 'adeu' per sortir): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("adeu")) {
                    System.out.println("Desconnectant..");
                    break;
                }
                int intent = Integer.parseInt(input);
                byte[] intentData = ByteBuffer.allocate(4).putInt(intent).array();
                DatagramPacket intentPacket = new DatagramPacket(intentData, intentData.length, serverAddress, serverPort);
                socket.send(intentPacket);

                byte[] respostaDAta = new byte[1024];
                DatagramPacket respostaPacket = new DatagramPacket(respostaDAta, respostaDAta.length);
                socket.receive(respostaPacket);

                String resposta = new String(respostaDAta, 0, respostaPacket.getLength());
                System.out.println("Resposta del servidor: " + resposta);
            }
            socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
