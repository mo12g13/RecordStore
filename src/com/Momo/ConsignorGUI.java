package com.Momo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Momo Johnson on 5/12/2016.
 */
public class ConsignorGUI extends JFrame implements WindowListener {
    private JTable consignorTable;
    private JPanel rootPane;


    public ConsignorGUI(final ConsignorModel consignorDataModel){
        setContentPane(rootPane);
        setTitle("Consignor Table");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

        consignorTable.setGridColor(Color.black);
        consignorTable.setModel(consignorDataModel);

    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
