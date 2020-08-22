package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RestartAction extends AbstractAction {

    private GamePanel gp;

    //EFFECTS: sets up button
    public RestartAction(GamePanel gp, JComponent parent) {
        this.gp = gp;
        JButton button = new JButton("restart");
        parent.add(button);
        button.setFocusable(false);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gp.setInGame(true);
        gp.setGameOver(false);
        gp.setInitialScore();
    }
}
