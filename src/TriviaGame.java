import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class TriviaGame extends JFrame implements ActionListener, KeyListener {
    private String chosenCategory;
    private JPanel gamePanel;
    private JLabel picture;
    private JLabel tempText;
    private JTextField answerBox;
    private JLabel timeDisplay;
    private JLabel playerScore;
    private JButton startButton;
    private JButton restartButton;
    private JLabel correctLabel;
    private JLabel randomText2;
    private JLabel randomText;
    private JTextArea tempTitle;
    private int temp;
    private int counter;
    private String currentAnswer;
    private String prevAnswer;
    private Timer timer;
    private int currentTime;
    private boolean started;
    private boolean correct;
    private ArrayList<String> repeat;

    public TriviaGame(String chosenCategory) {
        this.repeat = new ArrayList<String>();
        this.chosenCategory = chosenCategory;
        gamePanel();
        this.temp = 0;
        counter = 0;
        answerBox.addActionListener(this);
        currentAnswer = "";
        prevAnswer = "";
        timer = new Timer(1000, null);
        timer.addActionListener(this);
        currentTime = 0;
        startButton.addActionListener(this);
        restartButton.addActionListener(this);
        started = false;
        answerBox.setEnabled(false);
        correctLabel.setText("");
        correct = false;
        restartButton.setEnabled(false);
    }

    private void gamePanel() {
        setContentPane(gamePanel);
        setTitle("Fun Portal!");
        setSize(1600, 1600);
        setLocation(200 , 0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(this);
        currentTime = 61;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        correct = false;
        Object source = e.getSource();
        if (source instanceof JTextField) {
            String playerAns = answerBox.getText();
            answerBox.setText("");
//            System.out.println(playerAns);
            if (playerAns.length() >= 5 && currentAnswer.toLowerCase().contains(playerAns.toLowerCase())) {
                counter++;
                correct = true;
            }
            int temp = repeatCheck();
            repeatUpdate(temp);
            currentAnswer = tempParser.animeList.get(temp);
//            tempText.setText(currentAnswer + " number in list: " + temp + " previous answer: " + prevAnswer);
            tempText.setText("Previous answer: " + prevAnswer);
            playerScore.setText("Player Score: " + counter);
            if (correct) {
                correctLabel.setForeground(Color.GREEN);
                correctLabel.setText("[CORRECT]");
            }   else    {
                correctLabel.setForeground(Color.RED);
                correctLabel.setText("[INCORRECT]");
            }
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
            if (source == restartButton) {
                randomText.setVisible(true);
                randomText2.setVisible(true);
                repeat.clear();
                started = false;
                temp = 0;
                currentAnswer = "";
                prevAnswer = "";
                currentTime = 0;
                answerBox.setEnabled(false);
                startButton.setEnabled(true);
                timeDisplay.setText("");
                playerScore.setText("");
                tempText.setText("");
                timeDisplay.setForeground(Color.BLACK);
                playerScore.setForeground(Color.BLACK);
                correctLabel.setText("");
                correct = false;
                restartButton.setEnabled(false);
                ImageIcon icon = new ImageIcon("src/Question_mark_(black).svg.png");
                picture.setIcon(icon);
                counter = 0;
            }
            if (source == startButton) {
                randomText.setVisible(false);
                randomText2.setVisible(false);
                repeat.clear();
                currentTime = 61;
                started = true;
                timer.start();
                answerBox.setEnabled(true);
                startButton.setEnabled(false);
                correctLabel.setText("");
                correct = false;

                int temp = repeatCheck();
                repeatUpdate(temp);
                currentAnswer = tempParser.animeList.get(temp);
//            tempText.setText(currentAnswer + " number in list: " + temp + " previous answer: " + prevAnswer);
                tempText.setText("Previous answer: " + prevAnswer);
                playerScore.setText("Player Score: " + counter);
                if (correct) {
                    correctLabel.setForeground(Color.GREEN);
                    correctLabel.setText("[CORRECT]");
                }   else    {
                    correctLabel.setForeground(Color.RED);
                    correctLabel.setText("[INCORRECT]");
                }
                try {
                    URL imageURL = new URL(tempParser.animePicture.get(temp));
                    BufferedImage image = ImageIO.read(imageURL);
                    ImageIcon icon = new ImageIcon(image);
                    picture.setIcon(icon);
                } catch (IOException ignored) {
                }
                temp++;
                prevAnswer = currentAnswer;

                answerBox.setText("");
                tempText.setText("");
                correctLabel.setText("");
                restartButton.setEnabled(true);
            }
        } else {
            if (started) {
                timerFires();
                if (currentTime > 0) {
                    timeDisplay.setText("Current Time: " + currentTime);
                }
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
        if (currentTime > 0) {
            currentTime--;
        }   else    {
            timer.stop();
            playerScore.setForeground(Color.BLUE);
            playerScore.setText("Final Score: " + counter);
            tempText.setText("");
            answerBox.setEnabled(false);
            timeDisplay.setForeground(Color.RED);
            timeDisplay.setText("TIMES UP!");
            correctLabel.setText("");
            correct = false;
            ImageIcon icon = new ImageIcon("src/Question_mark_(black).svg.png");
            picture.setIcon(icon);
        }
    }

    private int repeatCheck() {
        int temp = (int) (Math.random() * tempParser.animeList.size());
        while (repeat.contains(tempParser.animeList.get(temp))) {
            temp = (int) (Math.random() * tempParser.animeList.size());
        }
        return temp;
    }

    private void repeatUpdate(int num) {
        repeat.add(tempParser.animeList.get(num));
    }
}
