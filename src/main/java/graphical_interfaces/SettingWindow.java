package graphical_interfaces;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 350;
    private JSlider sliderSize, sliderWin;
    private JRadioButton robotButton, humanButton;
    private JButton btnStart;

    public SettingWindow(GameWindow gameWindow) {
        btnStart = new JButton("Start new game");
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        setLocationRelativeTo(gameWindow);
        setLocation(getX() - WIDTH / 2, getY() - HEIGHT / 2);
        setSize(WIDTH, HEIGHT);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                int mode = robotButton.isSelected() ? 1 : 0;
                gameWindow.startNewGame(mode, sliderSize.getValue(), sliderSize.getValue(), sliderWin.getValue());
            }
        });
        mainPanel.add(getModePanel());
        mainPanel.add(getFieldSize());
        mainPanel.add(getWinLength());
        add(mainPanel);
        add(btnStart, BorderLayout.SOUTH);
    }

    private JPanel getModePanel() {
        JPanel modePanel = new JPanel(new GridLayout(3, 1));
        JLabel textTitle = new JLabel("Выберите режим игры");
        robotButton = new JRadioButton("Человек против человека");
        humanButton = new JRadioButton("Человек против компьютера");
        robotButton.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(robotButton);
        group.add(humanButton);
        modePanel.add(textTitle);
        modePanel.add(robotButton);
        modePanel.add(humanButton);
        return modePanel;
    }

    private JPanel getFieldSize() {
        String textTitle = "Установленный размер поля: ";
        JPanel modePanel = new JPanel(new GridLayout(3, 1));
        JLabel textTitle1 = new JLabel("Выберите размеры поля");
        sliderSize = new JSlider(3, 10, 3);
        JLabel textTitle2 = new JLabel(textTitle + sliderSize.getValue());
        sliderSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textTitle2.setText(textTitle + sliderSize.getValue());
                sliderWin.setMaximum(sliderSize.getValue());
            }
        });
        modePanel.add(textTitle1);
        modePanel.add(textTitle2);
        modePanel.add(sliderSize);
        return modePanel;
    }

    private JPanel getWinLength() {
        String textTitle = "Выберите длину для победы: ";
        JPanel modePanel = new JPanel(new GridLayout(3, 1));
        JLabel textTitle1 = new JLabel("Установленная длина");
        sliderWin = new JSlider(3, 10, 3);
        JLabel textTitle2 = new JLabel(textTitle + sliderWin.getValue());
        sliderWin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textTitle2.setText(textTitle + sliderWin.getValue());
            }
        });
        modePanel.add(textTitle1);
        modePanel.add(textTitle2);
        modePanel.add(sliderWin);
        return modePanel;
    }
}