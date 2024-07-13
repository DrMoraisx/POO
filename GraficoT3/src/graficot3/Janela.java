package graficot3;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Janela extends JFrame implements KeyListener{

    private Desenho desenho;
    
    Janela() {
        desenho = new Desenho(2, Color.green, Color.orange, Color.green);
        
        this.add(desenho);
        
        this.addKeyListener(this);
        
        this.setTitle("Desenho");
        this.setSize(1000, 600);  // Aumentei o tamanho da janela para acomodar os novos desenhos
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            desenho.setForma(2);
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            desenho.setForma(1);
        }
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            desenho.setCor1(Color.blue);
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            desenho.setCor1(Color.red);
        }
        
        if(e.getKeyCode() == KeyEvent.VK_W){
            desenho.setCor2(Color.yellow);
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            desenho.setCor2(Color.pink);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}



