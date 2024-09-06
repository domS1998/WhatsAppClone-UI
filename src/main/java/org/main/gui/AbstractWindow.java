package org.main.gui;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public abstract class AbstractWindow implements Window {


    protected Stage stage;
    protected double resolutionX  = 1920.0;
    protected double resolutionY  = 1080.0;

    public void setResolutionX(double resolutionX) {
        this.resolutionX = resolutionX;
    }
    public void setResolutionY(double resolutionY) {
        this.resolutionY = resolutionY;
    }

    public double getResolutionX (){
        return this.resolutionX;
    }
    public double getResolutionY (){
        return this.resolutionY;
    }

    protected AbstractWindow(Stage stage) {
        this.stage = stage;
        Image icon = new Image("pics/icons/whatsapp-icon.png"); // Bild zu Image Objekt
        stage.getIcons().add(icon);
    }

    public void scale (double factor ) {
        this.stage.setWidth(this.stage.getWidth()*factor);
        this.stage.setHeight(this.stage.getHeight()*factor);
    }

    public void scaleTo (int width, int height) {
        this.stage.setWidth(width);
        this.stage.setHeight(height);
    }

    public void center(){
        System.out.println("centering window ...");
        this.stage.setX((this.resolutionX-this.stage.getWidth())/2);
        this.stage.setY((this.resolutionY-this.stage.getHeight())/2);
    }

    public void show() throws IOException {
        this.stage.show();
    }


}
