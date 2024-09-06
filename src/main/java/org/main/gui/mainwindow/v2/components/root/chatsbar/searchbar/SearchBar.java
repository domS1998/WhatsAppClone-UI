package org.main.gui.mainwindow.v2.components.root.chatsbar.searchbar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.Buttons.svgbutton.NewChatButton;
import org.main.gui.mainwindow.v2.components.Buttons.svgbutton.ThreeDotButton;
import org.main.gui.mainwindow.v2.components.root.Root;

public class SearchBar extends VBox {

    private Root root;

    private HBox upperBox  = new HBox();
    private HBox middleBox = new HBox();
    private HBox lowerBox  = new HBox();

    public HBox getUpperBox() { return upperBox; }
    public HBox getMiddleBox() { return middleBox; }
    public HBox getLowerBox() { return lowerBox; }

    public SearchBar(Root root) {

        this.root = root;
        this.setId("chatsBar_searchBar");
        this.getStyleClass().add("chatsBar_searchBar");
        this.getChildren().addAll(upperBox, middleBox, lowerBox);


            // Zeile 1
            this.upperBox.setStyle("-fx-background-color: white;");


            // Inhalt
            Label label = new Label("Chats");
            label.setFont(new Font("System", 22));
            HBox.setMargin(label, new Insets(10, 10, 10, 20));
            label.setMinWidth(100);


            // Box für linksbündige Panes mit Buttons
            HBox box = new HBox(new NewChatButton(root), new ThreeDotButton(this.root));
            box.setAlignment(Pos.CENTER_RIGHT);
            box.setMinWidth(80);
            box.setPrefWidth(10000);

            this.upperBox.getChildren().addAll(label, box);






            // Inhalt Zeile 2
            TextField textField = new TextField();
            this.middleBox.getChildren().add(textField);
            textField.setId("chatsBar_searchbar_textField");
            textField.getStyleClass().add("chatsBar_searchbar_textField");
            HBox.setMargin(textField, new Insets(10, 10, 10, 10));
            textField.setMinWidth(380);
            textField.setPrefWidth(10000);
            textField.setFocusTraversable(false);
            textField.setPromptText("Search");




            // Inhalt Zeile 3
            Label label_1 = new Label("All");
            label_1.setId("chatsBar_searchbar_label_1");
            label_1.getStyleClass().add("chatsBar_searchbar_label_1");
            HBox.setMargin(label_1, new Insets(10, 10, 10, 10));
            label_1.setPadding(new Insets(5, 5, 5, 5));

            Label label_2 = new Label("Unread");
            label_2.setId("chatsBar_searchbar_label_2");
            label_2.getStyleClass().add("chatsBar_searchbar_label_2");
            HBox.setMargin(label_2, new Insets(10, 10, 10, 10));
            label_2.setPadding(new Insets(5, 5, 5, 5));

            Label label_3 = new Label("Groups");
            label_3.setId("chatsBar_searchbar_label_3");
            label_3.getStyleClass().add("chatsBar_searchbar_label_3");
            HBox.setMargin(label_3, new Insets(10, 10, 10, 10));
            label_3.setPadding(new Insets(5, 5, 5, 5));

            this.lowerBox.getChildren().addAll(label_1, label_2, label_3);



    }
}
