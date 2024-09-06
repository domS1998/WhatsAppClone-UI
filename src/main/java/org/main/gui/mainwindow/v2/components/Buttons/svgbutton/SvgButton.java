package org.main.gui.mainwindow.v2.components.Buttons.svgbutton;

import javafx.scene.layout.Pane;
import org.main.gui.mainwindow.v2.components.root.Root;


abstract public class SvgButton extends Pane {

    protected Root root;

    public SvgButton(Root root) {

        this.root = root;

        this.setOnMouseEntered((e)->{
            // scale up on hover
            this.setScaleX(this.getScaleY() * 1.15);
            this.setScaleY(this.getScaleY() * 1.15);
        });

        this.setOnMouseExited((e)->{
            // scale up on hover
            this.setScaleX(this.getScaleY() / 1.15);
            this.setScaleY(this.getScaleY() / 1.15);
        });

    }
}
