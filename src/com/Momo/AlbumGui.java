package com.Momo;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.util.IllegalFormatException;

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
    private JTable recordTable;
    private JTextField conNum;
    private JTextField price;
    private JButton buyMusicButton;
    private JButton viewConsignorTableButton;
    private JButton salesTableButton;
    public static  ConsignorModel consignorModel;

    AlbumDataModel addToTable;

    public AlbumGui(final AlbumDataModel recordStoreDataModel) {
        //Definition of various Gui variables
        setContentPane(rootpane);
        setTitle("Record Store Management");

        pack();
        addWindowListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 600);
        //setting up the Jtable
        recordTable.setGridColor(Color.blue);

        recordTable.setModel(recordStoreDataModel);


        //Action listener for the add button
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String artistName = nameOfArtist.getText();
                if (artistName == null || artistName.trim().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Please Enter artist name", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                String artistSong = songName.getText();
                if (artistSong == null || artistSong.trim().equals("")) {
                    JOptionPane.showMessageDialog(rootpane, "Please enter song name", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double priceOfSong;
                try {
                    priceOfSong = Double.parseDouble(price.getText());
                    if (priceOfSong < 0.0) {
                        throw new NumberFormatException("Enter a Positive Number");
                    }
                } catch (NumberFormatException ne) {
                    JOptionPane.showMessageDialog(rootpane, "Enter a number greater than zero", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }



                int conNumID;
                try {
                    conNumID = Integer.parseInt(conNum.getText());
                    if (conNumID < 0) throw new NumberFormatException("Please enter a postive number");
                } catch (NumberFormatException ce) {
                    JOptionPane.showMessageDialog(rootpane, "Please Enter consignor ID", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


//                Date todayDate = Date.valueOf(dateEnter);
                boolean insertRow = recordStoreDataModel.insertRows(artistName, artistSong, priceOfSong, conNumID);

                if (!insertRow) {
                    JOptionPane.showMessageDialog(rootpane, "Some field are left empty", "Error", JOptionPane.ERROR_MESSAGE);
                }

                songName.setText("");
                nameOfArtist.setText("");
                conNum.setText("");
                price.setText("");

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
                if (row == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Please choose a cube solver to be deleted");
                }
                boolean todelete = recordStoreDataModel.deleteRow(row);
                if (todelete) {
                    RecordStore.loadTables();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Warning, Error deleting cube_solver");
                }
            }
        });

        viewConsignorTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsignorGUI consignor = new ConsignorGUI(RecordStore.dataConsigModel);
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


