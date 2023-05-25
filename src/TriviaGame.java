import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class TriviaGame extends JFrame implements ActionListener, KeyListener {
    private String chosenCategory;
    private JPanel gamePanel;
    private JLabel picture;
    private JLabel tempText;
    private JTextArea tempTitle;
    private int temp;

    public TriviaGame(String chosenCategory) {
        this.chosenCategory = chosenCategory;
        gamePanel();
        this.temp = 0;
    }

    private void gamePanel() {
        setContentPane(gamePanel);
        setTitle("pls work");
        setSize(1000, 1000);
        setLocation(250 , 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 192) {
//          int temp = (int) (Math.random() * tempParser.animeList.size());
            tempText.setText(tempParser.animeList.get(temp) + " number in list: " + temp);
            try {
                URL imageURL = new URL(tempParser.animePicture.get(temp));
                BufferedImage image = ImageIO.read(imageURL);
                ImageIcon icon = new ImageIcon(image);
                picture.setIcon(icon);
            } catch (IOException ignored) { }
            temp++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
