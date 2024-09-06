package org.main.gui.mainwindow.v2.components.Buttons.svgbutton;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import org.main.fileio.cache.CacheUserstate;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.gui.mainwindow.v2.components.root.chatsbar.AbstractChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.ChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.chatsview.chatbox.ChatBox;
import org.main.gui.mainwindow.v2.components.root.messagesbar.AbstractMessagesBar;
import org.main.gui.mainwindow.v2.components.root.messagesbar.MessagesBar;
import org.main.model.Message;
import org.main.net.api.transaction.NewMessageTransaction;
import org.main.net.api.transaction.messages.MessageType;
import org.main.net.api.transaction.messages.result.status.TransactionStatusMessage;
import org.main.sound.SoundPlayer;

import java.io.IOException;

public class SendButton extends SvgButton {

    private TextArea messageTextArea = new TextArea();

    public SendButton(Root root, TextArea messagesTextArea) {

        super(root);

        this.messageTextArea = messagesTextArea;

        SVGPath svg = new SVGPath();
        svg.setContent("M0 0 C2.01633612 0.90277408 4.03294155 1.80494688 6.04980469 2.70654297 C10.1183637 4.52603412 14.18570483 6.34820234 18.25195312 8.17285156 C25.52709414 11.43644086 32.81083713 14.68055952 40.09744263 17.91845703 C41.53559942 18.55753815 42.97364831 19.19686213 44.41159058 19.83642578 C57.49129741 25.65380162 70.5969252 31.41148392 83.70721436 37.1595459 C97.45340486 43.18658124 111.18123457 49.25277547 124.89013672 55.36425781 C130.23741545 57.74698305 135.58764061 60.12307623 140.9375 62.5 C143.17579487 63.49476101 145.41407573 64.48955357 147.65234375 65.484375 C149.40780518 66.26458008 149.40780518 66.26458008 151.19873047 67.06054688 C153.88525391 68.25455729 156.57177734 69.44856771 159.25830078 70.64257812 C160.36065315 71.13250999 160.36065315 71.13250999 161.48527527 71.63233948 C163.00343826 72.30708199 164.52159618 72.9818359 166.03974915 73.65660095 C169.91318117 75.37818611 173.78670106 77.09957302 177.66040039 78.82055664 C184.52290212 81.86960502 191.38467609 84.92021072 198.2421875 87.98046875 C209.75813258 93.1164097 221.29820586 98.19050752 232.875 103.1875 C246.59488292 109.11109106 260.25294438 115.16315095 273.86279297 121.33544922 C276.68365434 122.60736134 279.51316455 123.85651466 282.34765625 125.09765625 C296.84404516 131.53180999 296.84404516 131.53180999 301 137.75 C302.55899806 142.42699417 302.78215988 146.81760499 300.9375 151.375 C296.04072345 157.37750029 290.27641246 160.95622174 283.27734375 164.08984375 C282.45096909 164.47027328 281.62459442 164.85070282 280.7731781 165.24266052 C278.18845754 166.4264198 275.59599299 167.591192 273 168.75 C272.19830383 169.10910461 271.39660767 169.46820923 270.57061768 169.8381958 C265.75899129 171.98734523 260.93378077 174.10429367 256.10180664 176.20724487 C251.64981063 178.14501455 247.20214079 180.09267761 242.75390625 182.0390625 C241.85314362 182.43292648 240.95238098 182.82679047 240.02432251 183.23258972 C231.00001247 187.18121419 221.99800206 191.17936519 213 195.1875 C210.04047569 196.50545802 207.0808576 197.823205 204.12109375 199.140625 C203.06992874 199.60850937 203.06992874 199.60850937 201.99752808 200.08584595 C195.69451298 202.8894903 189.37991365 205.66646308 183.0625 208.4375 C171.57166443 213.47952325 160.12304761 218.6140506 148.67578125 223.75390625 C128.91103704 232.6282681 109.07165236 241.33337833 89.23046875 250.03515625 C69.1020544 258.86370617 48.99072438 267.72735869 28.91668701 276.67901611 C-4.88826154 291.75312355 -4.88826154 291.75312355 -17.3125 297.125 C-18.11671387 297.4758667 -18.92092773 297.8267334 -19.74951172 298.18823242 C-27.02978123 301.31072969 -33.99012343 303.75498451 -42 302.75 C-46.54054054 300.66891892 -46.54054054 300.66891892 -48 297.75 C-49.64603902 284.62871755 -42.58192683 273.62981733 -36.8125 262.3125 C-32.70784706 254.19203438 -28.6795344 246.04877097 -24.8125 237.8125 C-20.98999502 229.70325224 -16.92740267 221.75404954 -12.703125 213.84765625 C-10.69456916 210.08349051 -8.7364521 206.29625804 -6.796875 202.49609375 C9.49789104 170.62009689 9.49789104 170.62009689 17 166.75 C27.55715593 163.84021865 38.64779402 162.77567051 49.49609375 161.48046875 C51.16153123 161.2772723 52.82690451 161.07354898 54.49221802 160.86933899 C57.99988814 160.44023803 61.50795075 160.01457982 65.01638794 159.59179688 C70.47970884 158.93331206 75.94195495 158.26620076 81.40405273 157.59765625 C88.62025979 156.71507969 95.83678886 155.83520685 103.05371094 154.95849609 C114.8900404 153.52055244 126.7262723 152.08202115 138.55960083 150.61953735 C141.48578467 150.2594406 144.41264733 149.90530099 147.33959961 149.55151367 C149.08190805 149.33674644 150.82417967 149.12168029 152.56640625 148.90625 C153.31785919 148.81708817 154.06931213 148.72792633 154.84353638 148.63606262 C159.68447146 148.02868016 164.28522551 147.00948282 169 145.75 C169 145.09 169 144.43 169 143.75 C156.93686412 141.07881621 144.84103592 139.47587423 132.58203125 138.0078125 C130.51487705 137.7556206 128.44779579 137.50283016 126.38078308 137.2494812 C120.95540397 136.58571691 115.52928346 135.92824609 110.10290527 135.27270508 C58.7271802 129.06301811 58.7271802 129.06301811 38.71335411 126.36672592 C37.18784488 126.16213112 35.66192383 125.96057877 34.13556862 125.76239395 C15.6421677 123.35136082 15.6421677 123.35136082 10.92919922 118.71118164 C10.43436035 117.93121826 9.93952148 117.15125488 9.4296875 116.34765625 C8.86088867 115.45836426 8.29208984 114.56907227 7.70605469 113.65283203 C4.71466298 108.56301415 1.87533729 103.41132608 -0.875 98.1875 C-1.48432373 97.04144287 -2.09364746 95.89538574 -2.72143555 94.71459961 C-3.93428337 92.43190349 -5.14456587 90.14784217 -6.35205078 87.86230469 C-7.75289199 85.21668636 -9.1660027 82.57859162 -10.58984375 79.9453125 C-15.29812217 71.21158192 -19.61925719 62.33559543 -23.828125 53.3515625 C-25.76558938 49.22548095 -27.76577649 45.13534774 -29.8125 41.0625 C-30.12405029 40.44189697 -30.43560059 39.82129395 -30.7565918 39.18188477 C-32.32808824 36.05789097 -33.90539962 32.93686765 -35.48486328 29.81689453 C-37.11835496 26.58799136 -38.74673122 23.35653954 -40.375 20.125 C-40.89940674 19.09286377 -41.42381348 18.06072754 -41.96411133 16.99731445 C-42.45806396 16.01593506 -42.9520166 15.03455566 -43.4609375 14.0234375 C-43.90228027 13.15122559 -44.34362305 12.27901367 -44.79833984 11.38037109 C-46.97478422 6.61624844 -48.29031617 2.37517198 -48.3125 -2.875 C-48.32925781 -3.79539062 -48.34601562 -4.71578125 -48.36328125 -5.6640625 C-47.93480245 -8.71409436 -47.12632101 -10.04898387 -45 -12.25 C-30.32906843 -17.14031052 -13.01630142 -5.86154671 0 0 Z ");


        Region region = new Region();
        region.setShape(svg);

        region.setStyle("-fx-background-color: grey");
        region.setMinWidth(33);
        region.setMinHeight(30);
        region.setTranslateX(15);
        region.setTranslateY(15);


        this.getChildren().addAll(region);
        this.setMinWidth(30);
        this.setMinHeight(30);
        this.setPrefWidth(30);
        this.setPrefHeight(30);



        HBox.setMargin(this, new Insets(0, 20, 0, 0));


        // Klick Listener Senden Button
        this.setOnMouseClicked((e)->{

            // Abbruch, wenn keine Verbindung zu Server
            if ( ! MainWindowController.isLoggedIn() ){
                System.out.println("Not connected with server, can't send Message");
                SoundPlayer.getInstance().play("no-connection");
                return;
            }

            // get text in textfield
            String messageText = this.messageTextArea.getText();

            // keine leere Nachricht hinzufügen
            if (messageText.equals("")) {return;}

            // ansonsten Nachricht hinzufügen und senden
            String messageId = MainWindowController.getCurChat().addMessage(messageText, MainWindowController.getUser().getUsername());

            // neuste nachricht
            Message message = MainWindowController.getCurChat().getMessages().get(MainWindowController.getCurChat().getMessages().size() - 1);

            // neueste Nachricht zu Chat label
            ChatBox pane = ((ChatsBar)AbstractChatsBar.getChatsPane("chatsBar")).getChatsView().getChatBox(MainWindowController.getCurChat());
//            pane.getMsgLabel().setText("You: " + message.getContent());
            pane.setSendMsg(message);

            // API Nachricht senden
            System.out.println(" click: sending " + message);
            NewMessageTransaction sendMsg = new NewMessageTransaction(MainWindowController.getUser().getUsername(), MainWindowController.getUser().getPassword(), message);
            sendMsg.start();

            Thread thread = new Thread(()->{
                TransactionStatusMessage response = sendMsg.getResult();
                if (response.getMESSAGE_TYPE() == MessageType.TRANSACTION_COMPLETE) {

                    ((MessagesBar)this.root.getMessagesBar()).getMessagesView().getLastSendMsgPane().setDoubleGreyTick();
                    message.setReceived(true);
                    System.out.println("SendButton: double grey ticking chat box");
//                    if () {}
                    pane.setDoubleGreyTick();

                    try {
                        CacheUserstate.getInstance().update(MainWindowController.getUser());
                    }
                    catch (IOException | ClassNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                else {
                    // ein Haken nach erfolgreichem Senden in Nachrichtenbox
                    ((MessagesBar)this.root.getMessagesBar()).getMessagesView().getLastSendMsgPane().setSingleGreyTick();
                    // ein Haken nach erfolgreichem Senden in Chatbox
                    pane.setSingleGreyTick();
                }

            });
            thread.start();

            // Cache updaten
            try {
                CacheUserstate.getInstance().update(MainWindowController.getUser());}
            catch (IOException | ClassNotFoundException  ex) {System.out.println(ex);}
            // Text in Textfeld zurücksetzen
            this.messageTextArea.setText("");
            // neue Nachricht in VBox einfügen
            Message lastMessage = MainWindowController.getCurChat().getMessages().get(MainWindowController.getCurChat().getMessages().size()-1);
            ((MessagesBar) AbstractMessagesBar.getMessagesBarPanes().get(MainWindowController.getCurChat().getOtherUser(MainWindowController.getUser().getUsername()))).getMessagesView().addMessage(lastMessage);
            SoundPlayer.getInstance().play("send");

            // runter scrollen im Scrollpane zu neue eingefügter Nachricht
            scrollDown();

        });
    }

    void scrollDown () {
        ((MessagesBar)this.root.getMessagesBar()).getMessagesView().getScrollPane().applyCss();
        ((MessagesBar)this.root.getMessagesBar()).getMessagesView().getScrollPane().layout();
        ((MessagesBar)this.root.getMessagesBar()).getMessagesView().getScrollPane().setVvalue(1.0);
    }
}
