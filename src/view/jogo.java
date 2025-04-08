public class jogo {
    public static void mensagemBoasVindas() {
        System.out.println("Bem-vindo ao Truco!");
    }

    public static void mensagemDigiteNomeJogador(int numeroJogador) {
        System.out.print("Digite o nome do Jogador " + numeroJogador + ": ");
    }

    public static void mensagemNovaRodada() {
        System.out.println("Nova rodada iniciada!");
    }

    public static void mensagemCartaVira(String vira) {
        System.out.println("A carta vira é: " + vira);
    }

    public static void mensagemManilha(String manilha) {
        System.out.println("A manilha é: " + manilha);
    }

    public static void mensagemCartasJogador(String nomeJogador, String cartas) {
        System.out.println("\nCartas do " + nomeJogador + ":");
        System.out.println(cartas);
    }

    public static void mensagemPedirTruco(String nomeJogador) {
        System.out.println("\n" + nomeJogador + ", deseja pedir Truco? (s/n): ");
    }

    public static void mensagemAceitarTruco(String nomeJogador) {
        System.out.println("\n" + nomeJogador + ", deseja aceitar o Truco, negar ou pedir 6 pontos? (aceitar/negar/6): ");
    }

    public static void mensagemEscolherCarta(String nomeJogador) {
        System.out.println("\n" + nomeJogador + ", escolha uma carta para jogar (1-3): ");
    }

    public static void mensagemJogadorJogou(String nomeJogador, String carta) {
        System.out.println(nomeJogador + " jogou: " + carta);
    }

    public static void mensagemVencedorRodada(String nomeJogador) {
        System.out.println(nomeJogador + " venceu a rodada!");
    }

    public static void mensagemEmpateRodada() {
        System.out.println("A rodada empatou!");
    }

    public static void mensagemPlacarAtual(String nomeJogador1, int pontosJogador1, String nomeJogador2, int pontosJogador2) {
        System.out.println("\nPlacar Atual:");
        System.out.println(nomeJogador1 + ": " + pontosJogador1 + " pontos");
        System.out.println(nomeJogador2 + ": " + pontosJogador2 + " pontos");
    }

    public static void mensagemPlacarFinal(String nomeJogador1, int pontosJogador1, String nomeJogador2, int pontosJogador2) {
        System.out.println("Placar Final:");
        System.out.println(nomeJogador1 + ": " + pontosJogador1 + " pontos");
        System.out.println(nomeJogador2 + ": " + pontosJogador2 + " pontos");
    }

    public static void mensagemVencedorJogo(String nomeJogador) {
        System.out.println(nomeJogador + " venceu o jogo!");
    }

    public static void mensagemEmpateJogo() {
        System.out.println("O jogo terminou empatado!");
    }

    public static void mensagemTrucoAceito(String nome, int pontos) {
        System.out.println(nome + " aceitou o Truco! A rodada agora vale " + pontos + " pontos.");
    }

    public static void mensagemTrucoAceito(String nome1, String nome2) {
        System.out.println(nome1 + " negou o Truco. Os pontos vão para " + nome2 + ".");
    }

    public static void mensagemAceitarSeis(String nome) {
        System.out.println(nome + ", deseja aceitar 6 pontos? (s/n): ");
    }

    public static void mensagemAceitarNove(String nome) {
        System.out.println(nome + ", deseja aceitar 9 pontos? (s/n): ");
    }

    public static void mensagemTrucoNaoPedido(String nome) {
        System.out.println(nome + " decidiu não pedir Truco.");
    }

    public static void mensagemUltimaCartaJogada(String nome, String carta) {
        System.out.println("\nÚltima carta jogada por " + nome + ": " + carta);
    }
}
