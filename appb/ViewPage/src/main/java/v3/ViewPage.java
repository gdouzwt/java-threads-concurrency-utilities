package v3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class ViewPage {
    public static void main(String[] args) {
        Runnable r = () ->
        {
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
                txtURL.setEnabled(false);
                Runnable worker = () ->
                {
                    InputStream is = null;
                    try {
                        URL url = new URL(txtURL.getText());
                        is = url.openStream();
                        final StringBuilder sb = new StringBuilder();
                        int b;
                        while ((b = is.read()) != -1)
                            sb.append((char) b);
                        Runnable r1 = () ->
                        {
                            txtHTML.setText(sb.toString());
                            txtURL.setEnabled(true);
                        };
                        try {
                            EventQueue.invokeAndWait(r1);
                        } catch (InterruptedException ie) {
                        } catch (InvocationTargetException ite) {
                        }
                    } catch (final IOException ioe) {
                        Runnable r1 = () ->
                        {
                            txtHTML.setText(ioe.getMessage());
                            txtURL.setEnabled(true);
                        };
                        try {
                            EventQueue.invokeAndWait(r1);
                        } catch (InterruptedException ie) {
                        } catch (InvocationTargetException ite) {
                        }
                    } finally {
                        Runnable r1 = () ->
                        {
                            txtHTML.setCaretPosition(0);
                            txtURL.setEnabled(true);
                        };
                        try {
                            EventQueue.invokeAndWait(r1);
                        } catch (InterruptedException ie) {
                        } catch (InvocationTargetException ite) {
                        }
                        if (is != null)
                            try {
                                is.close();
                            } catch (IOException ioe) {
                            }
                    }
                };
                new Thread(worker).start();
            };
            txtURL.addActionListener(al);
            frame.pack();
            frame.setVisible(true);
        };
        EventQueue.invokeLater(r);
    }
}
