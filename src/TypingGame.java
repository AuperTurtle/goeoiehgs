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
    private JTextField textField;
    private JPanel typePanel;

    public TypingGame() {
        startPanel();
    }


    private void startPanel() {
        setContentPane(typePanel);
        setTitle("Typing Game!");
        setSize(400, 200);
        setLocation(450, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textField.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
