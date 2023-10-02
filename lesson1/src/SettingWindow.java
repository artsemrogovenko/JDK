import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
Задача: На лекции был написан фрейм, содержащий одну кнопку – 
начать игру и расположением самого окна настроек автоматически, относительно игрового окна.
Добавить на экран компоновщик-сетку с одним столбцом и добавить над существующей кнопкой следующие компоненты 
в заданном порядке: 
JLabel с заголовком «Выберите режим игры», 
сгруппированные в ButtonGroup переключаемые JRadioButton с указанием режимов «Человек против компьютера» и «Человек против человека», 
JLabel с заголовком «Выберите размеры поля», 
JLabel с заголовком «Установленный размер поля:», 
JSlider со значениями 3..10, 
JLabel с заголовком «Выберите длину для победы», 
JLabel с заголовком «Установленная длина:», 
JSlider со значениями 3..10.
*/
public class SettingWindow extends JFrame {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 350;

    JButton btnStart;
    ButtonGroup bg=new ButtonGroup();

    JPanel mainPanel=new JPanel(new GridLayout(10,1));
    String[] theConst = new String[] { "Выберите режим игры", "Человек против компьютера", "Человек против человека",
            "Выберите размеры поля", "Установленный размер поля:", "Выберите длину для победы",
            "Установленная длина:" };

    int minvalue=3;
    int winCombination=minvalue;

    JLabel selectMode = new JLabel(theConst[0]);
    JRadioButton humanVsRobot = new JRadioButton(theConst[1]);
    JRadioButton humanVshuman = new JRadioButton(theConst[2]);
    JLabel dialogSetSize = new JLabel(theConst[3]);
    JLabel currentArea = new JLabel(theConst[4] + String.valueOf(minvalue));
    JSlider sliderGameArea = new JSlider(3, 10,minvalue);
    JLabel winSize = new JLabel(theConst[5]);
    JLabel currentWinSize = new JLabel(theConst[6] + sliderGameArea.getValue());
    JSlider sliderWinsize = new JSlider(3, 10, minvalue);
 



    SettingWindow(GameWindow gameWindow) {
        bg.add(humanVsRobot);
        bg.add(humanVshuman);

        mainPanel.add(selectMode);
        mainPanel.add(humanVshuman);
        mainPanel.add(humanVsRobot);
        mainPanel.add(dialogSetSize);
        mainPanel.add(currentArea);
        mainPanel.add(sliderGameArea);
        mainPanel.add(winSize);
        mainPanel.add(currentWinSize);
        mainPanel.add(sliderWinsize);

        btnStart = new JButton("Start new game");

        int[] centerLocation = new int[] { (gameWindow.getX() + gameWindow.getWidth() / 2) - (WIDTH / 2),
                (gameWindow.getY() + gameWindow.getHeight() / 2) - (HEIGHT / 2) };


        setLocationRelativeTo(gameWindow);
        /*
         * Задача: Добавить центрирование окна настроек относительно главного
         * (родительского)
         * окна. То есть, в центре родительского окна должен быть не левый верхний угол
         * окна
         * настроек (как это сделано сейчас), а также его центр.
         */
        setLocation(centerLocation[0], centerLocation[1]);
        setSize(WIDTH, HEIGHT);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                //gameWindow.startNewGame(0, 3, 3, 3);
                gameWindow.startNewGame(0, sliderGameArea.getValue(), sliderGameArea.getValue(), winCombination);
            }
        });
    /* 
    Задача: Добавить компонентам интерактивности, а именно, 
    при перемещении ползунка слайдера в соответствующих лейблах должны появляться текущие значения слайдеров.
    Для этого необходимо добавить к слайдеру слушателя изменений (как это было сделано для действия кнопки).
    */
    sliderGameArea.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            currentArea.setText(theConst[4] + sliderGameArea.getValue());
            currentWinSize.setText(theConst[6] + sliderGameArea.getValue());
            winCombination=sliderGameArea.getValue();
        }
    });

    sliderWinsize.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            if (sliderGameArea.getValue() >= sliderWinsize.getValue()) {
                currentWinSize.setText(theConst[6] + sliderWinsize.getValue());
                winCombination=sliderGameArea.getValue();
            }

        }
    });

        add(mainPanel);   
        add(btnStart, BorderLayout.SOUTH);
    }
}
