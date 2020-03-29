package panel;

import frame.MainFrame;
import shape.RegularShape;
import shape.ShapeType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DrawingPanel class
 * @author avram
 */

public class DrawingPanel extends JPanel {

    final MainFrame FRAME;
    List<RegularShape> shapeList = new ArrayList<>();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int W = screenSize.width - 300, H = screenSize.height - 180;
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

    private void init() {
        setPreferredSize(new Dimension(W, H));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(FRAME.changeShapeMenu.getButtonChoose().equals(ChooseShapeButtonType.ERASE)) {
                    eraseShape(e.getX(), e.getY());
                }
                else {
                    drawShape(e.getX(), e.getY());
                }
                repaint();
            }
        });
    }

    private void eraseShape(int x, int y){
        List<RegularShape> eraseShapes = new ArrayList<>();
        for(RegularShape shape : shapeList){
          if(shape.getShapeType().equals(ShapeType.POLYGON)){

              if(shape.polygonContainPoints(x, y)){
                  graphics.setColor(Color.WHITE);
                  graphics.fill(shape.getRegularPolygon(shape.getNbOfEdges()));
                  eraseShapes.add(shape);
              }
          }

          else if(shape.getShapeType().equals(ShapeType.CIRCLE)){
              if(shape.ellipseContainPoints(x, y)){
                  graphics.setColor(Color.WHITE);
                  graphics.fill(shape.getRegularEllipse());
                  eraseShapes.add(shape);
              }
          }
        }
        for (RegularShape shape : eraseShapes){
            shapeList.remove(shape);
        }
        eraseShapes.clear();
    }

    private void drawShape(int x, int y) {
        int radius =(int) (Math.random() * 30 + 50);
        int sides = (int) FRAME.configMenu.nbOfEdgesField.getValue();

        if(FRAME.changeShapeMenu.getButtonChoose().equals(ChooseShapeButtonType.DRAW_CIRCLE)) {
            radius = (int) FRAME.configMenu.radiusField.getValue();
        }

        String[] colorsRgb = FRAME.configMenu.getColorsRgb();
        String[] colorOptions = FRAME.configMenu.getColorOptions();
        
        String chosenColor = (String) FRAME.configMenu.colorCombo.getSelectedItem();
        Color color = null;

        assert chosenColor != null;
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
        RegularShape regularShape = new RegularShape(FRAME, x, y, radius);
        shapeList.add(regularShape);

        if(FRAME.changeShapeMenu.getButtonChoose().equals(ChooseShapeButtonType.DRAW_POLYGON)) {
            graphics.fill(regularShape.getRegularPolygon(sides));
        }
        else{
            graphics.fill(regularShape.getRegularEllipse());
        }
    }

    public void clear(){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
        this.repaint();
    }

    private void setImage(BufferedImage image){
        this.image = image;
        graphics = this.image.createGraphics();
        repaint();
    }

    public void saveImage() {
        final JFileChooser saveFileChooser = new JFileChooser();
        int returnValue = saveFileChooser.showSaveDialog(FRAME);

        if(returnValue == JFileChooser.APPROVE_OPTION) {
            File file = saveFileChooser.getSelectedFile();
            System.out.println("Save: " + file.getName());
            try {
                ImageIO.write(this.image, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Cancelled");
        }
    }

    public void loadImage() {

        final JFileChooser openFileChooser = new JFileChooser();
        int returnValue = openFileChooser.showOpenDialog(FRAME);

        if(returnValue == JFileChooser.APPROVE_OPTION) {
            File file = openFileChooser.getSelectedFile();
            System.out.println("Opening: " + file.getName());
            BufferedImage img = null;
            try {
                img = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.setImage(img);
        }
        else {
            System.out.println("Cancelled");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

}
