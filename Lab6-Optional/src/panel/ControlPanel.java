package panel;

import frame.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * ControlPanel class
 * @author avram
 */

public class ControlPanel extends JPanel{

    final MainFrame FRAME;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        FRAME = frame;
        init();
    }

    private void init(){


        add(saveBtn);
        saveBtn.addActionListener(this::save);

        add(loadBtn);
        loadBtn.addActionListener(this::load);

        add(resetBtn);
        resetBtn.addActionListener(this::reset);

        add(exitBtn);
        exitBtn.addActionListener(this::exit);
    }

    private void save(ActionEvent e) {
        FRAME.canvas.saveImage();
    }

    private void load(ActionEvent e) {
        FRAME.canvas.loadImage();
    }

    private void reset(ActionEvent e){
        FRAME.canvas.clear();
    }

    private void exit(ActionEvent e){
        FRAME.dispose();
    }

}
