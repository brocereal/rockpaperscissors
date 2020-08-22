package ui;

import javax.swing.*;
import java.awt.*;

//CREDITS https://stackoverflow.com/questions/45777039/how-to-create-a-game-option-pause-screen-in-swing
//panel with resume, save and load buttons
public class Menu extends JPanel {
    ResumeAction resumebutton;
    SaveAction savebutton;
    LoadAction loadbutton;
    RestartAction restartbutton;

    //EFFECTS: sets up the panel with each button
    public Menu(GamePanel gp, JComponent parent) {
        JLabel pausedLabel = new JLabel("PAUSED");
        pausedLabel.setForeground(Color.BLACK);
        JPanel pausedPanel = new JPanel();
        pausedPanel.setOpaque(false);
        pausedPanel.add(pausedLabel);

        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setLayout(new GridLayout(0, 1, 10, 10));
        add(pausedPanel);
        JPanel resume = new JPanel();
        add(resume);
        resumebutton = new ResumeAction(gp, resume);
        JPanel save = new JPanel();
        add(save);
        savebutton = new SaveAction(gp, save);
        JPanel load = new JPanel();
        add(load);
        loadbutton = new LoadAction(gp, load);
        JPanel restart = new JPanel();
        add(restart);
        restartbutton = new RestartAction(gp, restart);


    }


}
