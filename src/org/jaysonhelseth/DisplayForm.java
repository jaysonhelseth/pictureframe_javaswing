package org.jaysonhelseth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DisplayForm {
    private JPanel displayPanel;
    private JButton quitButton;
    private JLabel image;

    public DisplayForm() {
        image.setIcon(new StretchIcon(getFile(), true));
        quitButton.addActionListener(e -> System.exit(0));
        ActionListener timerAction = e -> image.setIcon(new StretchIcon(getFile(), true));

        Timer timer = new Timer(2000, timerAction);
        timer.setRepeats(true);
        timer.start();
    }

    private String getFile() {
        try {
            List<File> files = Files.list(Paths.get("images"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            Random rand = new Random();
            int pos = rand.nextInt(files.size());

            return files.get(pos).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DisplayForm");
        GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();

        frame.setContentPane(new DisplayForm().displayPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.pack();

        device.setFullScreenWindow(frame);
        frame.setVisible(true);
    }
}
