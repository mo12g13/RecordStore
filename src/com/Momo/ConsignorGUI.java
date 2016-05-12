package com.Momo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Momo Johnson on 5/12/2016.
 */
public class ConsignorGUI extends JFrame implements WindowListener {
    private JTable consignorTable;
    private JPanel rootPane;
    private JTextField cosigname;
    private JTextField consigID;
    private JTextField consigPhoneNum;
    private JButton insertButtonButton;
    private JTextField consigName;
    private JButton deleteButtonButton;
    private JButton quitButton;
    private JTextField moneyOwed;


    public ConsignorGUI(final ConsignorModel consignorDataModel){
        setContentPane(rootPane);
        setTitle("Consignor Table");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

        consignorTable.setGridColor(Color.black);
        consignorTable.setModel(consignorDataModel);

        insertButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String conName = consigName.getText();
                if (conName == null || conName.trim().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Please Enter artist name", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }



               int consignorID=0;
                try {
                    consignorID = Integer.parseInt(consigID.getText());
                    if (consignorID < 0.0) {
                        throw new NumberFormatException("Enter a Positive Number");
                    }
                } catch (NumberFormatException ne) {
                    JOptionPane.showMessageDialog(rootPane, "Enter a number greater than zero", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                String consigPhone = consigPhoneNum.getText();
                if (consigPhone == null || consigPhone.trim().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Please Enter artist name", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }

               double moneyDue =0;
                try {
                    moneyDue = Double.parseDouble(moneyOwed.getText());
                    if (consignorID < 0.0) {
                        throw new NumberFormatException("Enter a Positive Number");
                    }
                } catch (NumberFormatException ne) {
                    JOptionPane.showMessageDialog(rootPane, "Enter a number greater than zero", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }



//                Date todayDate = Date.valueOf(dateEnter);
                boolean insertRow = consignorDataModel.insertRows(conName, consignorID, consigPhone, moneyDue);
                if (!insertRow) {
                    JOptionPane.showMessageDialog(rootPane, "Some field are left empty", "Error", JOptionPane.ERROR_MESSAGE);
                }

                consigName.setText("");
                consigID.setText("");
                consigPhoneNum.setText("");
               moneyOwed.setText("");
            }
        });
        deleteButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = consignorTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Please choose a cube solver to be deleted");
                }
                boolean todelete = consignorDataModel.deleteRow(row);
                if (todelete) {
                    RecordStore.loadTables();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Warning, Error deleting cube_solver");
                }
            }
        });
    }

    //An action listener for the delete button



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
