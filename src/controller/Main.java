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

        int partidasJogadas = 0;
        int vitoriasJogador1 = 0;
        int vitoriasJogador2 = 0;

        while (partidasJogadas < 3) { // Controle de 3 partidas
            int pontosJogador1 = 0;
            int pontosJogador2 = 0;

            while (pontosJogador1 < 12 && pontosJogador2 < 12) { // Controle de rodadas
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
                scanner.nextLine(); // Limpa o buffer
                boolean trucoAceito = false;
                int pontosRodada = 1;

                if (pedirTruco.equals("s")) {
                    limparConsole();
                    jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
                    jogo.mensagemAceitarTruco(jogador2.getNome());
                    String respostaTruco = scanner.next().toLowerCase();
                    scanner.nextLine(); // Limpa o buffer
                    if (respostaTruco.equals("aceitar")) {
                        trucoAceito = true;
                        pontosRodada = 3;
                        jogo.mensagemTrucoAceito(jogador2.getNome(), 3);
                    } else if (respostaTruco.equals("negar")) {
                        jogo.mensagemTrucoAceito(jogador2.getNome(), jogador1.getNome());
                        pontosJogador1++;
                        break; // Finaliza a rodada
                    } else if (respostaTruco.equals("6")) {
                        limparConsole();
                        jogo.mensagemCartasJogador(jogador1.getNome(), jogador1.mostrarCartas());
                        jogo.mensagemAceitarSeis(jogador1.getNome());
                        String respostaSeis = scanner.next().toLowerCase();
                        scanner.nextLine(); // Limpa o buffer
                        if (respostaSeis.equals("s")) { // Alterado para aceitar "s" para 6
                            pontosRodada = 6;
                            jogo.mensagemTrucoAceito(jogador1.getNome(), 6);
                        } else if (respostaSeis.equals("n")) { // Alterado para aceitar "n" para negar
                            jogo.mensagemTrucoAceito(jogador1.getNome(), jogador2.getNome());
                            pontosJogador2 += 6;
                            break; // Finaliza a rodada
                        } else if (respostaSeis.equals("9")) {
                            limparConsole();
                            jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
                            jogo.mensagemAceitarNove(jogador2.getNome());
                            String respostaNove = scanner.next().toLowerCase();
                            scanner.nextLine(); // Limpa o buffer
                            if (respostaNove.equals("s")) { // Alterado para aceitar "s" para 9
                                pontosRodada = 9;
                                jogo.mensagemTrucoAceito(jogador2.getNome(), 9);
                            } else if (respostaNove.equals("n")) {
                                jogo.mensagemTrucoAceito(jogador2.getNome(), jogador1.getNome());
                                pontosJogador1 += 9;
                                break; // Finaliza a rodada
                            }
                        }
                    }
                } else {
                    jogo.mensagemTrucoNaoPedido(jogador1.getNome());
                    trucoAceito = false; // Adicionado para garantir que o jogo continue
                }

                carta cartaJogador1 = null;
                do {
                    try {
                        jogo.mensagemEscolherCarta(jogador1.getNome());
                        int indice = scanner.nextInt() - 1;
                        scanner.nextLine(); // Limpa o buffer
                        cartaJogador1 = jogador1.jogarCarta(indice);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Índice inválido. Tente novamente.");
                    }
                } while (cartaJogador1 == null);
                jogo.mensagemJogadorJogou(jogador1.getNome(), cartaJogador1.toString());

                limparConsole();
                jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
                jogo.mensagemPedirTruco(jogador2.getNome());
                String pedirTrucoJogador2 = scanner.next().toLowerCase();
                scanner.nextLine(); // Limpa o buffer
                if (pedirTrucoJogador2.equals("s")) {
                    limparConsole();
                    jogo.mensagemCartasJogador(jogador1.getNome(), jogador1.mostrarCartas());
                    jogo.mensagemAceitarTruco(jogador1.getNome());
                    String respostaTrucoJogador1 = scanner.next().toLowerCase();
                    scanner.nextLine(); // Limpa o buffer
                    if (respostaTrucoJogador1.equals("aceitar")) {
                        pontosRodada = 3;
                        jogo.mensagemTrucoAceito(jogador1.getNome(), 3);
                    } else if (respostaTrucoJogador1.equals("negar")) {
                        jogo.mensagemTrucoAceito(jogador1.getNome(), jogador2.getNome());
                        pontosJogador2++;
                        break; // Finaliza a rodada
                    } else if (respostaTrucoJogador1.equals("6")) {
                        limparConsole();
                        jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
                        jogo.mensagemAceitarSeis(jogador2.getNome());
                        String respostaSeisJogador2 = scanner.next().toLowerCase();
                        scanner.nextLine(); // Limpa o buffer
                        if (respostaSeisJogador2.equals("s")) { // Alterado para aceitar "s" para 6
                            pontosRodada = 6;
                            jogo.mensagemTrucoAceito(jogador2.getNome(), 6);
                        } else if (respostaSeisJogador2.equals("n")) {
                            jogo.mensagemTrucoAceito(jogador2.getNome(), jogador1.getNome());
                            pontosJogador1 += 6;
                            break; // Finaliza a rodada
                        }
                    }
                }

                limparConsole();
                jogo.mensagemUltimaCartaJogada(jogador1.getNome(), cartaJogador1.toString());
                jogo.mensagemCartaVira(vira.toString());

                jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
                jogo.mensagemEscolherCarta(jogador2.getNome());
                carta cartaJogador2 = jogador2.jogarCarta(scanner.nextInt() - 1);
                scanner.nextLine(); // Limpa o buffer
                jogo.mensagemJogadorJogou(jogador2.getNome(), cartaJogador2.toString());

                limparConsole();
                jogo.mensagemCartasJogador(jogador1.getNome(), jogador1.mostrarCartas());
                jogo.mensagemPedirTruco(jogador1.getNome());
                String pedirTrucoJogador1 = scanner.next().toLowerCase();
                scanner.nextLine(); // Limpa o buffer
                if (pedirTrucoJogador1.equals("s")) {
                    limparConsole();
                    jogo.mensagemCartasJogador(jogador2.getNome(), jogador2.mostrarCartas());
                    jogo.mensagemAceitarTruco(jogador2.getNome());
                    String respostaTrucoJogador2 = scanner.next().toLowerCase();
                    scanner.nextLine(); // Limpa o buffer
                    if (respostaTrucoJogador2.equals("aceitar")) {
                        pontosRodada = 3;
                        jogo.mensagemTrucoAceito(jogador2.getNome(), 3);
                    } else if (respostaTrucoJogador2.equals("negar")) {
                        jogo.mensagemTrucoAceito(jogador2.getNome(), jogador1.getNome());
                        pontosJogador1++;
                        break; // Finaliza a rodada
                    } else if (respostaTrucoJogador2.equals("6")) {
                        limparConsole();
                        jogo.mensagemCartasJogador(jogador1.getNome(), jogador1.mostrarCartas());
                        jogo.mensagemAceitarSeis(jogador1.getNome());
                        String respostaSeisJogador1 = scanner.next().toLowerCase();
                        scanner.nextLine(); // Limpa o buffer
                        if (respostaSeisJogador1.equals("s")) { // Alterado para aceitar "s" para 6
                            pontosRodada = 6;
                            jogo.mensagemTrucoAceito(jogador1.getNome(), 6);
                        } else if (respostaSeisJogador1.equals("n")) {
                            jogo.mensagemTrucoAceito(jogador1.getNome(), jogador2.getNome());
                            pontosJogador2 += 6;
                            break; // Finaliza a rodada
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
            }

            limparConsole();
            jogo.mensagemPlacarFinal(jogador1.getNome(), pontosJogador1, jogador2.getNome(), pontosJogador2);

            if (pontosJogador1 > pontosJogador2) {
                jogo.mensagemVencedorJogo(jogador1.getNome());
                vitoriasJogador1++;
            } else if (pontosJogador1 < pontosJogador2) {
                jogo.mensagemVencedorJogo(jogador2.getNome());
                vitoriasJogador2++;
            } else {
                jogo.mensagemEmpateJogo();
            }

            partidasJogadas++;
            System.out.println("Partida " + partidasJogadas + " finalizada. Pressione Enter para iniciar a próxima partida...");
            scanner.nextLine();
        }

        limparConsole();
        System.out.println("===== Resultado Final =====");
        System.out.println(jogador1.getNome() + ": " + vitoriasJogador1 + " vitórias");
        System.out.println(jogador2.getNome() + ": " + vitoriasJogador2 + " vitórias");

        if (vitoriasJogador1 > vitoriasJogador2) {
            System.out.println("Grande vencedor: " + jogador1.getNome());
        } else if (vitoriasJogador1 < vitoriasJogador2) {
            System.out.println("Grande vencedor: " + jogador2.getNome());
        } else {
            System.out.println("O jogo terminou empatado!");
        }

        scanner.close();
    }
}
