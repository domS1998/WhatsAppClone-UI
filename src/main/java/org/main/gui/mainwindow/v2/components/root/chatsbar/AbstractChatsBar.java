package org.main.gui.mainwindow.v2.components.root.chatsbar;

import javafx.scene.layout.VBox;
import org.main.gui.mainwindow.v2.components.root.Root;

import java.util.Hashtable;


// Abstrakte Klasse für alle Boxen, die an der Stelle der
//  Chatlistenbox angezeigt werden können
abstract public class AbstractChatsBar extends VBox {

    protected Root root;

    // Tabelle für alle Komponenten als Zwischenablage
    //  für wechseln der Anzeige
    protected static Hashtable<String, AbstractChatsBar> chatsBarPanes = new Hashtable<>();

    public static Hashtable<String, AbstractChatsBar> getChatsBarPanes() { return chatsBarPanes; }


    // Komponente aus Tabelle holen für Wechsel
    public static AbstractChatsBar getChatsPane(String id) {
        System.out.println("AbstractChatsBar: getting pane with id '"+id+"'");
        System.out.println("returning "+chatsBarPanes.get(id));
        return chatsBarPanes.get(id);
    }

    public AbstractChatsBar(Root root, String id) {

        // Komponente in Tabelle eintragen
        chatsBarPanes.put(id, this);
        this.root = root;

        // Geteilte Attribute für alle Komponenten an der Stelle
        this.setMinWidth(400);
        this.setMaxWidth(600);


    }




}
