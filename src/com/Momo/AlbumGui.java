package com.Momo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Momo Johnson on 5/4/2016.
 */
public class AlbumGui extends JFrame implements WindowListener {
    private JTextField nameOfArtist;
    private JTextField songName;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton quitButton;
    private JPanel rootpane;
    private JButton deleteButton1;
    private JTable recordTable;

    AlbumDataModel addToTable;

    public AlbumGui(final AlbumDataModel recordStoreDataModel) {
        //Definition of various Gui variables
        setContentPane(rootpane);
        setTitle("Rubic Cube Solver");
        pack();
        addWindowListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        //setting up the Jtable
        recordTable.setGridColor(Color.blue);
        recordTable.setModel(recordStoreDataModel);


        //Action listener for the add button
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String artistName = nameOfArtist.getText();
                if (artistName == null || artistName.trim().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Please enter valid text", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }
            }
        });

        //An action listner for the quit button
        quitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RecordStore.shutDown();
                System.exit(0);
            }
        });

        //An action listener for the delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = recordTable.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(rootPane, "Please choose a cube solver to be deleted");
                }
                boolean todelete = recordStoreDataModel.deleteRow(row);
                if(todelete){
                    RecordStore.loadRubikCubeData();
                }else {
                    JOptionPane.showMessageDialog(rootPane, "Warning, Error deleting cube_solver");
                }
            }
        });
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    //A method that shuts the database securely
    public void windowClosing(WindowEvent e) {
        System.out.println("Database Closing");
        RecordStore.shutDown();

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
}


