package ui;

import assets.GameImages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends BasePanel {
    public static final int START_BUTTON_WIDTH = 160;
    public static final int START_BUTTON_HEIGHT = 100;
    public static final int INSTRUCTIONS_BUTTON_WIDTH = 180;
    public static final int INSTRUCTIONS_BUTTON_HEIGHT = 80;


    private JButton start;
    private JButton instructions;
    private JPanel buttonPanel;
    private Image startGameImage;
    private Image instructionsImage;
    private GameImages gameImages;

    public Menu(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.gameImages = new GameImages();
        this.setBackground(this.gameImages.getMenuImage());
        this.startGameImage = this.gameImages.getStartButtonImage();
        this.instructionsImage = this.gameImages.getInstructionsButtonImage();

        this.buttonPanel = createButtonPanel(this);

        this.start = new JButton((setButtonImage(this.startGameImage, START_BUTTON_WIDTH, START_BUTTON_HEIGHT)));
        styleImageButton(this.start);
        this.buttonPanel.add(this.start);

        this.instructions = new JButton(setButtonImage(this.instructionsImage, INSTRUCTIONS_BUTTON_WIDTH, INSTRUCTIONS_BUTTON_HEIGHT));
        styleImageButton(this.instructions);
        this.buttonPanel.add(this.instructions);
    }


    public void setStartButtonAction(ActionListener actionListener) {
        this.start.addActionListener(actionListener);
    }


    public void setInstructionsButtonAction(ActionListener actionListener) {
        this.instructions.addActionListener(actionListener);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }
}
