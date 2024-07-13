package graficot3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

public class Desenho extends JComponent implements Runnable{
    
    private int forma;
    private Color cor1;
    private Color cor2;
    private Color cor3; // Cor do terceiro desenho
    private int px = 5, py = 5;
    private int px2 = 5, py2 = 150; // Coordenadas do segundo desenho
    private int px3 = 5, py3 = 300; // Coordenadas do terceiro desenho
    private int direcao = 1;
    private int direcao2 = 1; // Direção do segundo desenho
    private int direcao3 = 1; // Direção do terceiro desenho

    public Desenho(int forma, Color cor1, Color cor2, Color cor3) {
        this.forma = forma;
        this.cor1 = cor1;
        this.cor2 = cor2;
        this.cor3 = cor3;
        new Thread(this).start();
    }

    public void setForma(int forma) {
        this.forma = forma;
    }

    public void setCor1(Color cor) {
        this.cor1 = cor;
    }
    
    public void setCor2(Color cor) {
        this.cor2 = cor;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public void setPy(int py) {
        this.py = py;
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(cor1);
        
        if (forma == 1) {
            g.fillRect(px, py, 100, 100);
        } else if (forma == 2) {
            g.fillOval(px, py, 100, 100);
        }
        
        g.setColor(cor2);
        if (forma == 1) {
            g.fillRect(px2, py2, 100, 100); // Desenho adicional
        } else if (forma == 2) {
            g.fillOval(px2, py2, 100, 100); // Desenho adicional
        }
        
        g.setColor(cor3);
        if (forma == 1) {
            g.fillRect(px3, py3, 100, 100); // Terceiro desenho
        } else if (forma == 2) {
            g.fillOval(px3, py3, 100, 100); // Terceiro desenho
        }
    }
    
    private void movimenta() {
        if(px > this.getWidth() - 25){
            direcao = 2;
        }
        if(px < 6){
            direcao = 1;
        }
        
        if(direcao == 1){
            px += 1;
        }else if(direcao == 2){
            px -= 1;
        }
        
        // Movimento do segundo desenho
        if(px2 > this.getWidth() - 25){
            direcao2 = 2;
        }
        if(px2 < 6){
            direcao2 = 1;
        }
        
        if(direcao2 == 1){
            px2 += 1;
        }else if(direcao2 == 2){
            px2 -= 1;
        }
        
        // Movimento do terceiro desenho
        if(px3 > this.getWidth() - 25){
            direcao3 = 2;
        }
        if(px3 < 6){
            direcao3 = 1;
        }
        
        if(direcao3 == 1){
            px3 += 1;
        }else if(direcao3 == 2){
            px3 -= 1;
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10); // Ajustei o tempo de espera para que a animação seja mais visível
                movimenta();
                repaint();
            } catch (InterruptedException ex) {
                Logger.getLogger(Desenho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
