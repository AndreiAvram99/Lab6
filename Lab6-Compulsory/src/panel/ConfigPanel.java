package panel;

import frame.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * ConfigPanel class
 * @author avram
 */

public class ConfigPanel extends JPanel {

    final MainFrame FRAME;
    JLabel nbOfEdgesLabel;
    JSpinner nbOfEdgesField;
    JLabel colorChoseLabel;
    JComboBox<String> colorCombo;
    private String[] colorOptions = {"RANDOM", "BLACK", "BLUE", "GREEN", "RED", "YELLOW", "ORANGE"};
    private String[] colorsRgb = {"#000000", "#325bad", "#24bf13", "#ab1a0f", "#f7f439", "#d17f0d"};
    private Map<String, String> color = new TreeMap<>();


    public ConfigPanel(MainFrame frame){
        FRAME = frame;
        init();
    }

    public String[] getColorsRgb() {
        return colorsRgb;
    }

    public String[] getColorOptions() {
        return colorOptions;
    }

    private void init(){
        nbOfEdgesLabel = new JLabel("Number of edges: ");
        nbOfEdgesField = new JSpinner(new SpinnerNumberModel(3, 3, 30 ,1));

        colorChoseLabel = new JLabel("Color: ");
        colorCombo = new JComboBox<>(colorOptions);

        add(nbOfEdgesLabel);
        add(nbOfEdgesField);

        add(colorChoseLabel);
        add(colorCombo);
    }
}
