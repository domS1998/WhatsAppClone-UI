package org.main.gui;

import java.io.IOException;



public interface Window {

    public void setResolutionX(double resolutionX);
    public void setResolutionY(double resolutionY);

    public double getResolutionX ();
    public double getResolutionY ();
    public void scale (double factor );
    public void scaleTo (int width, int height);
    public void show() throws IOException;
    public void center();
}
