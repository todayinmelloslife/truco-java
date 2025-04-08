import java.util.ArrayList;
import java.util.List;

public class jogador {
    private String nome;
    private List<carta> cartas;

    public jogador(String nome) {
        this.nome = nome;
        this.cartas = new ArrayList<>();
    }

    public void receberCarta(carta carta) {
        cartas.add(carta);
    }

    public void recalcularForcaCartas(String manilha) {
        for (carta carta : cartas) {
            carta.calcularForca(manilha);
        }
    }

    public String mostrarCartas() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cartas.size(); i++) {
            sb.append((i + 1) + ". " + cartas.get(i) + "\n");
        }
        return sb.toString();
    }

    public carta jogarCarta(int indice) {
        if (indice < 0 || indice >= cartas.size()) {
            throw new IndexOutOfBoundsException("Índice inválido para jogar carta.");
        }
        return cartas.remove(indice);
    }

    public carta jogarCarta() {
        if (!cartas.isEmpty()) {
            cartas.sort((c1, c2) -> Integer.compare(c2.getForca(), c1.getForca()));
            return cartas.remove(0);
        }
        return null;
    }

    public boolean decidirGritarTruco() {
        for (carta carta : cartas) {
            if (carta.getForca() >= 10) {
                return true;
            }
        }
        return false;
    }

    public boolean decidirAceitarTruco() {
        for (carta carta : cartas) {
            if (carta.getForca() >= 10) {
                return true;
            }
        }
        return false;
    }

    public List<carta> getCartas() {
        return cartas;
    }

    public String getNome() {
        return nome;
    }
}