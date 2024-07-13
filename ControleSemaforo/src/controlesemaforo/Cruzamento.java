package controlesemaforo;

import javax.swing.*;
import java.awt.*;

public class Cruzamento extends JPanel {
    private Semaforo semaforoHorizontal;
    private Semaforo semaforoVertical;
    private PlacaPare placaPareHorizontal;
    private PlacaPare placaPareVertical;
    private boolean modoDigital;
    private Timer timer;

    public Cruzamento() {
        this.semaforoHorizontal = new Semaforo();
        this.semaforoVertical = new Semaforo();
        this.placaPareHorizontal = new PlacaPare(160, 160); // Posição da placa de pare na rua horizontal
        this.placaPareVertical = new PlacaPare(400, 400);   // Posição da placa de pare na rua vertical
        this.modoDigital = false;
        iniciarTimer();
    }

    private void iniciarTimer() {
        timer = new Timer(1000, e -> {
            atualizarTempoSemaforos();
            repaint();
        });
        timer.start();
    }

    private void atualizarTempoSemaforos() {
        if (semaforoHorizontal.getTempoRestante() > 0) {
            semaforoHorizontal.setTempoRestante(semaforoHorizontal.getTempoRestante() - 1);
        }
        if (semaforoVertical.getTempoRestante() > 0) {
            semaforoVertical.setTempoRestante(semaforoVertical.getTempoRestante() - 1);
        }
    }

    public void controlarSemaforos() throws InterruptedException {
        while (true) {
            // Semáforo horizontal verde e semáforo vertical vermelho
            semaforoHorizontal.mudarParaVerde(5);
            semaforoVertical.mudarParaVermelho(7);
            repaint();
            Thread.sleep(5000); // 5 segundos

            // Semáforo horizontal amarelo e semáforo vertical vermelho
            semaforoHorizontal.mudarParaAmarelo(2);
            repaint();
            Thread.sleep(2000); // 2 segundos

            // Semáforo horizontal vermelho e semáforo vertical verde
            semaforoHorizontal.mudarParaVermelho(7);
            semaforoVertical.mudarParaVerde(5);
            repaint();
            Thread.sleep(5000); // 5 segundos

            // Semáforo horizontal vermelho e semáforo vertical amarelo
            semaforoVertical.mudarParaAmarelo(2);
            repaint();
            Thread.sleep(2000); // 2 segundos
        }
    }

    public void alternarModo() {
        modoDigital = !modoDigital;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCruzamento(g);
        if (modoDigital) {
            drawSemaforoDigital(g, semaforoHorizontal, 420, 40); // Semáforo horizontal
            drawSemaforoDigital(g, semaforoVertical, 100, 300);   // Semáforo vertical
        } else {
            drawSemaforoAnalogico(g, semaforoHorizontal, 420, 40); // Semáforo horizontal
            drawSemaforoAnalogico(g, semaforoVertical, 100, 300);   // Semáforo vertical
        }

        // Desenhar placa de pare
        placaPareHorizontal.draw(g);
        placaPareVertical.draw(g);
    }

    private void drawCruzamento(Graphics g) {
        // Desenhar calçadas
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 600, 700); // Calçada superior
        g.fillRect(0, 500, 600, 100); // Calçada inferior
        g.fillRect(0, 100, 100, 400); // Calçada esquerda
        g.fillRect(500, 100, 100, 400); // Calçada direita
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(400, 400, 200, 10); // Calçada superior
        g.fillRect(0, 190, 200, 10); // Calçada inferior
        g.fillRect(0, 400, 200, 10); // Calçada esquerda
        g.fillRect(400, 190, 200, 10); // Calçada direita
        g.fillRect(400,0, 10, 600);
        g.fillRect(190, 0, 10, 600);

        // Desenhar ruas
        g.setColor(Color.GRAY);
        g.fillRect(200, 0, 200, 600); // Rua vertical
        g.fillRect(0, 200, 600, 200); // Rua horizontal

        // Desenhar faixa de pedestres na rua horizontal
        g.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            g.fillRect(400, 205 + i * 20, 30, 10);
        }

        // Desenhar faixa de pedestres na rua vertical
        for (int i = 0; i < 10; i++) {
            g.fillRect(206 + i * 20, 167, 10, 30);
        }

        // Desenhar faixa de pedestres na rua horizontal oposta
        for (int i = 0; i < 10; i++){
            g.fillRect(167, 205 + i * 20, 30, 10);
        }

        // Desenhar faixa de pedestres na rua vertical oposta
        for (int i = 0; i < 10; i++) {
            g.fillRect(206 + i * 20, 400, 10, 30);
        }

        // Desenhar faixa no meio da rua horizontal
        g.setColor(Color.YELLOW);
        for (int i = 0; i < 4; i++) {
            g.fillRect(i * 40, 295, 30, 10); // Linha tracejada no meio da rua horizontal
        }

        // Desenhar faixa no meio da rua vertical
        for (int i = 0; i < 4; i++) {
            g.fillRect(295, i * 40, 10, 30); // Linha tracejada no meio da rua vertical
        }
        
        // Desenhar faixa na parte inferior da rua vertical
        for (int i = 0; i < 4; i++) {
            g.fillRect(295, 450 + i * 40, 10, 30); // Linha tracejada na parte inferior da rua vertical
        }
        
        // Desenhar faixa na parte direita da rua horizontal
        for (int i = 0; i < 4; i++) {
            g.fillRect(450 + i * 40, 295, 30, 10); // Linha tracejada na parte direita da rua horizontal
        }
    }

    private void drawSemaforoAnalogico(Graphics g, Semaforo semaforo, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(x, y, 60, 180, 20, 20);
        
        g2d.setColor(semaforo.getCor() == Color.RED ? Color.RED : Color.DARK_GRAY);
        g2d.fillOval(x + 10, y + 10, 40, 40);
        g2d.setColor(semaforo.getCor() == Color.YELLOW ? Color.YELLOW : Color.DARK_GRAY);
        g2d.fillOval(x + 10, y + 70, 40, 40);
        g2d.setColor(semaforo.getCor() == Color.GREEN ? Color.GREEN : Color.DARK_GRAY);
        g2d.fillOval(x + 10, y + 130, 40, 40);
    }

    private void drawSemaforoDigital(Graphics g, Semaforo semaforo, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(x, y, 100, 180, 20, 20);
        
        g2d.setColor(semaforo.getCor());
        g2d.fillOval(x + 30, y + 20, 40, 40); // Indicação da cor do semáforo

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        String tempoRestante = String.valueOf(semaforo.getTempoRestante());
        g2d.drawString(tempoRestante, x + 50 - g2d.getFontMetrics().stringWidth(tempoRestante) / 2, y + 140); // Exibe o tempo restante
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Controle de Semáforos");
        Cruzamento cruzamento = new Cruzamento();
        frame.add(cruzamento);

        JButton botaoAlternar = new JButton("Alternar Modo");
        botaoAlternar.addActionListener(e -> cruzamento.alternarModo());
        botaoAlternar.setFont(new Font("Arial", Font.BOLD, 16));
        botaoAlternar.setBackground(new Color(50, 150, 250));
        botaoAlternar.setForeground(Color.WHITE);
        frame.add(botaoAlternar, BorderLayout.SOUTH);

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(() -> {
            try {
                cruzamento.controlarSemaforos();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
