package graficot2;

import javax.swing.JFrame;

public class Janela extends JFrame{
    Janela(){
        
   this.add(new Desenho());
        
        this.setSize(80,80);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    }
}


