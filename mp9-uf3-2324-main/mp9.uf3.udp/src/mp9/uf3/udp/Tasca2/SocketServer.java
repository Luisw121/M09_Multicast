package mp9.uf3.udp.Tasca2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class SocketServer {
    public static void main (String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5555);
            SecretNum secretNum = new SecretNum();

            while (true) {
                byte[] intentData = new byte[4];
                DatagramPacket intentPacket = new DatagramPacket(intentData, intentData.length);
                socket.receive(intentPacket);

                InetAddress clientAddress = intentPacket.getAddress();
                int clientPort = intentPacket.getPort();
                int intentClient = ByteBuffer.wrap(intentData).getInt();

                int resultatComparacio = Integer.compare(intentClient, secretNum.getNumeroSecret());

                String resposta = obtenirResposta(resultatComparacio);
                byte[] respostaData = resposta.getBytes();
                DatagramPacket respostaPacket = new DatagramPacket(respostaData, respostaData.length, clientAddress, clientPort);
                socket.send(respostaPacket);

                if (resultatComparacio == 0) {
                    secretNum.generarNumeroAleatorio();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String obtenirResposta(int resultatComparacio) {
        switch (resultatComparacio) {
            case -1:
                return "El número secret és més gran.";
            case 1:
                return "El número secret és més petit.";
            case 0:
                return "Felicitats! Has encertat el número secret.";
            default:
                return "Error desconegut.";
        }
    }
}
