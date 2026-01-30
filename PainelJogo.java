package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Elementos_do_Jogo.*;

public class PainelJogo extends JPanel implements Runnable {

    Thread gameThread;

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int cutsceneState = 2;

    Rectangle btnIniciarArea, btnSairArea;

    BufferedImage imgFundoMenu, imgTitulo, imgIniciar, imgSair, imgCutsceneFundo;
    BufferedImage imgArena;

    BufferedImage sprTanque, sprMago, sprAtirador;

    int LARGURA = 800;
    int ALTURA = 600;

    int SPRITE_W = 96;
    int SPRITE_H = 96;

    int CHAO_Y = 455;

    // POSIÇÃO DOS BOTÕES (mais fácil de entender/ajustar)
    int BTN_Y = 400;
    int BTN_W = 180;
    int BTN_X1 = 200;
    int BTN_X2 = 420;

    String[] cutsceneDialogos = {
            "Em um reino mistico, a paz e mantida",
            "atraves de um antigo ritual...",
            "conhecido como o Grande Torneio.",
            "A cada geracao, as duas maiores",
            "faccoes do mundo se enfrentam.",
            "Voce e o Senhor da Guerra,",
            "responsavel por comandar a batalha."
    };
    int dialogoAtualIndex = 0;

    Font fonteDialogo = new Font("Monospaced", Font.BOLD, 16);

    Arena arena;
    List<String> logBatalha = new ArrayList<>();

    String nomeTimeA = "Alianca da Luz";
    String nomeTimeB = "Horda das Sombras";

    public PainelJogo() {
        setPreferredSize(new Dimension(LARGURA, ALTURA));
        setBackground(Color.BLACK);
        setFocusable(true);

        carregarImagens();
        configurarBotoes();

        addMouseListener(new MouseHandler());
        addKeyListener(new KeyHandler());

        gameState = titleState;
        startGameThread();
    }

    private void carregarImagens() {
        try {
            imgTitulo = ImageIO.read(new File("Imagens/titulo.png"));
            imgIniciar = ImageIO.read(new File("Imagens/iniciar.png"));
            imgSair = ImageIO.read(new File("Imagens/sair.png"));

            imgFundoMenu = ImageIO.read(new File("Imagens/fundo.png"));
            imgCutsceneFundo = ImageIO.read(new File("Imagens/cutscene.png"));
            imgArena = ImageIO.read(new File("Imagens/Arena.png"));

            sprTanque = ImageIO.read(new File("Imagens/tanque.png"));
            sprMago = ImageIO.read(new File("Imagens/mago.png"));
            sprAtirador = ImageIO.read(new File("Imagens/atirador.png"));
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagens.");
        }
    }

    private void configurarBotoes() {
        btnIniciarArea = new Rectangle(BTN_X1, BTN_Y, BTN_W, 60);
        btnSairArea = new Rectangle(BTN_X2, BTN_Y, BTN_W, 60);

        if (imgIniciar != null) {
            btnIniciarArea.height = (int) (BTN_W * ((double) imgIniciar.getHeight() / imgIniciar.getWidth()));
        }
        if (imgSair != null) {
            btnSairArea.height = (int) (BTN_W * ((double) imgSair.getHeight() / imgSair.getWidth()));
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void configurarBatalha() {
        arena = new Arena();
        logBatalha.clear();

        int qtdA = 1;
        int qtdB = 1;

        try {
            String sA = JOptionPane.showInputDialog(this, "Quantos combatentes na " + nomeTimeA + "?");
            if (sA != null) qtdA = Integer.parseInt(sA.trim());
        } catch (Exception e) { qtdA = 1; }

        try {
            String sB = JOptionPane.showInputDialog(this, "Quantos combatentes na " + nomeTimeB + "?");
            if (sB != null) qtdB = Integer.parseInt(sB.trim());
        } catch (Exception e) { qtdB = 1; }

        if (qtdA <= 0) qtdA = 1;
        if (qtdB <= 0) qtdB = 1;
        if (qtdA > 100) qtdA = 100;
        if (qtdB > 100) qtdB = 100;

        for (int i = 1; i <= qtdA; i++) {
            String nome = JOptionPane.showInputDialog(this, nomeTimeA + " - Combatente " + i + "\nNome:");
            if (nome == null || nome.trim().isEmpty()) nome = "A" + i;

            String[] opcoes = {"Tanque", "Mago", "Atirador"};
            int tipo = JOptionPane.showOptionDialog(this, "Classe:", nomeTimeA + " - " + nome,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[0]);
            if (tipo < 0) tipo = 0;

            Combatente c;
            if (tipo == 0) c = new Tanque(nome);
            else if (tipo == 1) c = new Mago(nome);
            else c = new Atirador(nome);

            arena.adicionarLadoA(c);
        }

        for (int i = 1; i <= qtdB; i++) {
            String nome = JOptionPane.showInputDialog(this, nomeTimeB + " - Combatente " + i + "\nNome:");
            if (nome == null || nome.trim().isEmpty()) nome = "B" + i;

            String[] opcoes = {"Tanque", "Mago", "Atirador"};
            int tipo = JOptionPane.showOptionDialog(this, "Classe:", nomeTimeB + " - " + nome,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opcoes, opcoes[0]);
            if (tipo < 0) tipo = 0;

            Combatente c;
            if (tipo == 0) c = new Tanque(nome);
            else if (tipo == 1) c = new Mago(nome);
            else c = new Atirador(nome);

            arena.adicionarLadoB(c);
        }

        logBatalha.add("BATALHA INICIADA!");
        logBatalha.add(nomeTimeA + " VS " + nomeTimeB);
        logBatalha.add("RESOLUCAO DE DANO:");
        logBatalha.add("1) Forca do golpe (magia/critico/etc).");
        logBatalha.add("2) Defensor tenta se defender.");
        logBatalha.add("3) Dano final reduz os PV.");
        logBatalha.add("ENTER para avancar.");

        gameState = playState;
    }

    @Override
    public void run() {
        while (gameThread != null) {
            repaint();
            try { Thread.sleep(16); } catch (Exception e) {}
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            if (imgFundoMenu != null) g2.drawImage(imgFundoMenu, 0, 0, LARGURA, ALTURA, null);
            else { g2.setColor(Color.BLACK); g2.fillRect(0,0,LARGURA,ALTURA); }

            if (imgTitulo != null) {
                int w = 380;
                int h = (int) (w * ((double) imgTitulo.getHeight() / imgTitulo.getWidth()));
                g2.drawImage(imgTitulo, (LARGURA - w) / 2, 100, w, h, null);
            }

            if (imgIniciar != null) g2.drawImage(imgIniciar, btnIniciarArea.x, btnIniciarArea.y, btnIniciarArea.width, btnIniciarArea.height, null);
            else { g2.setColor(Color.GREEN); g2.fillRect(btnIniciarArea.x, btnIniciarArea.y, btnIniciarArea.width, btnIniciarArea.height); }

            if (imgSair != null) g2.drawImage(imgSair, btnSairArea.x, btnSairArea.y, btnSairArea.width, btnSairArea.height, null);
            else { g2.setColor(Color.RED); g2.fillRect(btnSairArea.x, btnSairArea.y, btnSairArea.width, btnSairArea.height); }
        }
        else if (gameState == cutsceneState) {
            if (imgCutsceneFundo != null) g2.drawImage(imgCutsceneFundo, 0, 0, LARGURA, ALTURA, null);
            else { g2.setColor(Color.BLACK); g2.fillRect(0,0,LARGURA,ALTURA); }

            int boxW = 600, boxH = 130, boxX = 100, boxY = 440;

            g2.setColor(Color.BLACK);
            g2.fillRect(boxX, boxY, boxW, boxH);

            g2.setColor(Color.WHITE);
            g2.drawRect(boxX, boxY, boxW, boxH);

            g2.setFont(fonteDialogo);

            if (dialogoAtualIndex < 0) dialogoAtualIndex = 0;
            if (dialogoAtualIndex >= cutsceneDialogos.length) dialogoAtualIndex = cutsceneDialogos.length - 1;

            g2.drawString(cutsceneDialogos[dialogoAtualIndex], boxX + 30, boxY + 60);
            g2.drawString("[ESPACO] >>", boxX + boxW - 120, boxY + boxH - 15);
        }
        else if (gameState == playState) {
            if (imgArena != null) g2.drawImage(imgArena, 0, 0, LARGURA, ALTURA, null);
            else { g2.setColor(new Color(60,60,60)); g2.fillRect(0,0,LARGURA,ALTURA); }

            g2.setColor(Color.CYAN);
            g2.drawString(nomeTimeA, 100, 40);
            g2.setColor(Color.RED);
            g2.drawString(nomeTimeB, 600, 40);

            if (arena != null) {
                desenharTimeNoChao(g2, arena.getTimeA(), true);
                desenharTimeNoChao(g2, arena.getTimeB(), false);
            }

            g2.setColor(new Color(0,0,0,160));
            g2.fillRect(50, 400, 700, 180);
            g2.setColor(Color.WHITE);
            g2.drawRect(50, 400, 700, 180);

            int y = 430;
            int inicio = Math.max(0, logBatalha.size() - 7);
            for (int i = inicio; i < logBatalha.size(); i++) {
                g2.drawString(logBatalha.get(i), 70, y);
                y += 20;
            }

            g2.setColor(Color.GREEN);
            g2.drawString("ENTER proximo", 520, 570);
        }

        g2.dispose();
    }

    private void desenharTimeNoChao(Graphics2D g2, List<Combatente> time, boolean ladoEsquerdo) {
        if (time == null || time.isEmpty()) return;

        int centroX = ladoEsquerdo ? 260 : 540;

        int espacoX = 70;
        int espacoY = 70;
        int porLinha = 3;

        int vivosIndex = 0;
        int vivosTotal = 0;
        for (Combatente c : time) if (c.estaVivo()) vivosTotal++;

        for (Combatente c : time) {
            if (!c.estaVivo()) continue;

            int col = vivosIndex % porLinha;
            int lin = vivosIndex / porLinha;

            int totalNaLinha = Math.min(porLinha, vivosTotal - lin * porLinha);
            int larguraLinha = (totalNaLinha - 1) * espacoX;

            int x = centroX - (larguraLinha/2) + col * espacoX;
            int ySprite = CHAO_Y - SPRITE_H - (lin * espacoY);

            BufferedImage spr = pegarSprite(c);
            if (spr != null) {
                g2.drawImage(spr, x, ySprite, SPRITE_W, SPRITE_H, null);
            } else {
                g2.setColor(Color.WHITE);
                g2.fillRect(x, ySprite, SPRITE_W, SPRITE_H);
            }

            desenharBarraVida(g2, c, x, ySprite - 12);

            g2.setColor(Color.WHITE);
            g2.drawString(c.getNome(), x, ySprite - 18);

            vivosIndex++;
        }
    }

    private void desenharBarraVida(Graphics2D g2, Combatente c, int x, int y) {
        int w = 80, h = 8;

        g2.setColor(Color.RED);
        g2.fillRect(x, y, w, h);

        double pct = (double) c.getVida() / c.getVidaMaxima();
        int verde = (int) (w * pct);

        g2.setColor(Color.GREEN);
        g2.fillRect(x, y, verde, h);

        g2.setColor(Color.WHITE);
        g2.drawRect(x, y, w, h);
    }

    private BufferedImage pegarSprite(Combatente c) {
        String tipo = c.getClass().getSimpleName().toLowerCase();
        if (tipo.contains("tanque")) return sprTanque;
        if (tipo.contains("mago")) return sprMago;
        if (tipo.contains("atirador")) return sprAtirador;
        return null;
    }

    class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if (gameState == titleState && e.getButton() == MouseEvent.BUTTON1) {
                if (btnIniciarArea.contains(e.getPoint())) {
                    gameState = cutsceneState;
                    dialogoAtualIndex = 0;
                } else if (btnSairArea.contains(e.getPoint())) {
                    System.exit(0);
                }
            }
        }
    }

    class KeyHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (gameState == cutsceneState && e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (dialogoAtualIndex < cutsceneDialogos.length - 1) {
                    dialogoAtualIndex++;
                } else {
                    configurarBatalha();
                }
            }
            else if (gameState == playState && e.getKeyCode() == KeyEvent.VK_ENTER) {
                List<String> logs = arena.executarRodada();

                for (String linha : logs) {
                    String ajustada = linha.replace("TIME A", nomeTimeA).replace("TIME B", nomeTimeB);
                    logBatalha.add(ajustada);

                    if (ajustada.contains("VENCEU") || ajustada.contains("CAMPE")) {
                        JOptionPane.showMessageDialog(null, ajustada);
                        gameState = titleState;
                        break;
                    }
                }
            }
        }
    }
}
