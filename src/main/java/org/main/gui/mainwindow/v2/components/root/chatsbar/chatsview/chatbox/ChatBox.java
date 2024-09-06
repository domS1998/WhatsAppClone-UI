package org.main.gui.mainwindow.v2.components.root.chatsbar.chatsview.chatbox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.gui.mainwindow.v2.components.root.chatsbar.AbstractChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.ChatsBar;
import org.main.model.Chat;
import org.main.model.Message;
import org.main.model.User;
import org.main.util.Util;


public class ChatBox extends HBox {

    private Root root;
    private Chat chat;
    private VBox vBox = new VBox();
    private Label msgLabel;
    private Label unreadLabel;
    private Label dateLabel;
    private Label chatLabel;
    private HBox unreadBox;
    private HBox lowerLeftBox;
    private Region tick1 = new Region();
    private Region tick2 = new Region();
    private final String COLOR_UNTICKED = "#6e8187";
    private final String COLOR_TICKED   = "#53bdeb";
    private boolean sendMsg = true;

    public Chat getChat() { return chat; }
    public Label getMsgLabel () { return this.msgLabel;}
    public Label getUnreadLabel () { return this.unreadLabel;}
    public Label getDateLabel () { return this.dateLabel;}
    public Label getChatLabel () { return this.chatLabel;}

    public ChatBox(Root root, Chat chat, User user) {

        this.root = root;
        this.chat = chat;
        this.setStyle("-fx-background-color: #ffffff;");

         // Bild
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
        region.setStyle("-fx-background-color: grey;");
        region.setMinWidth(48);
        region.setMinHeight(40);
        region.setPrefWidth(48);
        region.setPrefHeight(40);
        region.setMaxWidth(48);
        region.setMaxHeight(40);
        StackPane.setMargin(region, new Insets(10, 10, 10, 10));
        StackPane stackPane = new StackPane(region);

//        this.getChildren().addAll(picture, vBox);
        this.getChildren().addAll(stackPane, vBox);

        HBox lineBox = new HBox();
        lineBox.setAlignment(Pos.CENTER_RIGHT);
        lineBox.setMinHeight(1);
        lineBox.setPrefHeight(1);
        lineBox.setMaxHeight(1);
        lineBox.setStyle("-fx-background-color: #eaeaea;");

        Pane pane = new Pane();
        pane.setMinWidth(5);
        pane.setMinHeight(1);
        pane.setPrefWidth(5);
        pane.setPrefHeight(1);
        pane.setMaxWidth(5);
        pane.setMaxHeight(1);
        pane.setStyle("-fx-background-color: #ffffff;");
        lineBox.getChildren().add(pane);

        // VBox initialisieren mit Name / Tel, Datum, letze Nachricht
        HBox upperBox = new HBox();
//        upperBox.setStyle("-fx-background-color: blue");
//        upperBox.setStyle("-fx-background-color: purple;");
        HBox lowerBox = new HBox();
//        lowerBox.setStyle("-fx-background-color: blue;");
        this.vBox.getChildren().addAll(lineBox, upperBox, lowerBox);

            // Inhalt obere Box
            upperBox.setAlignment(Pos.CENTER_LEFT);

            // Tel / Benutzername
            this.chatLabel = new Label();
            this.chatLabel.setText(chat.getOtherUser(MainWindowController.getUser().getUsername()));
            this.chatLabel.setFont(new Font("System", 18));
            this.chatLabel.setPrefWidth(10000);
            HBox.setMargin(chatLabel, new Insets(10, 0, 0, 10));

            // Datum
            this.dateLabel = new Label();
            this.dateLabel.setStyle("-fx-text-fill: #85959f;");
            HBox.setMargin(dateLabel, new Insets(0, 10, 0, 0));

            HBox dateBox = new HBox(dateLabel);
            dateBox.setAlignment(Pos.CENTER_RIGHT);
            dateBox.setMinWidth(100);
//            dateBox.setStyle("-fx-background-color: red");

            upperBox.getChildren().addAll(chatLabel, dateBox);


            // Inhalt untere Box
            this.msgLabel = new Label();
            this.msgLabel.setStyle("-fx-text-fill: #85959f;");
            this.msgLabel.setPadding(new Insets(5, 10, 5, 10));

            this.unreadBox = new HBox();
            this.unreadBox.setAlignment(Pos.CENTER);
            this.unreadBox.setStyle("-fx-background-color: #236be3; -fx-background-radius: 10 10 10 10;");
            this.unreadBox.setPadding(new Insets(5, 5, 5, 5));
            this.unreadBox.setMinHeight(20);
            this.unreadBox.setMinWidth(20);
            this.unreadBox.setPrefHeight(20);
            this.unreadBox.setMaxHeight(20);
            this.unreadBox.setVisible(false);

            this.unreadLabel = new Label();
            this.unreadBox.getChildren().addAll(unreadLabel);
            this.unreadLabel.setText("1");
            this.unreadLabel.setFont(new Font("System", 14));
            this.unreadLabel.setStyle("-fx-text-fill: #ffffff;");

            this.lowerLeftBox = new HBox();

//            setTicked();

            lowerLeftBox.setAlignment(Pos.CENTER_LEFT);
            lowerLeftBox.getChildren().addAll(/*tickBox,*/ msgLabel);
            lowerLeftBox.setPrefWidth(10000);
//            lowerLeftBox.setStyle("-fx-background-color: blue;");

            HBox lowerRightBox = new HBox();
            lowerRightBox.setAlignment(Pos.CENTER_RIGHT);
            lowerRightBox.getChildren().add(unreadBox);
            lowerRightBox.setMinWidth(55);
//            lowerRightBox.setStyle("-fx-background-color: purple;");
            lowerRightBox.setPadding(new Insets(0, 10, 0, 10));

            lowerBox.setPadding(new Insets(0, 0, 10, 0));
            lowerBox.getChildren().addAll(lowerLeftBox, lowerRightBox);



        // event handler setzen
        this.setOnMouseEntered(E->{
            if (this.chat != MainWindowController.getCurChat()) {
                this.setStyle("-fx-background-color: #f0f2f5");
            }
        });

        this.setOnMouseExited(E->{
            if (this.chat != MainWindowController.getCurChat()) {
                this.setStyle("-fx-background-color: #ffffff");
            }
        });

        this.setOnMouseClicked(E -> {
            // keine Aktion, wenn aktueller Chat = bereits angeklickter Chat
            if (MainWindowController.getCurChat() == this.chat) {return;}
            Chat oldCurChat = MainWindowController.getCurChat();

            // aktuellen Chat auf Angeklickten aktualisieren
            MainWindowController.setCurChat(this.chat);

            // aktuellen chat einfärben
            this.setStyle("-fx-background-color: #f0f2f5");

            // alten chat weiß färben
            if (MainWindowController.getCurChat() != null) {
                if (oldCurChat != null) {
                    ChatBox box = ((ChatsBar) AbstractChatsBar.getChatsPane("chatsBar")).getChatsView().getChatBox(oldCurChat);
                    box.setStyle("-fx-background-color: #ffffff");
                }
            }
            this.root.displayMessagesBar(this.chat);
        });
    }

    public void setUnreadMessages(Message lastMsg) {

        // Wenn bereits ungelesene Nachrichten, Labelzahl erhöhen
        if (this.unreadBox.isVisible()) {
            this.unreadLabel.setText(""+(Integer.parseInt(this.unreadLabel.getText())+1));
            this.msgLabel.setText(lastMsg.getContent());
        }
        else {
            this.unreadLabel.setText(1 + "");
            this.msgLabel.setText(lastMsg.getContent());
            this.unreadBox.setVisible(true);
            this.dateLabel.setStyle("-fx-text-fill: #236be3;");
        }
    }

    public void setRead(){
        System.out.println("pane is sendMsg pane: " + this.sendMsg);
//        if ( ! this.sendMsg ) {return;}
        this.unreadBox.setVisible(false);
        this.dateLabel.setStyle("-fx-text-fill: #85959f;");
    }

    public void setSingleGreyTick () {
        if ( ! this.sendMsg ) {return;}
        tick1.setStyle("-fx-background-color: "+COLOR_UNTICKED+";");
    }

    public void setDoubleGreyTick () {
        if ( ! this.sendMsg ) {return;}
        tick1.setStyle("-fx-background-color: "+COLOR_UNTICKED+";");
        tick2.setStyle("-fx-background-color: "+COLOR_UNTICKED+";");
    }

    public void setTicked () {
        tick1.setStyle("-fx-background-color: "+COLOR_TICKED+";");
        tick2.setStyle("-fx-background-color: "+COLOR_TICKED+";");
    }

    public void setSendMsg (Message message) {

        this.sendMsg = true;

        // Text
        this.getMsgLabel().setText("You: " + message.getContent());
        this.msgLabel.setTranslateX(-17);

        //datum
        dateLabel.setText(Util.timestampToTimeString(chat.getMostRecentMsg().getTimestamp()));

        // Haken
        HBox tickBox = new HBox();
        tickBox.setVisible(true);
        // alte Haken zurücksetzen
        tick1 = new Region();
        tick2 = new Region();

        SVGPath svg2 = new SVGPath();
        svg2.setContent("M0 0 C4.12066954 0.98382768 6.9651774 2.97033806 9.2578125 6.5390625 C9.81120656 9.63303837 9.6922416 12.43599747 9.2578125 15.5390625 C8.5978125 15.5390625 7.9378125 15.5390625 7.2578125 15.5390625 C7.061875 16.36277344 6.8659375 17.18648437 6.6640625 18.03515625 C4.807691 22.66061523 1.98844862 25.85382048 -1.2421875 29.6015625 C-2.52117363 31.11840835 -3.79850674 32.63664949 -5.07421875 34.15625 C-5.70150879 34.90100586 -6.32879883 35.64576172 -6.97509766 36.41308594 C-10.21105249 40.3062463 -13.25430577 44.33929631 -16.3203125 48.3671875 C-18.9111943 51.76040689 -21.53852444 55.12246167 -24.1796875 58.4765625 C-24.63972168 59.06107178 -25.09975586 59.64558105 -25.57373047 60.24780273 C-26.96275617 62.01191885 -28.3524322 63.77552113 -29.7421875 65.5390625 C-36.37398036 73.95895798 -42.97468931 82.40215599 -49.55004883 90.86621094 C-53.53362626 95.99176539 -57.53551092 101.10236522 -61.55200195 106.20214844 C-64.74642775 110.26193817 -67.92054927 114.33715194 -71.08837891 118.41772461 C-73.4506532 121.45999061 -75.81679777 124.49922905 -78.18408203 127.53759766 C-79.15000163 128.77836996 -80.11474992 130.02005625 -81.07763672 131.26318359 C-82.16927132 132.67144143 -83.26788288 134.07428379 -84.3671875 135.4765625 C-85.22183594 136.57291016 -85.22183594 136.57291016 -86.09375 137.69140625 C-88.37137414 140.24428828 -90.31542009 142.26148129 -93.75 142.9296875 C-101.5179947 142.41373213 -105.53849356 137.86009624 -110.7421875 132.5390625 C-112.04421628 131.23432186 -113.34770658 129.93103871 -114.65234375 128.62890625 C-116.72877788 126.55632495 -118.80138986 124.48102228 -120.85766602 122.38842773 C-124.71428961 118.47785861 -128.68186298 114.7867632 -132.84082031 111.20019531 C-135.67695648 108.72239993 -138.37835209 106.12483673 -141.0546875 103.4765625 C-141.56580078 102.98607422 -142.07691406 102.49558594 -142.60351562 101.99023438 C-145.84219943 98.78524299 -148.04993309 95.77549391 -149.7421875 91.5390625 C-149.67319524 87.53751122 -149.36892457 85.36030418 -146.9296875 82.1640625 C-143.23745592 79.42126189 -138.77527264 78.88929759 -134.234375 78.94140625 C-128.91857233 80.21619905 -125.4153008 84.72111035 -121.7421875 88.5390625 C-120.20180076 90.08200803 -118.66017919 91.623722 -117.1171875 93.1640625 C-116.385 93.9065625 -115.6528125 94.6490625 -114.8984375 95.4140625 C-112.2590785 98.01516992 -109.48383369 100.4209743 -106.671875 102.83203125 C-103.55272614 105.59127831 -100.64467528 108.55494225 -97.7421875 111.5390625 C-93.83689704 109.47273587 -91.70172256 107.50173612 -89.2421875 103.8515625 C-85.90810586 99.09036928 -82.38213713 94.58115887 -78.6796875 90.1015625 C-73.65523629 83.99411541 -68.8402093 77.75722899 -64.1171875 71.4140625 C-55.51359366 59.86216261 -46.61991557 48.59392244 -37.51391602 37.43530273 C-31.53969808 30.09590526 -25.77382897 22.60370754 -20.11328125 15.01953125 C-19.67459717 14.4371167 -19.23591309 13.85470215 -18.78393555 13.25463867 C-17.57201775 11.6450854 -16.36776415 10.02976942 -15.1640625 8.4140625 C-10.75882025 3.18461364 -7.14727444 -0.46033293 0 0 Z ");
        svg2.setFill(Color.BLACK);
        svg2.setTranslateX(20);
        tick1.setShape(svg2);
        tick1.setMaxWidth(20);
        tick1.setMaxHeight(20);
        tick1.setMinWidth(20);
        tick1.setMinHeight(20);
        tick1.setScaleX(0.5);
        tick1.setScaleY(0.5);
        tick1.setTranslateX(-2+10);
        tick1.setTranslateY(17-7);

        SVGPath svg3 = new SVGPath();
        svg3.setContent("M0 0 C3.96569539 0.86622698 6.25238791 2.84290974 9.2890625 5.5 C11.15379455 9.22946409 10.51754982 12.69061169 9.2890625 16.5 C4.69489702 23.72067481 -1.09944911 30.01546466 -6.84765625 36.328125 C-9.45755633 39.37027268 -11.66740109 42.6395687 -13.921875 45.94921875 C-17.8016755 51.48089937 -22.14467368 56.67106861 -26.5234375 61.8125 C-28.73157696 64.38538159 -28.73157696 64.38538159 -30.2109375 67.0625 C-31.6501988 69.40129962 -33.24321595 70.99707934 -35.2109375 72.8828125 C-37.93804235 75.82297241 -40.15343672 79.15773299 -42.484375 82.4140625 C-48.55664643 90.83001764 -54.98008208 98.88830862 -61.73876953 106.75854492 C-65.32811205 110.96948762 -68.59709919 115.35440332 -71.80078125 119.8671875 C-74.39436667 123.44198622 -77.17680201 126.84952659 -79.96484375 130.2734375 C-81.41081147 132.11728896 -82.67602161 133.94432389 -83.8984375 135.9375 C-86.43321786 139.52115499 -89.03480407 140.88247716 -93.0859375 142.375 C-98.53105849 142.63429148 -102.65101028 139.76167999 -106.60546875 136.3203125 C-107.92067755 135.09839024 -109.22657413 133.86637568 -110.5234375 132.625 C-111.19052734 132.01269531 -111.85761719 131.40039062 -112.54492188 130.76953125 C-118.60780836 125.0553678 -118.60780836 125.0553678 -119.7109375 120.5 C-119.50737007 115.61438157 -118.04051642 113.03137158 -114.7109375 109.5 C-111.02808426 107.65857338 -106.73923659 108.13043128 -102.7109375 108.5 C-99.0859375 110 -99.0859375 110 -96.7109375 111.5 C-92.78331171 108.5993859 -89.99979253 105.53287915 -87.0859375 101.625 C-86.20646213 100.4577807 -85.32622748 99.29113319 -84.4453125 98.125 C-83.99478516 97.52558594 -83.54425781 96.92617187 -83.08007812 96.30859375 C-80.86764324 93.38603354 -78.60369552 90.50471859 -76.3359375 87.625 C-75.87848145 87.04266602 -75.42102539 86.46033203 -74.94970703 85.86035156 C-72.60932523 82.88701582 -70.2541083 79.92682741 -67.875 76.984375 C-62.10777439 69.84566907 -56.50632896 62.61016354 -51.0234375 55.25 C-45.75088613 48.1755209 -40.36349192 41.27538781 -34.7109375 34.5 C-32.55754388 31.88114261 -30.41419099 29.25419581 -28.2734375 26.625 C-27.71962402 25.9455835 -27.16581055 25.26616699 -26.59521484 24.56616211 C-22.25519263 19.22052499 -18.05971526 13.77859226 -13.9296875 8.26953125 C-7.00445249 -0.3748202 -7.00445249 -0.3748202 0 0 Z ");
        svg3.setFill(Color.BLACK);
        tick2.setShape(svg3);
        tick2.setMaxWidth(20);
        tick2.setMaxHeight(20);
        tick2.setMinWidth(20);
        tick2.setMinHeight(20);
        tick2.setScaleX(0.5);
        tick2.setScaleY(0.5);
        tick2.setTranslateX(-16+10);
        tick2.setTranslateY(17-7);
        tick2.setRotate(-5);

        HBox tickContainer = new HBox();
        tickContainer.setMinWidth(40);
        tickContainer.setMinHeight(40);
        tickContainer.setMaxWidth(40);
        tickContainer.setMaxHeight(40);
        tickContainer.getChildren().addAll(tick1, tick2);
        tickBox.getChildren().add(tickContainer);
        this.lowerLeftBox.getChildren().clear();
        this.lowerLeftBox.getChildren().addAll(tickBox, msgLabel);

    }

    public void setRcvMsg (Message message) {

        // schalter zum wechseln der unterschiedlichen boxen
        //  für gesendete und erhaltene Nachrichten
        this.sendMsg = false;
        // text
        this.msgLabel.setText(message.getContent());
        //datum
        dateLabel.setText(Util.timestampToTimeString(chat.getMostRecentMsg().getTimestamp()));
        // layout anpassen an box ohne haken
        this.msgLabel.setTranslateX(0);
        // hakenbox un altes label entfernen
        this.lowerLeftBox.getChildren().clear();
        // nur neues label einfügen
        this.lowerLeftBox.getChildren().addAll(msgLabel);
    }


}
