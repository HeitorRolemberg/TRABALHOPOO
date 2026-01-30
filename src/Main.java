import javax.swing.JFrame;
import Interface.PainelJogo;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("O Grande Torneio");

        PainelJogo painel = new PainelJogo();
        window.add(painel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
