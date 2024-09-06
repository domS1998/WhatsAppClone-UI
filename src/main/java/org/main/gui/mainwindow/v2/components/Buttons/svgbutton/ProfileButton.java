package org.main.gui.mainwindow.v2.components.Buttons.svgbutton;

import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import org.main.gui.mainwindow.v2.components.root.Root;

public class ProfileButton extends SvgButton {

    public ProfileButton(Root root) {

        super(root);

        SVGPath svg = new SVGPath();
        svg.setContent( "M0 0 C0.66515625 0.21495117 1.3303125 0.42990234 2.015625 0.65136719 C19.14040669 " +
                "6.28939817 34.77129239 14.99053749 49 26 C49.928125 26.70511719 50.85625 27.41023438 " +
                "51.8125 28.13671875 C86.74834848 55.60807635 107.47410576 95.27115388 113.1875 139 " +
                "C117.13885039 173.67919253 110.73952939 212.22774777 91.38671875 241.8671875 C90.23130273 " +
                "243.64424988 89.17617565 245.48601227 88.1484375 247.33984375 C86.04411686 251.04724445 " +
                "83.59245337 254.3590299 80.9375 257.6875 C80.46070801 258.28836426 79.98391602 258.88922852 " +
                "79.49267578 259.50830078 C72.06443459 268.76375774 64.10576648 277.35565283 55 285 C53.13752803 " +
                "282.20629204 52.44209971 280.44678928 51.58984375 277.2734375 C43.75775748 249.21626737 " +
                "29.74428437 225.29083699 4 210 C-4.58300298 205.18165911 -13.53225549 200.76031424 -23 198 " +
                "C-23.99773437 197.70222656 -24.99546875 197.40445313 -26.0234375 197.09765625 C-53.5155848 " +
                "190.27180274 -82.8685187 195.33619789 -107.25390625 209.25 C-135.29759713 227.12137058 " +
                "-149.55141115 252.55799681 -158 284 C-163.31687371 282.51127536 -166.43722129 279.02064201 " +
                "-170.1875 275.1875 C-170.85096436 274.51388428 -171.51442871 273.84026855 -172.19799805 " +
                "273.14624023 C-203.76355285 240.59294581 -218.92080545 197.17105969 -218.26953125 152.2109375 " +
                "C-217.05072065 114.63794859 -203.04446729 79.67025659 -179 51 C-178.30519531 50.09894531 " +
                "-177.61039063 49.19789062 -176.89453125 48.26953125 C-172.35273465 42.44156216 -167.61885567 " +
                "37.70172415 -161.78515625 33.16796875 C-160.00410996 31.78160032 -158.25700673 30.34973229 " +
                "-156.55859375 28.86328125 C-133.12119801 8.43350124 -102.56473691 -3.26590863 -72 -7 " +
                "C-71.27474121 -7.0942627 -70.54948242 -7.18852539 -69.80224609 -7.28564453 C-46.39103347 " +
                "-10.158722 -22.3388632 -7.36550254 0 0 Z M-97.9921875 83.1875 C-102.51368524 88.62105952 " +
                "-105.48593127 94.41553429 -108 101 C-108.32097656 101.82886719 -108.64195313 102.65773437 " +
                "-108.97265625 103.51171875 C-114.00204179 118.78701529 -111.41737748 135.44269823 -104.4375 " +
                "149.625 C-95.89612537 164.17538191 -82.33425039 174.66863338 -66.0625 179.25 C-48.98286698 " +
                "182.5760338 -32.55834656 179.43732013 -18 170 C-7.75449232 162.15846619 -0.59847354 " +
                "153.04362117 4 141 C4.32097656 140.17113281 4.64195312 139.34226562 4.97265625 138.48828125 " +
                "C10.00204179 123.21298471 7.41737748 106.55730177 0.4375 92.375 C-8.42417731 77.27897685 " +
                "-21.49504907 68.29734499 -38 63 C-60.39955833 57.18862033 -82.96863591 66.69105119 " +
                "-97.9921875 83.1875 Z");


        Region region = new Region();
        region.setShape(svg);

        region.setStyle("-fx-background-color: grey");
        region.setMinWidth(33);
        region.setMinHeight(30);
        region.setTranslateX(15);
        region.setTranslateY(15);


        this.getChildren().addAll(region);
        this.setMinWidth(60);
        this.setMinHeight(60);
        this.setPrefWidth(60);
        this.setPrefHeight(60);


    }
}
