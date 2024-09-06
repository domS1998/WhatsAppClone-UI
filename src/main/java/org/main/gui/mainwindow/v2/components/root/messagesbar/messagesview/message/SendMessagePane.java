package org.main.gui.mainwindow.v2.components.root.messagesbar.messagesview.message;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.model.Message;
import org.main.util.Util;

public class SendMessagePane extends AbstractMessagePane {

    private Region tick1 = new Region();
    private Region tick2 = new Region();
    private final String COLOR_UNTICKED = "#6e8187";
    private final String COLOR_TICKED   = "#53bdeb";

    public SendMessagePane(Root root, Message message) {

        super(root, message, MainWindowController.getUser());

        System.out.println("creating send message pane for message: "+message);

        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(3, 20, 0, 10));

        StackPane container = new StackPane();
        container.setStyle("-fx-background-color: #d9fdd3; -fx-border-radius: 10 10 10 10;  -fx-background-radius: 7 7 7 7; -fx-effect: dropshadow(three-pass-box, rgba(209,206,200,0.8), 1, 0, 1, 1);");
        this.getChildren().add(container);

        // username label
        this.userLabel.setText("~ "+MainWindowController.getUser().getUsername());
        this.userLabel.setStyle("-fx-text-fill: #acafb1");

        // Spalte 2
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(0, 0, 0, 5));
        container.getChildren().addAll(vBox);

        HBox hBoxLower = new HBox();
        vBox.getChildren().addAll(hBoxLower);

        // Zeile 2
        hBoxLower.setAlignment(Pos.CENTER_LEFT);
        this.messageLabel.setText(message.getContent());
        this.messageLabel.setFont(new Font("System", 14));
        this.messageLabel.setTranslateX(5);

        this.timeLabel.setText(Util.timestampToMessageTime(message.getTimestamp()));
        this.timeLabel.setStyle("-fx-text-fill: #6e8187");
        this.timeLabel.setFont(new Font("System", 11));
        this.timeLabel.setTranslateX(15);
        HBox.setMargin(this.timeLabel, new Insets(5, 0, 5, 0));

        HBox timeBox = new HBox();
        timeBox.setAlignment(Pos.BOTTOM_CENTER);
        timeBox.getChildren().addAll(this.timeLabel);

        // Haken
        HBox tickBox = new HBox();

        SVGPath svg = new SVGPath();
        svg.setContent("M0 0 C4.12066954 0.98382768 6.9651774 2.97033806 9.2578125 6.5390625 C9.81120656 9.63303837 9.6922416 12.43599747 9.2578125 15.5390625 C8.5978125 15.5390625 7.9378125 15.5390625 7.2578125 15.5390625 C7.061875 16.36277344 6.8659375 17.18648437 6.6640625 18.03515625 C4.807691 22.66061523 1.98844862 25.85382048 -1.2421875 29.6015625 C-2.52117363 31.11840835 -3.79850674 32.63664949 -5.07421875 34.15625 C-5.70150879 34.90100586 -6.32879883 35.64576172 -6.97509766 36.41308594 C-10.21105249 40.3062463 -13.25430577 44.33929631 -16.3203125 48.3671875 C-18.9111943 51.76040689 -21.53852444 55.12246167 -24.1796875 58.4765625 C-24.63972168 59.06107178 -25.09975586 59.64558105 -25.57373047 60.24780273 C-26.96275617 62.01191885 -28.3524322 63.77552113 -29.7421875 65.5390625 C-36.37398036 73.95895798 -42.97468931 82.40215599 -49.55004883 90.86621094 C-53.53362626 95.99176539 -57.53551092 101.10236522 -61.55200195 106.20214844 C-64.74642775 110.26193817 -67.92054927 114.33715194 -71.08837891 118.41772461 C-73.4506532 121.45999061 -75.81679777 124.49922905 -78.18408203 127.53759766 C-79.15000163 128.77836996 -80.11474992 130.02005625 -81.07763672 131.26318359 C-82.16927132 132.67144143 -83.26788288 134.07428379 -84.3671875 135.4765625 C-85.22183594 136.57291016 -85.22183594 136.57291016 -86.09375 137.69140625 C-88.37137414 140.24428828 -90.31542009 142.26148129 -93.75 142.9296875 C-101.5179947 142.41373213 -105.53849356 137.86009624 -110.7421875 132.5390625 C-112.04421628 131.23432186 -113.34770658 129.93103871 -114.65234375 128.62890625 C-116.72877788 126.55632495 -118.80138986 124.48102228 -120.85766602 122.38842773 C-124.71428961 118.47785861 -128.68186298 114.7867632 -132.84082031 111.20019531 C-135.67695648 108.72239993 -138.37835209 106.12483673 -141.0546875 103.4765625 C-141.56580078 102.98607422 -142.07691406 102.49558594 -142.60351562 101.99023438 C-145.84219943 98.78524299 -148.04993309 95.77549391 -149.7421875 91.5390625 C-149.67319524 87.53751122 -149.36892457 85.36030418 -146.9296875 82.1640625 C-143.23745592 79.42126189 -138.77527264 78.88929759 -134.234375 78.94140625 C-128.91857233 80.21619905 -125.4153008 84.72111035 -121.7421875 88.5390625 C-120.20180076 90.08200803 -118.66017919 91.623722 -117.1171875 93.1640625 C-116.385 93.9065625 -115.6528125 94.6490625 -114.8984375 95.4140625 C-112.2590785 98.01516992 -109.48383369 100.4209743 -106.671875 102.83203125 C-103.55272614 105.59127831 -100.64467528 108.55494225 -97.7421875 111.5390625 C-93.83689704 109.47273587 -91.70172256 107.50173612 -89.2421875 103.8515625 C-85.90810586 99.09036928 -82.38213713 94.58115887 -78.6796875 90.1015625 C-73.65523629 83.99411541 -68.8402093 77.75722899 -64.1171875 71.4140625 C-55.51359366 59.86216261 -46.61991557 48.59392244 -37.51391602 37.43530273 C-31.53969808 30.09590526 -25.77382897 22.60370754 -20.11328125 15.01953125 C-19.67459717 14.4371167 -19.23591309 13.85470215 -18.78393555 13.25463867 C-17.57201775 11.6450854 -16.36776415 10.02976942 -15.1640625 8.4140625 C-10.75882025 3.18461364 -7.14727444 -0.46033293 0 0 Z ");
        svg.setFill(Color.BLACK);
        svg.setTranslateX(20);
        tick1.setShape(svg);
        tick1.setMaxWidth(20);
        tick1.setMaxHeight(20);
        tick1.setMinWidth(20);
        tick1.setMinHeight(20);
        tick1.setScaleX(0.5);
        tick1.setScaleY(0.5);
        tick1.setTranslateX(-2+15);
        tick1.setTranslateY(17);

        SVGPath svg2 = new SVGPath();
        svg2.setContent("M0 0 C3.96569539 0.86622698 6.25238791 2.84290974 9.2890625 5.5 C11.15379455 9.22946409 10.51754982 12.69061169 9.2890625 16.5 C4.69489702 23.72067481 -1.09944911 30.01546466 -6.84765625 36.328125 C-9.45755633 39.37027268 -11.66740109 42.6395687 -13.921875 45.94921875 C-17.8016755 51.48089937 -22.14467368 56.67106861 -26.5234375 61.8125 C-28.73157696 64.38538159 -28.73157696 64.38538159 -30.2109375 67.0625 C-31.6501988 69.40129962 -33.24321595 70.99707934 -35.2109375 72.8828125 C-37.93804235 75.82297241 -40.15343672 79.15773299 -42.484375 82.4140625 C-48.55664643 90.83001764 -54.98008208 98.88830862 -61.73876953 106.75854492 C-65.32811205 110.96948762 -68.59709919 115.35440332 -71.80078125 119.8671875 C-74.39436667 123.44198622 -77.17680201 126.84952659 -79.96484375 130.2734375 C-81.41081147 132.11728896 -82.67602161 133.94432389 -83.8984375 135.9375 C-86.43321786 139.52115499 -89.03480407 140.88247716 -93.0859375 142.375 C-98.53105849 142.63429148 -102.65101028 139.76167999 -106.60546875 136.3203125 C-107.92067755 135.09839024 -109.22657413 133.86637568 -110.5234375 132.625 C-111.19052734 132.01269531 -111.85761719 131.40039062 -112.54492188 130.76953125 C-118.60780836 125.0553678 -118.60780836 125.0553678 -119.7109375 120.5 C-119.50737007 115.61438157 -118.04051642 113.03137158 -114.7109375 109.5 C-111.02808426 107.65857338 -106.73923659 108.13043128 -102.7109375 108.5 C-99.0859375 110 -99.0859375 110 -96.7109375 111.5 C-92.78331171 108.5993859 -89.99979253 105.53287915 -87.0859375 101.625 C-86.20646213 100.4577807 -85.32622748 99.29113319 -84.4453125 98.125 C-83.99478516 97.52558594 -83.54425781 96.92617187 -83.08007812 96.30859375 C-80.86764324 93.38603354 -78.60369552 90.50471859 -76.3359375 87.625 C-75.87848145 87.04266602 -75.42102539 86.46033203 -74.94970703 85.86035156 C-72.60932523 82.88701582 -70.2541083 79.92682741 -67.875 76.984375 C-62.10777439 69.84566907 -56.50632896 62.61016354 -51.0234375 55.25 C-45.75088613 48.1755209 -40.36349192 41.27538781 -34.7109375 34.5 C-32.55754388 31.88114261 -30.41419099 29.25419581 -28.2734375 26.625 C-27.71962402 25.9455835 -27.16581055 25.26616699 -26.59521484 24.56616211 C-22.25519263 19.22052499 -18.05971526 13.77859226 -13.9296875 8.26953125 C-7.00445249 -0.3748202 -7.00445249 -0.3748202 0 0 Z ");
        svg2.setFill(Color.BLACK);
        tick2.setShape(svg2);
        tick2.setMaxWidth(20);
        tick2.setMaxHeight(20);
        tick2.setMinWidth(20);
        tick2.setMinHeight(20);
        tick2.setScaleX(0.5);
        tick2.setScaleY(0.5);
        tick2.setTranslateX(-16+15);
        tick2.setTranslateY(17);
        tick2.setRotate(-5);

        HBox tickContainer = new HBox();
        tickContainer.setMinWidth(40);
        tickContainer.setMinHeight(40);
        tickContainer.setMaxWidth(40);
        tickContainer.setMaxHeight(40);
        tickContainer.getChildren().addAll(tick1, tick2);
        tickBox.getChildren().add(tickContainer);

        Polygon triangle = new Polygon(0, 0, 0, 15, 15, 0);
        triangle.setFill(Color.web("#d9fdd3"));
        triangle.setStyle("-fx-border-radius: 10 10 10 10;  -fx-background-radius: 7 7 7 7; -fx-effect: dropshadow(three-pass-box, rgba(209,206,200,0.8), 1, 0, 0.5, 0.5);");
        triangle.setTranslateX(-2);
        triangle.setTranslateY(1);

        tickContainer.getChildren().addAll(triangle);
        hBoxLower.getChildren().addAll(this.messageLabel, timeBox, tickBox);

        this.setSingleGreyTick();


        if (message.isReceived()){
            System.out.println("setting double tick ...");
            this.setDoubleGreyTick();
        }

        if (message.isRead()){
            this.setTicked();
        }
    }


    public void setSingleGreyTick () {
        tick1.setStyle("-fx-background-color: "+COLOR_UNTICKED+";");
    }

    public void setDoubleGreyTick () {
        tick1.setStyle("-fx-background-color: "+COLOR_UNTICKED+";");
        tick2.setStyle("-fx-background-color: "+COLOR_UNTICKED+";");
    }

    public void setTicked () {
        tick1.setStyle("-fx-background-color: "+COLOR_TICKED+";");
        tick2.setStyle("-fx-background-color: "+COLOR_TICKED+";");
    }

}
