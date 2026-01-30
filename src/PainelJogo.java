
import Elementos_do_Jogo.Arena;
import Elementos_do_Jogo.Atirador;
import Elementos_do_Jogo.Combatente;
import Elementos_do_Jogo.Mago;
import Elementos_do_Jogo.Tanque;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PainelJogo extends JPanel implements Runnable {
   final int originalTileSize = 16;
   final int scale = 3;
   final int tileSize = 48;
   Thread gameThread;
   public int gameState;
   public final int titleState = 0;
   public final int cutsceneState = 2;
   public final int playState = 1;
   Rectangle btnIniciarArea;
   Rectangle btnSairArea;
   BufferedImage imgFundo;
   BufferedImage imgTitulo;
   BufferedImage imgIniciar;
   BufferedImage imgSair;
   BufferedImage imgCutsceneFundo;
   int titleFinalW;
   int titleFinalH;
   String[] cutsceneDialogos = new String[]{"Em um reino místico, a paz é mantida", "através de um antigo ritual...", "conhecido como o Grande Torneio.", "A cada geração, as duas maiores", "facções do mundo se enfrentam.", "Você é o Senhor da Guerra,", "responsável por comandar a batalha."};
   int dialogoAtualIndex = 0;
   Font fonteDialogo = new Font("Monospaced", 1, 16);
   Arena arena;
   List<String> logBatalha = new ArrayList();

   public PainelJogo() {
      this.setPreferredSize(new Dimension(800, 600));
      this.setBackground(Color.BLACK);
      this.setDoubleBuffered(true);
      this.setFocusable(true);
      this.requestFocusInWindow();

      try {
         this.imgTitulo = ImageIO.read(new File("Imagens/titulo.png"));
         this.imgIniciar = ImageIO.read(new File("Imagens/iniciar.png"));
         this.imgSair = ImageIO.read(new File("Imagens/sair.png"));

         try {
            this.imgFundo = ImageIO.read(new File("Imagens/fundo.png"));
         } catch (Exception var5) {
         }

         try {
            this.imgCutsceneFundo = ImageIO.read(new File("Imagens/cutscene.png"));
         } catch (Exception var4) {
         }

         try {
            BufferedImage var1 = ImageIO.read(new File("Imagens/cursor.png"));
            Cursor var2 = Toolkit.getDefaultToolkit().createCustomCursor(var1, new Point(0, 0), "CursorRPG");
            this.setCursor(var2);
         } catch (Exception var3) {
         }
      } catch (IOException var6) {
         System.out.println("Aviso: Imagens não encontradas.");
      }

      if (this.imgTitulo != null) {
         this.titleFinalW = 380;
         double var7 = (double)this.imgTitulo.getHeight() / (double)this.imgTitulo.getWidth();
         this.titleFinalH = (int)((double)this.titleFinalW * var7);
      }

      short var8 = 400;
      if (this.imgIniciar != null) {
         this.btnIniciarArea = new Rectangle(200, var8, 180, (int)(180.0 * ((double)this.imgIniciar.getHeight() / (double)this.imgIniciar.getWidth())));
      } else {
         this.btnIniciarArea = new Rectangle(200, var8, 180, 60);
      }

      if (this.imgSair != null) {
         this.btnSairArea = new Rectangle(420, var8, 180, (int)(180.0 * ((double)this.imgSair.getHeight() / (double)this.imgSair.getWidth())));
      } else {
         this.btnSairArea = new Rectangle(420, var8, 180, 60);
      }

      this.addMouseListener(new MouseHandler());
      this.addKeyListener(new KeyHandler());
      this.gameState = 0;
      this.startGameThread();
   }

   public void startGameThread() {
      this.gameThread = new Thread(this);
      this.gameThread.start();
   }

   public void configurarBatalha() {
      this.arena = new Arena();
      String var1 = JOptionPane.showInputDialog(this, "JOGADOR 1 (Aliança)\nDigite seu nome:");
      if (var1 == null || var1.trim().isEmpty()) {
         var1 = "Jogador 1";
      }

      String[] var2 = new String[]{"Tanque (Azul)", "Mago (Roxo)", "Atirador (Verde)"};
      int var3 = JOptionPane.showOptionDialog(this, "Escolha seu Campeão:", "Seleção P1", -1, 1, (Icon)null, var2, var2[0]);
      this.criarPersonagem(var3, var1, 'A');
      String var4 = JOptionPane.showInputDialog(this, "JOGADOR 2 (Horda)\nDigite seu nome:");
      if (var4 == null || var4.trim().isEmpty()) {
         var4 = "Jogador 2";
      }

      int var5 = JOptionPane.showOptionDialog(this, "Escolha seu Campeão:", "Seleção P2", -1, 1, (Icon)null, var2, var2[0]);
      this.criarPersonagem(var5, var4, 'B');
      this.logBatalha.clear();
      this.logBatalha.add("DUELO 1x1 INICIADO!");
      this.logBatalha.add(var1 + " VS " + var4);
      this.gameState = 1;
   }

   private void criarPersonagem(int var1, String var2, char var3) {
      if (var1 == 0) {
         this.arena.adicionarCombatente(new Tanque(var2), var3);
      } else if (var1 == 1) {
         this.arena.adicionarCombatente(new Mago(var2), var3);
      } else {
         this.arena.adicionarCombatente(new Atirador(var2), var3);
      }

   }

   public void run() {
      while(this.gameThread != null) {
         this.repaint();

         try {
            Thread.sleep(16L);
         } catch (InterruptedException var2) {
            var2.printStackTrace();
         }
      }

   }

   public void paintComponent(Graphics var1) {
      super.paintComponent(var1);
      Graphics2D var2 = (Graphics2D)var1;
      if (this.gameState == 0) {
         if (this.imgFundo != null) {
            var2.drawImage(this.imgFundo, 0, 0, 800, 600, (ImageObserver)null);
         } else {
            var2.setColor(new Color(70, 120, 180));
            var2.fillRect(0, 0, 800, 600);
         }

         if (this.imgTitulo != null) {
            var2.drawImage(this.imgTitulo, (800 - this.titleFinalW) / 2, 100, this.titleFinalW, this.titleFinalH, (ImageObserver)null);
         }

         if (this.imgIniciar != null) {
            var2.drawImage(this.imgIniciar, this.btnIniciarArea.x, this.btnIniciarArea.y, this.btnIniciarArea.width, this.btnIniciarArea.height, (ImageObserver)null);
         } else {
            var2.setColor(Color.GREEN);
            var2.fillRect(this.btnIniciarArea.x, this.btnIniciarArea.y, this.btnIniciarArea.width, this.btnIniciarArea.height);
         }

         if (this.imgSair != null) {
            var2.drawImage(this.imgSair, this.btnSairArea.x, this.btnSairArea.y, this.btnSairArea.width, this.btnSairArea.height, (ImageObserver)null);
         } else {
            var2.setColor(Color.RED);
            var2.fillRect(this.btnSairArea.x, this.btnSairArea.y, this.btnSairArea.width, this.btnSairArea.height);
         }
      } else {
         int var3;
         int var4;
         int var5;
         if (this.gameState == 2) {
            if (this.imgCutsceneFundo != null) {
               var2.drawImage(this.imgCutsceneFundo, 0, 0, 800, 600, (ImageObserver)null);
            } else {
               var2.setColor(Color.BLACK);
               var2.fillRect(0, 0, 800, 600);
            }

            var3 = 600;
            var4 = 130;
            var5 = 100;
            short var6 = 440;
            var2.setColor(Color.BLACK);
            var2.fillRect(var5, var6, var3, var4);
            var2.setColor(Color.WHITE);
            var2.setStroke(new BasicStroke(5.0F));
            var2.drawRect(var5, var6, var3, var4);
            var2.setFont(this.fonteDialogo);
            if (this.dialogoAtualIndex < this.cutsceneDialogos.length) {
               var2.drawString(this.cutsceneDialogos[this.dialogoAtualIndex], var5 + 30, var6 + 60);
            }

            var2.setFont(new Font("Monospaced", 2, 12));
            var2.drawString("[ESPAÇO] >>", var5 + var3 - 100, var6 + var4 - 15);
         } else if (this.gameState == 1) {
            var2.setColor(new Color(60, 60, 60));
            var2.fillRect(0, 0, 800, 600);
            var2.setColor(Color.WHITE);
            var2.drawLine(400, 50, 400, 550);
            var2.setFont(new Font("Monospaced", 1, 14));
            var2.setColor(Color.CYAN);
            var2.drawString("JOGADOR 1", 100, 40);
            var2.setColor(Color.RED);
            var2.drawString("JOGADOR 2", 550, 40);
            if (this.arena != null) {
               this.desenharTime(var2, this.arena.getTimeA(), 100);
               this.desenharTime(var2, this.arena.getTimeB(), 500);
            }

            var2.setColor(new Color(0, 0, 0, 200));
            var2.fillRect(50, 400, 700, 180);
            var2.setColor(Color.WHITE);
            var2.drawRect(50, 400, 700, 180);
            var3 = 430;
            var4 = Math.max(0, this.logBatalha.size() - 7);

            for(var5 = var4; var5 < this.logBatalha.size(); ++var5) {
               var2.drawString((String)this.logBatalha.get(var5), 70, var3);
               var3 += 20;
            }

            var2.setColor(Color.GREEN);
            var2.drawString("[ENTER] PRÓXIMO TURNO", 550, 570);
         }
      }

      var2.dispose();
   }

   private void desenharTime(Graphics2D var1, List<Combatente> var2, int var3) {
      int var4 = 100;

      for(Iterator var5 = var2.iterator(); var5.hasNext(); var4 += 90) {
         Combatente var6 = (Combatente)var5.next();
         if (var6.estaVivo()) {
            var1.setColor(Color.RED);
            var1.fillRect(var3, var4 - 15, 50, 8);
            var1.setColor(Color.GREEN);
            double var7 = (double)var6.getVida() / (double)var6.getVidaMaxima();
            int var9 = (int)(50.0 * var7);
            var1.fillRect(var3, var4 - 15, var9, 8);
            var1.setColor(Color.WHITE);
            var1.drawRect(var3, var4 - 15, 50, 8);
         }

         if (!var6.estaVivo()) {
            var1.setColor(Color.DARK_GRAY);
         } else {
            var1.setColor(var6.getCor());
         }

         var1.fillRect(var3, var4, 50, 50);
         var1.setColor(Color.WHITE);
         var1.drawRect(var3, var4, 50, 50);
         var1.setFont(new Font("Arial", 0, 12));
         if (!var6.estaVivo()) {
            var1.setColor(Color.RED);
            var1.drawString("X MORTO", var3 + 60, var4 + 30);
         } else {
            var1.setColor(Color.WHITE);
            var1.drawString(var6.getNome(), var3 + 60, var4 + 20);
            var1.setColor(Color.LIGHT_GRAY);
            var1.drawString("PV: " + var6.getVida(), var3 + 60, var4 + 40);
         }
      }

   }

   class MouseHandler extends MouseAdapter {
      MouseHandler() {
         Objects.requireNonNull(PainelJogo.this);
         super();
      }

      public void mousePressed(MouseEvent var1) {
         if (PainelJogo.this.gameState == 0 && var1.getButton() == 1) {
            if (PainelJogo.this.btnIniciarArea.contains(var1.getPoint())) {
               PainelJogo.this.gameState = 2;
               PainelJogo.this.dialogoAtualIndex = 0;
            } else if (PainelJogo.this.btnSairArea.contains(var1.getPoint())) {
               System.exit(0);
            }
         }

      }
   }

   class KeyHandler extends KeyAdapter {
      KeyHandler() {
         Objects.requireNonNull(PainelJogo.this);
         super();
      }

      public void keyPressed(KeyEvent var1) {
         if (PainelJogo.this.gameState == 2) {
            if (var1.getKeyCode() == 32) {
               ++PainelJogo.this.dialogoAtualIndex;
               if (PainelJogo.this.dialogoAtualIndex >= PainelJogo.this.cutsceneDialogos.length) {
                  PainelJogo.this.configurarBatalha();
               }
            }
         } else if (PainelJogo.this.gameState == 1 && var1.getKeyCode() == 10) {
            List var2 = PainelJogo.this.arena.executarRodada();
            PainelJogo.this.logBatalha.addAll(var2);
            PainelJogo.this.repaint();
            Iterator var3 = var2.iterator();

            while(var3.hasNext()) {
               String var4 = (String)var3.next();
               if (var4.contains("VENCEU")) {
                  JOptionPane.showMessageDialog((Component)null, var4);
                  PainelJogo.this.gameState = 0;
               }
            }
         }

      }
   }
}
