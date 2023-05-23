import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TriviaGUI extends JFrame implements ActionListener, KeyListener {
    private JLabel title;
    private JComboBox typeSelector;
    private JButton confirmButton;
    private JPanel mainPanel;

    public TriviaGUI() {
        test();
    }

    private void test() {
        setContentPane(mainPanel);
        setTitle("Trivia Game!");
        setSize(400, 200);
        setLocation(450, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        confirmButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            String temp = typeSelector.getSelectedItem().toString();
            if (temp.equals("Cat")) {
                System.out.println("its a cat");
            }
            if (temp.equals("Dog")) {
                System.out.println("its a dog");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
