package mp9.uf3.udp.Tasca2;

import java.util.Random;

public class SecretNum {
    private int numeroSecret;

    public SecretNum() {
        generarNumeroAleatorio();
    }

    public void generarNumeroAleatorio() {
        Random random = new Random();
        numeroSecret = random.nextInt(100);
    }
    public int getNumeroSecret() {
        return numeroSecret;
    }
}
