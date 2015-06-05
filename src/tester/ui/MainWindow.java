package tester.ui;

import ann.NeuronalNetwork;
import graph.GraphViewer;
import tester.ANNTester;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by danfergo on 05-05-2015.
 */
public class MainWindow extends JFrame {

    ANNTester tester;

    public void chooseDataSet(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma-separated values file", "csv", "gif");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(filter);


        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }

        try {
            tester.testNewDataSet(chooser.getSelectedFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void buildMenuBar(){

        MenuBar menuBar = new MenuBar();
            Menu fileMenu = new Menu("File");

                MenuItem openDataSet = new MenuItem("Open data set");
                openDataSet.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        chooseDataSet();
                    }
                });
                fileMenu.add(openDataSet);

            menuBar.add(fileMenu);

        setMenuBar(menuBar);
    }


    public MainWindow(ANNTester tester){
        super("Neuronal Network Tester");
        this.tester = tester;

        //buildMenuBar();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GraphViewer(tester.getANN()));
        setPreferredSize(new Dimension(800, 600));
        setLocationByPlatform(true);
        pack();
        setVisible(true);

    }
}
