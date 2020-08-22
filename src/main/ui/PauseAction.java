package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//CREDITS https://stackoverflow.com/questions/45777039/how-to-create-a-game-option-pause-screen-in-swing
//buttton for pausing the game
public class PauseAction extends AbstractAction {
    private GamePanel gp;
    private JComponent parent;
    private Menu menu;

    //EFFECTS: sets up the button in the panel
    public PauseAction(GamePanel gp, JComponent parent) {
        this.gp = gp;
        this.parent = parent;
        menu = new Menu(gp, parent);
        JButton button = new JButton("menu");
        parent.add(button);
        button.setFocusable(false);
        button.addActionListener(this);
    }

    //EFFECTS: pauses game when clicked and opens menu panel
    @Override
    public void actionPerformed(ActionEvent e) {
        gp.setInGame(false);

        RootPaneContainer win = (RootPaneContainer) SwingUtilities.getWindowAncestor(parent);
        JDialog dialog = new JDialog((Window)win, "", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().add(menu);
        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo((Window)win);
        dialog.setVisible(true);
        dialog.setFocusable(false);
    }
}
