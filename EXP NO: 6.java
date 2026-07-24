import java.awt.*;
import java.awt.event.*;

public class Main extends Frame implements ActionListener {

    Label l1, l2, l3;
    TextField t1, t2, t3;
    Button add, sub, mul, div;

    Main() {

        setTitle("Simple Calculator");
        setSize(350, 250);
        setLayout(new FlowLayout());

        l1 = new Label("First Number");
        t1 = new TextField(15);

        l2 = new Label("Second Number");
        t2 = new TextField(15);

        add = new Button("Add");
        sub = new Button("Subtract");
        mul = new Button("Multiply");
        div = new Button("Divide");

        l3 = new Label("Result");
        t3 = new TextField(20);
        t3.setEditable(false);

        add(l1);
        add(t1);
        add(l2);
        add(t2);

        add(add);
        add(sub);
        add(mul);
        add(div);

        add(l3);
        add(t3);

        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        double n1 = Double.parseDouble(t1.getText());
        double n2 = Double.parseDouble(t2.getText());
        double result = 0;

        if (e.getSource() == add) {
            result = n1 + n2;
            t3.setText(String.valueOf(result));
        }

        else if (e.getSource() == sub) {
            result = n1 - n2;
            t3.setText(String.valueOf(result));
        }

        else if (e.getSource() == mul) {
            result = n1 * n2;
            t3.setText(String.valueOf(result));
        }

        else if (e.getSource() == div) {
            if (n2 == 0) {
                t3.setText("Cannot divide by zero");
            } else {
                result = n1 / n2;
                t3.setText(String.valueOf(result));
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
