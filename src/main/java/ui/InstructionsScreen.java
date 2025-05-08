package ui;

import assets.GameImages;

import javax.swing.*;
import java.awt.event.ActionListener;

public class InstructionsScreen extends BasePanel {

    private JButton returnButton;
    private JPanel secondaryPanel;
    private GameImages gameImages;

    public InstructionsScreen(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.gameImages = new GameImages();
        this.setBackground(this.gameImages.getInstructionsScreenImage());
        this.secondaryPanel = createButtonPanel(this);
        this.returnButton = setBackToMenuButton(this);
        styleImageButton(this.returnButton);
        this.secondaryPanel.add(this.returnButton);
    }

    public void setReturnButton(ActionListener actionListener) {
        this.returnButton.addActionListener(actionListener);
    }
}
