package controlesemaforo;

import javax.swing.*;
import java.awt.*;

public class ControleSemaforo {
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

        frame.setSize(620, 665);
        frame.setResizable(false);
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