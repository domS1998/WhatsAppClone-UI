package org.main.gui.mainwindow.v2.components.root.chatsbar;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.gui.mainwindow.v2.components.root.chatsbar.chatsview.chatbox.ChatBox;
import org.main.gui.mainwindow.v2.components.root.chatsbar.chatsview.ChatsView;
import org.main.gui.mainwindow.v2.components.root.chatsbar.searchbar.SearchBar;
import org.main.model.Chat;

import java.util.ArrayList;


public class ChatsBar extends AbstractChatsBar {

    private Root root;

    // UI Komponenten
    private SearchBar searchBar;
    private ChatsView chatsView;

    public VBox getSearchBar () { return this.searchBar; }
    public synchronized ChatsView getChatsView () {
        return this.chatsView;
    }
    public void setSearchBar (SearchBar searchBar) { this.searchBar = searchBar; }
    public void setChatsView (ChatsView chatsView) {}

    public ChatsBar (Root root) {

        super(root, "chatsBar");

        this.root = root;
        this.searchBar = new SearchBar(root);
        this.chatsView = new ChatsView(this.root);

        this.setId("chatsBar");
        this.getStyleClass().add("chatsBar");

        this.getChildren().add(searchBar);
        this.getChildren().add(chatsView);
    }

    public void bubbleUp(Chat chat){

        ChatBox chatPane = this.chatsView.getChatBox(chat);

        // Wenn Chat bereits oben keine Änderung
        if (this.getChatsView().getVbox().getChildren().indexOf(chatPane) == 0 ) {
            return;
        }
        // ChatPane oben einfügen und restliche Panes nach unten verschieben
        ArrayList<Node> nodes = new ArrayList();
        nodes.add(chatPane);

        for (int i = 0; i < this.getChatsView().getVbox().getChildren().size(); i++) {
            if ( ! this.getChatsView().getVbox().getChildren().get(i).equals(chatPane)) {
                nodes.add(this.getChatsView().getVbox().getChildren().get(i));
            }
        }
        this.getChatsView().getVbox().getChildren().clear();
        this.getChatsView().getVbox().getChildren().addAll(nodes);
    }




}
