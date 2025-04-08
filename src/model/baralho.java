import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class baralho {
    private List<carta> cartas;
    private carta vira;

    public baralho() {
        this.cartas = new ArrayList<>();
        gerarCartas();
    }

    private void gerarCartas() {
        String[] valores = {"4", "5", "6", "7", "Q", "J", "K", "A", "2", "3"};
        String[] naipes = {"Ouros", "Espadas", "Copas", "Paus"};

        for (String naipe : naipes) {
            for (String valor : valores) {
                carta carta = new carta(valor, naipe);
                cartas.add(carta);
            }
        }
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public carta distribuirCarta() {
        return cartas.isEmpty() ? null : cartas.remove(0);
    }

    public carta virarCarta() {
        vira = distribuirCarta();
        return vira;
    }

    public String getManilha() {
        if (vira == null) {
            return "Nenhuma manilha definida ainda.";
        }

        String valorVira = vira.getValor();
        String[] valores = {"4", "5", "6", "7", "Q", "J", "K", "A", "2", "3"};
        int index = 0;

        for (int i = 0; i < valores.length; i++) {
            if (valores[i].equals(valorVira)) {
                index = i;
                break;
            }
        }

        String valorManilha;
        if (index == valores.length - 1) {
            valorManilha = valores[0];
        } else {
            valorManilha = valores[index + 1];
        }

        return valorManilha;
    }

    public List<carta> getCartas() {
        return cartas;
    }

    public carta getVira() {
        return vira;
    }
}