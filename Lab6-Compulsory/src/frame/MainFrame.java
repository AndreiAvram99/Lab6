package frame;

import panel.ConfigPanel;
import panel.ControlPanel;
import panel.DrawingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * MainFrame class
 * @author avram
 * */

public class MainFrame extends JFrame {

    public ConfigPanel configMenu; /** Shape configuration*/
    public DrawingPanel canvas; /** Drawing part */
    public ControlPanel controlMenu; /** Save, Load, .. */

    public MainFrame(){
        super("Draw App");
        init();
    }

    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configMenu = new ConfigPanel(this);
        canvas = new DrawingPanel(this);
        controlMenu = new ControlPanel(this);

        this.setLayout(new BorderLayout());

        add(configMenu, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlMenu, BorderLayout.SOUTH);

        pack();
    }
}
