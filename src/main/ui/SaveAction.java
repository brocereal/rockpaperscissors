package ui;

import model.RpsGame;
import persistence.Writer;

import javax.swing.*;
import java.awt.event.ActionEvent;

//button for saving the game
public class SaveAction extends AbstractAction {
    private GamePanel gp;

    //EFFECTS: sets up the save button
    public SaveAction(GamePanel gp, JComponent parent) {
        this.gp = gp;
        JButton button = new JButton("save");
        parent.add(button);
        button.setFocusable(false);
        button.addActionListener(this);

    }

    //EFFECTS: saves the game when clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        gp.saveGame();
    }
}
