import java.awt.*;

public class GamePanel extends BasePanel {

    private GameEngine gameEngine;
    private Player player;
    private int playerStartingX;
    private int playerStartingY;
    private int score;
    private ScoreLabel scoreLabel;
    private String text;
    private int x;
    private int y;


    public GamePanel(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.text = "Score: ";
        this.x = 20;
        this.y = 20;
        this.setLayout(null);

        scoreLabel = new ScoreLabel(this.text, this.x, this.y);
        this.add(scoreLabel);


        this.score = 0;
        this.setFocusable(true);
        this.requestFocus();

    }

    public void updateScore(int score) {
        scoreLabel.updateScore(score);
    }

    public boolean gameLost() {
        if (this.gameEngine.isPlayerOutOfBounds()) {
            this.gameEngine.stopGame();
            return true;
        }
        return false;
    }

    public int getPlayerStartingX() {
        return playerStartingX;
    }

    public void startGame() {
        this.playerStartingX = (this.getWidth() / 2) - Player.PLAYER_WIDTH / 2;
        this.playerStartingY = (this.getHeight() / 2) + Player.PLAYER_HEIGHT / 2;
        this.player = new Player(this.playerStartingX, this.playerStartingY);
        this.addKeyListener(new PlayerKeyListener(this.player, this));

        this.gameEngine = new GameEngine(this, this.player);
        this.gameEngine.start();
        this.gameEngine.startGame();

    }

    public void setScore(int score) {
        this.score = score;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);


        for (BasePlatform platform : this.gameEngine.getPlatforms()) {
            platform.draw(graphics);
        }
        this.player.draw(graphics);

        if (this.player.getPlayerX() > this.getWidth()) {
            this.player.setPlayerX(this.getX() - this.player.getPlayerWidth());
        } else if (this.player.getPlayerX() + this.player.getPlayerWidth() < this.getX()) {
            this.player.setPlayerX(this.getWidth());
        }
    }
}
