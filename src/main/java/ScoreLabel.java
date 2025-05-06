import javax.swing.*;
import java.awt.*;


public class ScoreLabel extends JLabel {
    private static final Font DEFAULT_FONT = new Font("Comic Sans MS", Font.BOLD, 24);
    private static final Color TEXT_COLOR = new Color(67, 18, 18, 255);
    private static final int WIDTH_BOUNDS = 200;
    private static final int HEIGHT_BOUNDS = 60;
    private static final int X_POSITION = 0;
    private static final int Y_POSITION = 0;

    private Image backgroundImage;

    public ScoreLabel(String text, int x, int yPOSITION) {
        super(text);

        backgroundImage = new ImageIcon(getClass().getResource("images/wooden_tile.png")).getImage(); // Make sure the path is correct

        this.setFont(DEFAULT_FONT);
        this.setForeground(TEXT_COLOR);
        this.setBounds(x, yPOSITION, WIDTH_BOUNDS, HEIGHT_BOUNDS);
        this.setOpaque(false);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, X_POSITION, Y_POSITION, getWidth(), getHeight(), this);
        }
        super.paintComponent(g);
    }

    public void updateScore(int score) {
        this.setText("Score: " + score);
    }

    public void setPosition(int x, int y) {
        this.setBounds(x, y, getWidth(), getHeight());
    }
}
