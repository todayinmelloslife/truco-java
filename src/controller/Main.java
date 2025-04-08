import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Não foi possível limpar o console.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        jogo.mensagemBoasVindas();
        jogo.mensagemDigiteNomeJogador(1);
        jogador jogador1 = new jogador(scanner.nextLine());
        jogo.mensagemDigiteNomeJogador(2);
        jogador jogador2 = new jogador(scanner.nextLine());

        int pontosJogador1 = 0;
        int pontosJogador2 = 0;

        while (pontosJogador1 < 12 && pontosJogador2 < 12) {
            limparConsole();
            jogo.mensagemNovaRodada();

            baralho baralho = new baralho();
            baralho.embaralhar();
            jogador1.getCartas().clear();
            jogador2.getCartas().clear();
            for (int i = 0; i < 3; i++) {
                jogador1.receberCarta(baralho.distribuirCarta());
                jogador2.receberCarta(baralho.distribuirCarta());
            }

            carta vira = baralho.virarCarta();
            String manilha = baralho.getManilha();
            jogo.mensagemCartaVira(vira.toString());
            jogo.mensagemManilha(manilha);

            jogador1.recalcularForcaCartas(manilha);
            jogador2.recalcularForcaCartas(manilha);

            jogo.mensagemCartasJogador(jogador1.getNome(), jogador1.mostrarCartas());
            jogo.mensagemPedirTruco(jogador1.getNome());
            String pedirTruco = scanner.next().toLowerCase();
            boolean trucoAceito = false;
            int pontosRodada = 1;

            if (pedirTruco.equals("s")) {
                limparConsole();
                jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
                jogo.mensagemAceitarTruco(jogador2.getNome());
                String respostaTruco = scanner.next().toLowerCase();
                if (respostaTruco.equals("aceitar")) {
                    trucoAceito = true;
                    pontosRodada = 3;
                    jogo.mensagemTrucoAceito(jogador2.getNome(), 3);
                } else if (respostaTruco.equals("negar")) {
                    jogo.mensagemTrucoAceito(jogador2.getNome(), jogador1.getNome());
                    pontosJogador1++;
                    continue;
                } else if (respostaTruco.equals("6")) {
                    limparConsole();
                    jogo.mensagemCartasJogador(jogador1.getNome(), jogador1.mostrarCartas());
                    jogo.mensagemAceitarSeis(jogador1.getNome());
                    String respostaSeis = scanner.next().toLowerCase();
                    if (respostaSeis.equals("s")) { // Alterado para aceitar "s" para 6
                        pontosRodada = 6;
                        jogo.mensagemTrucoAceito(jogador1.getNome(), 6);
                    } else if (respostaSeis.equals("n")) { // Alterado para aceitar "n" para negar
                        jogo.mensagemTrucoAceito(jogador1.getNome(), jogador2.getNome());
                        pontosJogador2 += 6;
                        continue;
                    } else if (respostaSeis.equals("9")) {
                        limparConsole();
                        jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
                        jogo.mensagemAceitarNove(jogador2.getNome());
                        String respostaNove = scanner.next().toLowerCase();
                        if (respostaNove.equals("s")) { // Alterado para aceitar "s" para 9
                            pontosRodada = 9;
                            jogo.mensagemTrucoAceito(jogador2.getNome(), 9);
                        } else {
                            jogo.mensagemTrucoAceito(jogador2.getNome(), jogador1.getNome());
                            pontosJogador1 += 9;
                            continue;
                        }
                    }
                }
            } else {
                jogo.mensagemTrucoNaoPedido(jogador1.getNome());
            }

            jogo.mensagemEscolherCarta(jogador1.getNome());
            carta cartaJogador1 = jogador1.jogarCarta(scanner.nextInt() - 1);
            jogo.mensagemJogadorJogou(jogador1.getNome(), cartaJogador1.toString());

            limparConsole();
            jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
            jogo.mensagemPedirTruco(jogador2.getNome());
            String pedirTrucoJogador2 = scanner.next().toLowerCase();
            if (pedirTrucoJogador2.equals("s")) {
                limparConsole();
                jogo.mensagemCartasJogador(jogador1.getNome(), jogador1.mostrarCartas());
                jogo.mensagemAceitarTruco(jogador1.getNome());
                String respostaTrucoJogador1 = scanner.next().toLowerCase();
                if (respostaTrucoJogador1.equals("aceitar")) {
                    pontosRodada = 3;
                    jogo.mensagemTrucoAceito(jogador1.getNome(), 3);
                } else if (respostaTrucoJogador1.equals("negar")) {
                    jogo.mensagemTrucoAceito(jogador1.getNome(), jogador2.getNome());
                    pontosJogador2++;
                    continue;
                } else if (respostaTrucoJogador1.equals("6")) {
                    limparConsole();
                    jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
                    jogo.mensagemAceitarSeis(jogador2.getNome());
                    String respostaSeisJogador2 = scanner.next().toLowerCase();
                    if (respostaSeisJogador2.equals("s")) { // Alterado para aceitar "s" para 6
                        pontosRodada = 6;
                        jogo.mensagemTrucoAceito(jogador2.getNome(), 6);
                    } else {
                        jogo.mensagemTrucoAceito(jogador2.getNome(), jogador1.getNome());
                        pontosJogador1 += 6;
                        continue;
                    }
                }
            }

            limparConsole();
            jogo.mensagemUltimaCartaJogada(jogador1.getNome(), cartaJogador1.toString());
            jogo.mensagemCartaVira(vira.toString());

            jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
            jogo.mensagemEscolherCarta(jogador2.getNome());
            carta cartaJogador2 = jogador2.jogarCarta(scanner.nextInt() - 1);
            jogo.mensagemJogadorJogou(jogador2.getNome(), cartaJogador2.toString());

            limparConsole();
            jogo.mensagemCartasJogador(jogador1.getNome(), jogador1.mostrarCartas());
            jogo.mensagemPedirTruco(jogador1.getNome());
            String pedirTrucoJogador1 = scanner.next().toLowerCase();
            if (pedirTrucoJogador1.equals("s")) {
                limparConsole();
                jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
                jogo.mensagemAceitarTruco(jogador2.getNome());
                String respostaTrucoJogador2 = scanner.next().toLowerCase();
                if (respostaTrucoJogador2.equals("aceitar")) {
                    pontosRodada = 3;
                    jogo.mensagemTrucoAceito(jogador2.getNome(), 3);
                } else if (respostaTrucoJogador2.equals("negar")) {
                    jogo.mensagemTrucoAceito(jogador2.getNome(), jogador1.getNome());
                    pontosJogador1++;
                    continue;
                } else if (respostaTrucoJogador2.equals("6")) {
                    limparConsole();
                    jogo.mensagemCartasJogador(jogador1.getNome(), jogador1.mostrarCartas());
                    jogo.mensagemAceitarSeis(jogador1.getNome());
                    String respostaSeisJogador1 = scanner.next().toLowerCase();
                    if (respostaSeisJogador1.equals("s")) { // Alterado para aceitar "s" para 6
                        pontosRodada = 6;
                        jogo.mensagemTrucoAceito(jogador1.getNome(), 6);
                    } else {
                        jogo.mensagemTrucoAceito(jogador1.getNome(), jogador2.getNome());
                        pontosJogador2 += 6;
                        continue;
                    }
                }
            }

            if (cartaJogador1.getForca() > cartaJogador2.getForca()) {
                jogo.mensagemVencedorRodada(jogador1.getNome());
                pontosJogador1 += pontosRodada;
            } else if (cartaJogador1.getForca() < cartaJogador2.getForca()) {
                jogo.mensagemVencedorRodada(jogador2.getNome());
                pontosJogador2 += pontosRodada;
            } else {
                jogo.mensagemEmpateRodada();
            }

            jogo.mensagemPlacarAtual(jogador1.getNome(), pontosJogador1, jogador2.getNome(), pontosJogador2);

            System.out.println("Pressione Enter para continuar para a próxima rodada...");
            scanner.nextLine();
            scanner.nextLine();
        }

        limparConsole();
        jogo.mensagemPlacarFinal(jogador1.getNome(), pontosJogador1, jogador2.getNome(), pontosJogador2);

        if (pontosJogador1 > pontosJogador2) {
            jogo.mensagemVencedorJogo(jogador1.getNome());
        } else if (pontosJogador1 < pontosJogador2) {
            jogo.mensagemVencedorJogo(jogador2.getNome());
        } else {
            jogo.mensagemEmpateJogo();
        }

    scanner.close();
}
}
