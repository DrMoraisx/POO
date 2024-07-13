package controlesemaforo;

import java.awt.*;

public class PlacaPare {
    private int x;
    private int y;

    public PlacaPare(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        // Desenhar placa de pare
        g.setColor(Color.RED);
        g.fillOval(x, y, 40, 35);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("PARE", x + 5, y + 18);
    }
}

