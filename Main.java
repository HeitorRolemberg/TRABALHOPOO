import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("O Grande Torneio");

        // configura o ícone da janela para o da espada
        ImageIcon icon = new ImageIcon("Imagens/espada.png"); 
        window.setIconImage(icon.getImage());

        // cria e adiciona o painel do jogo (localizado em PainelJogo.java) a janela
        PainelJogo gamePanel = new PainelJogo(); 
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
