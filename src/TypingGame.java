import javax.swing.*;
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

public class TypingGame extends JFrame implements ActionListener, KeyListener {
    StringBuilder sb = new StringBuilder();
    private JLabel textField;
    private JPanel typePanel;
    private JLabel scoreLabel;
    private String playerText;
    private String answerText;
    private String displayString;
    private int counter;
    private Timer timer;
    private int currentTime;
    private double wpm;

    public TypingGame() {
        startPanel();
    }


    private void startPanel() {
        setContentPane(typePanel);
        setTitle("Typing Game!");
        setSize(1500, 400);
        setLocation(-100, -200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(this);
        setVisible(true);
        playerText = "";
        displayString = "";
        answerText = "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.";
        textField.setText(String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 700, "<html> <font size='5' color=black>" + answerText + "</font> <html>"));
        counter = 0;
        timer = new Timer(1000, null);
        timer.addActionListener(this);
        currentTime = 0;
        scoreLabel.setText("WPM: ?");
        timer.start();
        wpm = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (playerText.length() < answerText.length()) {
            counter = 0;
//            if (getFont().canDisplayUpTo(String.valueOf(e.getKeyChar())) == -1) {
                if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                sb.append(e.getKeyChar());
//        System.out.println(sb.toString());
                String tempText = sb.toString();
                playerText += tempText;
                displayString = "<html>";

                for (int i = 0; i < playerText.length(); i++) {
                    if (String.valueOf(playerText.charAt(i)).equals(String.valueOf(answerText.charAt(i)))) {
//                        System.out.println("works");
                        displayString += "<font size='5' color=green>" + answerText.charAt(i) + "</font>";
                        counter++;
                    } else {
//                        System.out.println("broke");
                        displayString += "<font size='5' color=red>" + answerText.charAt(i) + "</font>";
                    }
                }
                displayString += "<font size='5' color=black>" + answerText.substring(playerText.length()) + "</font>";
                displayString += "<html>";
            } else {
                if (playerText.length() > 0) {
                    playerText = playerText.substring(0, playerText.length() - 1);
                    displayString = "<html>";

                    for (int i = 0; i < playerText.length(); i++) {
                        if (String.valueOf(playerText.charAt(i)).equals(String.valueOf(answerText.charAt(i)))) {
                            System.out.println("works");
                            displayString += "<font size='5' color=green>" + answerText.charAt(i) + "</font>";
                        } else {
                            System.out.println("broke");
                            displayString += "<font size='5' color=red>" + answerText.charAt(i) + "</font>";
                        }
                    }
                    displayString += "<font size='5' color=black>" + answerText.substring(playerText.length()) + "</font>";
                    displayString += "<html>";
                }
            }
//            System.out.println(playerText.length());
            System.out.println(playerText);
            sb.setLength(0);
            textField.setText(String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 700, displayString));
//            textField.setText(displayString.replaceAll(">\\s+<", "><"));
        }

    }


//        for (int i = 0; i < sb.toString().length(); i++) {
//            if (String.valueOf(sb.toString().charAt(i)).equals(String.valueOf(answerText.charAt(i)))) {
//                System.out.println("works");
//            }   else    {
//                System.out.println("broke");
//            }
//        }

//        System.out.println(sb.toString());
//        String tempString = "<html><font size='4' color=red> wrong words </font> <font size='4'color=green> right words</font>";
//        System.out.println(tempString);
//        tempString += "<font size='4' color=blue> poop </font> </html>";
//        System.out.println(tempString);

//        playerText = sb.toString();
//        System.out.println(playerText);
//        textField.setText(sb.toString());
//        textField.setText("<html><font size='4' color=red> wrong words </font> <font size='4'color=green> right words</font></html>");
//        textField.setText(tempString);


    @Override
    public void keyPressed(KeyEvent e) {
//        int keyCode = e.getKeyCode();
//        if (keyCode == KeyEvent.VK_BACK_SPACE) {
//            sb.setLength(sb.length() - 1);
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timerFires();
        if (playerText.length() != answerText.length()) {
            wpm = (((double) counter / 5) / (double) currentTime) * 60;
            scoreLabel.setText("WPM: " + wpm);
        }   else    {
            timer.stop();
        }
    }

    public void timerFires() {
        currentTime++;
    }

}
