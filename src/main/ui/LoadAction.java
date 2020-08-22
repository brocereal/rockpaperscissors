package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

//button for loading the game
public class LoadAction extends AbstractAction {
    private GamePanel gp;

    //EFFECTS: sets the button
    public LoadAction(GamePanel gp, JComponent parent) {
        this.gp = gp;
        JButton button = new JButton("load");
        parent.add(button);
        button.setFocusable(false);
        button.addActionListener(this);
    }

    //EFFECTS: loads the game when clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        gp.loadGame();
    }
}
