import javax.swing.*;
import java.awt.event.ActionListener;

public class InstructionsScreen extends BackgroundPanel {

    private JButton returnButton;
    private JPanel secondaryPanel;

    public InstructionsScreen(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setBackground(new ImageIcon(getClass().getResource("images/jungle_jump_instructions.png")).getImage());

        this.secondaryPanel = createButtonPanel(this);
        this.returnButton = setBackToMenuButton(this);
        styleImageButton(this.returnButton);
        this.secondaryPanel.add(this.returnButton);
    }

    public void setReturnButton(ActionListener actionListener) {
        this.returnButton.addActionListener(actionListener);
    }
}
