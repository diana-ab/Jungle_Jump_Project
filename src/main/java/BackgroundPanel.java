import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    public static final int HORIZONTAL_GAP = 20;
    public static final int VERTICAL_GAP = 20;
    public static final int BACK_BUTTON_WIDTH = 180;
    public static final int BACK_BUTTON_HEIGHT = 80;

    private Image background;


    public BackgroundPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.background =
                new ImageIcon(getClass().getResource("images/background.png")).getImage();

    }

    public JButton setBackToMenuButton(JPanel panel) {
        Image backButton =
                new ImageIcon(getClass().getResource("/images/back_to_menu.png")).getImage();
        JButton backToMenuButton = new JButton(setButtonImage(backButton, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT));
        styleImageButton(backToMenuButton);
        return backToMenuButton;
    }

    public JPanel createButtonPanel(JPanel panel) {
        panel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP));
        buttonPanel.setOpaque(false);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return buttonPanel;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public ImageIcon setButtonImage(Image buttonImage, int buttonWidth, int buttonHeight) {
        Image image = buttonImage;
        image = image.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(image);
        return scaledImage;
    }

    public void styleImageButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }


    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (background != null) {
            graphics.drawImage(this.background, this.getX(), this.getY(),
                    this.getWidth(), this.getHeight(), this);
        }
    }
}
