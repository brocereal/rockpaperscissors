package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//button for resuming the game
public class ResumeAction extends AbstractAction {
    private GamePanel gp;

    //EFFECTS: sets up button
    public ResumeAction(GamePanel gp, JComponent parent) {
        this.gp = gp;
        JButton button = new JButton("resume");
        parent.add(button);
        button.setFocusable(false);
        button.addActionListener(this);
    }

    //EFFECTS: resumes game and closes menu panel when clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        gp.setInGame(true);

        Component comp = (Component) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
    }
}
