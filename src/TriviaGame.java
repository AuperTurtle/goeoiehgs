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
    private JTextField answerBox;
    private JLabel timeDisplay;
    private JLabel playerScore;
    private JButton tempButton;
    private JButton tempButton2;
    private JTextArea tempTitle;
    private int temp;
    private tempParser gameAns;
    private String answer;
    private int counter;
    private String currentAnswer;
    private String prevAnswer;
    private Timer timer;
    private int currentTime;
    private boolean started;

    public TriviaGame(String chosenCategory) {
        this.chosenCategory = chosenCategory;
        gamePanel();
        this.temp = 0;
        counter = 0;
        gameAns = null;
        answerBox.addActionListener(this);
        currentAnswer = "";
        prevAnswer = "";
        timer = new Timer(1000, null);
        timer.addActionListener(this);
        currentTime = 0;
        tempButton.addActionListener(this);
        tempButton2.addActionListener(this);
        started = false;
    }

    private void gamePanel() {
        setContentPane(gamePanel);
        setTitle("pls work");
        setSize(1100, 1100);
        setLocation(200 , 0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(this);
        currentTime = 60;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JTextField) {
            String playerAns = answerBox.getText();
            answerBox.setText("");
            System.out.println(playerAns);
            if (playerAns.length() >= 6 && currentAnswer.toLowerCase().contains(playerAns.toLowerCase())) {
                counter++;
            }
            //int temp = (int) (Math.random() * tempParser.animeList.size());
            currentAnswer = tempParser.animeList.get(temp);
            tempText.setText(currentAnswer + " number in list: " + temp + " previous answer: " + prevAnswer);
            playerScore.setText("Player Score: " + counter);
            try {
                URL imageURL = new URL(tempParser.animePicture.get(temp));
                BufferedImage image = ImageIO.read(imageURL);
                ImageIcon icon = new ImageIcon(image);
                picture.setIcon(icon);
            } catch (IOException ignored) {
            }
            temp++;
            prevAnswer = currentAnswer;
        } else if (source instanceof JButton) {
            if (tempButton.getText().equals("Start")) {
                currentTime = 60;
                started = true;
                timer.start();
            }
            if (tempButton2.getText().equals("Restart")) {

            }
        } else {
            if (started) {
                timerFires();
                timeDisplay.setText("Current Time: " + currentTime);
            }
        }
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

    public void timerFires() {
        currentTime--;
    }
}
