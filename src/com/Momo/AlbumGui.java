package com.Momo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Arc2D;

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
    private JButton updateMusicButton;
    private JTable recordTable;
    private JTextField consignorName;
    private JTextField consignorPhoneNumber;
    private JTextField price;
    private JButton buyMusicButton;

    AlbumDataModel addToTable;

    public AlbumGui(final AlbumDataModel recordStoreDataModel) {
        //Definition of various Gui variables
        setContentPane(rootpane);
        setTitle("Record Store Management");

        pack();
        addWindowListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(990,550);
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

                String artistSong = songName.getText();
                if(artistSong == null||artistSong.trim().equals("")){
                    JOptionPane.showMessageDialog(rootpane, "Enter a valid text", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double priceOfSong;
                try{
                    priceOfSong = Double.parseDouble(price.getText());
                    if(priceOfSong<0.0){
                        throw new NumberFormatException("Enter a Positive Number");
                    }
                }catch (NumberFormatException ne){
                    JOptionPane.showMessageDialog(rootpane, "Enter a number greater than zero", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String consignorFullName = consignorName.getText();
                        ;
                if(consignorFullName == null || consignorFullName.trim().equals("")){
                    JOptionPane.showMessageDialog(rootpane, "Enter valid text", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                String consignorNumber = consignorPhoneNumber.getText();
                if(consignorNumber == null ||  consignorNumber.trim().equals("")){
                    JOptionPane.showMessageDialog(rootpane, "Enter Valid text", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean insertRow = recordStoreDataModel.insertRows(artistName, artistSong, priceOfSong, consignorFullName, consignorNumber);

                if(!insertRow){
                    JOptionPane.showMessageDialog(rootpane, "Some field are left empty", "Error", JOptionPane.ERROR_MESSAGE);
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


