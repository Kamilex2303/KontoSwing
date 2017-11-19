import java.awt.* ;
import java.awt.event.* ;
import java.util.ArrayList;
import javax.swing.*;

public class Main extends JFrame {

    Konto konto = new Konto();
    public ArrayList<Integer> historiaOperacji = new ArrayList<>();
    int i = historiaOperacji.size();

    JTextField
            rezultat = new JTextField(),
            stan = new JTextField(),
            operacja = new JTextField(),
            historia = new JTextField();
    JButton
            polecenie = new JButton("wplata/wyplata"),
            odblokuj = new JButton("odblokuj"),
            historiaButton = new JButton("Operacja wstecz ");
    JLabel
            stanLable = new JLabel("          Stan : ");

    Main(){
        setTitle("Konto");
        Container c = getContentPane();
        setSize(400 , 600);
        setLayout(new GridLayout(4 , 2));
        c.add(stanLable);
        c.add(stan);


        c.add(polecenie);
        c.add(operacja);


        c.add(odblokuj);
        c.add(rezultat);

        c.add(historiaButton);
        c.add(historia);



        stan.setText(String.valueOf(konto.dajStan()));
        operacja.setText("0");
        polecenie.addActionListener(new Operacja());
        odblokuj.addActionListener(new Odblokuj());
        historiaButton.addActionListener(new Historia());
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
        historiaOperacji.add(ile);
        i=historiaOperacji.size();
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

    class Historia implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                if(i>0) {
                    historia.setText(String.valueOf(historiaOperacji.get(i - 1)));
                    i--;
                }
                else
                    historia.setText("Brak operacji");
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
