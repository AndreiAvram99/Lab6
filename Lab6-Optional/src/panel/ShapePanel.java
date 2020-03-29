package panel;

import frame.MainFrame;
import shape.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * ShapePanel class
 * @author avram
 */

public class ShapePanel extends JPanel{
    final MainFrame FRAME;
    JButton polygonButton = new JButton("Regular polygon");
    JButton circleButton = new JButton("Circle");
    JButton eraseButton = new JButton("Erase figure");
    private ChooseShapeButtonType buttonChoose = ChooseShapeButtonType.DRAW_POLYGON; /** Default */

    public ShapePanel(MainFrame frame){
        this.FRAME = frame;
        init();
    }

    public ShapePanel getShapePanel(){
        return this;
    }

    public ChooseShapeButtonType getButtonChoose(){
        return buttonChoose;
    }

    private void init(){
        setLayout(new GridLayout(10, 1));

        polygonButton.setBackground(Color.green);
        circleButton.setBackground(Color.gray);
        eraseButton.setBackground(Color.gray);

        add(polygonButton);
        polygonButton.addActionListener(this::changePolygonConfigPanel);

        add(circleButton);
        circleButton.addActionListener(this::changeCircleConfigPanel);

        add(eraseButton);
        eraseButton.addActionListener(this::changeErase);
    }


    private void changePolygonConfigPanel(ActionEvent e){
        FRAME.configMenu.removeAll();
        FRAME.configMenu.add(FRAME.configMenu.nbOfEdgesLabel);
        FRAME.configMenu.add(FRAME.configMenu.nbOfEdgesField);
        FRAME.configMenu.add(FRAME.configMenu.colorChoseLabel);
        FRAME.configMenu.add(FRAME.configMenu.colorCombo);
        FRAME.setSize(FRAME.getWidth() + 1, FRAME.getHeight());
        FRAME.setSize(FRAME.getWidth() - 1, FRAME.getHeight());
        FRAME.configMenu.repaint();

        polygonButton.setBackground(Color.green);
        circleButton.setBackground(Color.gray);
        eraseButton.setBackground(Color.gray);
        JOptionPane.showMessageDialog(FRAME, "Now you are drawing polygons");
        buttonChoose = ChooseShapeButtonType.DRAW_POLYGON;
    }

    private void changeCircleConfigPanel(ActionEvent e){
        FRAME.configMenu.removeAll();
        FRAME.configMenu.add(FRAME.configMenu.radiusLabel);
        FRAME.configMenu.add(FRAME.configMenu.radiusField);
        FRAME.configMenu.add(FRAME.configMenu.colorChoseLabel);
        FRAME.configMenu.add(FRAME.configMenu.colorCombo);
        FRAME.setSize(FRAME.getWidth() + 1, FRAME.getHeight());
        FRAME.setSize(FRAME.getWidth() - 1, FRAME.getHeight());
        FRAME.configMenu.repaint();

        circleButton.setBackground(Color.green);
        polygonButton.setBackground(Color.gray);
        eraseButton.setBackground(Color.gray);
        JOptionPane.showMessageDialog(FRAME, "Now you are drawing circles");
        buttonChoose = ChooseShapeButtonType.DRAW_CIRCLE;
    }


    private void changeErase(ActionEvent actionEvent) {
        eraseButton.setBackground(Color.green);
        polygonButton.setBackground(Color.gray);
        circleButton.setBackground(Color.gray);
        JOptionPane.showMessageDialog(FRAME, "Now you are erasing figures");
        buttonChoose = ChooseShapeButtonType.ERASE;
    }

}
