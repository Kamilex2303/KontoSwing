import java.awt.* ;
import java.awt.event.* ;
import javax.swing.*;

public class Main extends JFrame {

    Konto konto = new Konto();

    JTextField
            rezultat = new JTextField(),
            stan = new JTextField(),
            operacja = new JTextField();
    JButton
            polecenie = new JButton("wplata/wyplata"),
            odblokuj = new JButton("odblokuj");
    JLabel
            stanLable = new JLabel("          Stan : ");

    Main(){
        setTitle("Konto");
        Container c = getContentPane();
        setSize(400 , 600);
        setLayout(new GridLayout(3 , 2));
        c.add(stanLable);
        c.add(stan);

        c.add(polecenie);
        c.add(operacja);

        c.add(rezultat);
        c.add(odblokuj);

        stan.setText(String.valueOf(konto.dajStan()));
        operacja.setText("0");
        polecenie.addActionListener(new Operacja());
        odblokuj.addActionListener(new Odblokuj());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void blokada(){
        operacja.setEnabled(false);
        polecenie.setEnabled(false);
        rezultat.setEnabled(false);
    }

    void obliczenia(){
        int ile = Integer.parseInt(operacja.getText());
        try {
            konto.operacja(ile);
            rezultat.setText("OK");
            stan.setText(String.valueOf(konto.dajStan()));
        } catch (DebetException m) {
            int brakujace = -konto.dajStan() - ile;
            rezultat.setText("Brakuje : "+brakujace);
            m.printStackTrace();
        }
    }
    class Operacja implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            blokada();
            obliczenia();
        }
    }

    class Odblokuj implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            operacja.setEnabled(true);
            rezultat.setEnabled(true);
            polecenie.setEnabled(true);
            operacja.setText("0");
            rezultat.setText("");
        }
    }
    public static void main(String[] args) {
        JFrame f1 = new Main();
    }
}
