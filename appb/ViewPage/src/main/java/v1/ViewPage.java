import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ViewPage {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("View Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter URL"));
        final JTextField txtURL = new JTextField(40);
        panel.add(txtURL);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        final JTextArea txtHTML = new JTextArea(10, 40);
        frame.getContentPane().add(new JScrollPane(txtHTML),
                BorderLayout.CENTER);
        ActionListener al = (ae) ->
        {
            InputStream is = null;
            try {
                URL url = new URL(txtURL.getText());
                is = url.openStream();
                StringBuilder sb = new StringBuilder();
                int b;
                while ((b = is.read()) != -1)
                    sb.append((char) b);
                txtHTML.setText(sb.toString());
            } catch (IOException ioe) {
                txtHTML.setText(ioe.getMessage());
            } finally {
                txtHTML.setCaretPosition(0);
                if (is != null)
                    try {
                        is.close();
                    } catch (IOException ioe) {
                    }
            }
        };
        txtURL.addActionListener(al);
        frame.pack();
        frame.setVisible(true);
    }
}
