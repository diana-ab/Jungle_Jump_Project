package ui;

import assets.GameImages;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GameEndPanel extends BasePanel {
    private JButton backToMenu;
    private JPanel endButtonPanel;
    private GameImages gameImages;


    public GameEndPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.gameImages = new GameImages();
        this.setBackground(this.gameImages.getGameOverImage());
        this.endButtonPanel = createButtonPanel(this);
        this.backToMenu = setBackToMenuButton(this);
        styleImageButton(this.backToMenu);
        this.endButtonPanel.add(this.backToMenu);
    }

    public void setBackToMenuButtonAction(ActionListener backToMenu) {
        this.backToMenu.addActionListener(backToMenu);
    }
}
