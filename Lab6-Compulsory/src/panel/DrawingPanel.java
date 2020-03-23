package panel;

import frame.MainFrame;
import shape.RegularPolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * DrawingPanel class
 * @author avram
 */

public class DrawingPanel extends JPanel {

    final MainFrame FRAME;
    final static int W = 500, H = 500;
    BufferedImage image;
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame) {
        FRAME = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    public void setImage(BufferedImage image){
        this.image = image;
        repaint();
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private void drawShape(int x, int y) {
        int radius =(int) (Math.random() * 30 + 50);
        int sides = (int) FRAME.configMenu.nbOfEdgesField.getValue();

        String[] colorsRgb = FRAME.configMenu.getColorsRgb();
        String[] colorOptions = FRAME.configMenu.getColorOptions();
        
        String chosenColor = (String) FRAME.configMenu.colorCombo.getSelectedItem();
        Color color = null;

        if(chosenColor.equals("RANDOM"))
        {
            int index = (int)(Math.random() * colorsRgb.length);
            color = Color.decode(colorsRgb[index]);
        }

        else {
            for(int index = 0 ; index < colorOptions.length; index++){
                
                if(colorOptions[index].equals(chosenColor)){
                    color = Color.decode(colorsRgb[index - 1]);
                }
            }
        }
        graphics.setColor(color);
        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

}
