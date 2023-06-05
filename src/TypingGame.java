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
    private String playerText;
    private String answerText;
    private int index;

    public TypingGame() {
        startPanel();
    }


    private void startPanel() {
        setContentPane(typePanel);
        setTitle("Typing Game!");
        setSize(1500, 400);
        setLocation(0, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(this);
        setVisible(true);
        playerText = "";
        answerText = "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.";
        textField.setText(answerText);
        index = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
            if (getFont().canDisplayUpTo(String.valueOf(e.getKeyChar())) == -1) {
            sb.append(e.getKeyChar());
//        System.out.println(sb.toString());
            String tempText = sb.toString();
            playerText += tempText;

                for (int i = 0; i < playerText.length(); i++) {
                    if (String.valueOf(playerText.charAt(i)).equals(String.valueOf(answerText.charAt(i)))) {
                        System.out.println("works");
                    } else {
                        System.out.println("broke");
                    }
                }
            }   else    {
                if (playerText.length() > 0) {
            playerText = playerText.substring(0, playerText.length() - 1);
                }
            }
            System.out.println(playerText.length());
            System.out.println(playerText);
            sb.setLength(0);
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
    }

}
