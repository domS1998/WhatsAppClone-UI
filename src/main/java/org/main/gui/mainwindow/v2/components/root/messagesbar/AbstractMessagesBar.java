package org.main.gui.mainwindow.v2.components.root.messagesbar;

import javafx.scene.layout.VBox;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.gui.mainwindow.v2.components.root.chatsbar.AbstractChatsBar;

import java.util.Hashtable;

abstract public class AbstractMessagesBar extends VBox {

    protected Root root;

    // Tabelle für alle Komponenten als Zwischenablage
    //  für wechseln der Anzeige
    protected static Hashtable<String, AbstractMessagesBar> messagesBarPanes = new Hashtable<>();

    public static Hashtable<String, AbstractMessagesBar> getMessagesBarPanes() { return messagesBarPanes; }


    // Komponente aus Tabelle holen für Wechsel
    public static AbstractMessagesBar getMessagesPane(String id) {
        return messagesBarPanes.get(id);
    }

    public AbstractMessagesBar(Root root, String id) {



        // Komponente in Tabelle eintragen
        messagesBarPanes.put(id, this);
        this.root = root;

        // Geteilte Attribute für alle Komponenten an der Stelle
        this.setMinWidth(400);
        this.setMinHeight(525);
        this.setPrefWidth(1920-60-400);
        this.setPrefHeight(870);
        this.setMaxWidth(1920-60-400);
        this.setMaxHeight(1080);
    }

}
