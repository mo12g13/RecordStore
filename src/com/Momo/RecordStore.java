package com.Momo;

import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/** Inventory for second-hand record store, selling CDs and LPs */

public class RecordStore {
    //Definition of the localhost server
    private static String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    //The database name to be created in Mysql
    private static final String DB_NAME = "cubesdatabase";
    //User name and password for the user on the database
    private static final String USER = "root";
    private static final String  PASS = "Flower1987!"; //todo

    //
    static Statement statementAlbum = null;
    static Statement statementConsig = null;
    static Connection conn = null;
    static ResultSet resultSetalAlbum = null;
    static ResultSet resultSetConsig = null;


    public final static String INVENTRY = "ALBUM_TABLE";   //The table name

    public final static String PK_COLUMN = "id";//Wanted this to be a primary key but have problem getting it to work
    //A primary key is needed to alDlow updates to the database on modifications to ResultSet
    public final static String ARTIST_NAME = "artist"; //Variable of the Cube solver which define the cube solver
    public final static String TITLE= "song";  //The variable that holds the time in seconds in the database;

    private static AlbumDataModel dataModel;  //Album data model
    public final static String PRICE = "price";//price for the table
    //public final static String NAME = " NameOfConsignor ";
    public final static String CONID = "consID";//concign id
    public final static String DATE_ENTER = " dateConsigned ";//Date to be entered. Will be used later

    public static final String CONSIG_TALBLE = "Consig_Table";//Consign table

    public final static String PHONE_NUM = "PhoneNum";

    public final static String CONSIG_NAME = "Name";
    public final static String  MONEYOWED = "MoneyOwed";
    public static ConsignorModel dataConsigModel;


    public static void main(String[] args) {










        //Create some example LPs and add them to an inventory list
        ArrayList<LP> lpInventory = new ArrayList<LP>();



        if (!setUP()) {
            System.exit(-1);

        }
        if (!loadTables()) {
            System.exit(-1);

        }

        //Initailization of the data form which lunch the gui

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());

                    break;
                }
            }
        } catch (ClassNotFoundException e) {

        } catch (InstantiationException e) {

        } catch (IllegalAccessException b) {

        } catch (UnsupportedLookAndFeelException b) {

        }
        AlbumGui jtable = new AlbumGui(dataModel);


    }




    //A method that either create or recreate the resultset
    public static boolean loadTables() {
        try {
            if (resultSetalAlbum != null) {
                resultSetalAlbum.close();
            }

            if(resultSetConsig !=null){
                resultSetConsig.close();
            }
            String loadData = "SELECT * FROM " + INVENTRY ;
            resultSetalAlbum = statementAlbum.executeQuery(loadData);
            if (dataModel == null) {
                dataModel = new AlbumDataModel(resultSetalAlbum);

            } else {
                dataModel.updateResultsSet(resultSetalAlbum);
            }


            String loadConsigData = " SELECT * FROM "+ CONSIG_TALBLE;
            resultSetConsig = statementConsig.executeQuery(loadConsigData);
            if(dataConsigModel == null){
                dataConsigModel = new ConsignorModel(resultSetConsig);
            }
            else {
                dataConsigModel.updateResultsSet(resultSetConsig);
            }


            return true;
            //Catching of any error and displaying that particular error
        } catch (Exception e) {
            System.out.println("Error loading or reloading data");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }


    //A method that setup the database to be lunch and connected
    public static boolean setUP() {
        try {
            try {
                String Driver = "com.mysql.jdbc.Driver";
                Class.forName(Driver);
            } catch (ClassNotFoundException nfe) {
                System.out.println("No Database driver were found");
                return false;
            }
            conn = DriverManager.getConnection(DB_CONNECTION_URL + DB_NAME, USER, PASS);
            statementAlbum = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statementConsig = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);


            //If result exit run this and create a table
            if (!album_table_exist()) {
                PreparedStatement psInsert = null;
                // Creation of the table
                String createTable = "CREATE TABLE if not exists " + INVENTRY + " (" + PK_COLUMN + " int not null auto_increment, "
                        + ARTIST_NAME + " VARCHAR(50), " + TITLE + " VARCHAR(50), "+ PRICE + " DOUBLE,   "+ CONID
                        + " int ,  Primary key (" + PK_COLUMN + "))";
                System.out.println(createTable);


                statementAlbum.executeUpdate(createTable);


                String addDataSql = " INSERT INTO " +  INVENTRY  + "(" + ARTIST_NAME + "," + TITLE
                        + ","+ PRICE +", "+ CONID +") VALUES  (?, ?, ?, ?)";
                System.out.println(addDataSql);
                psInsert = conn.prepareStatement(addDataSql);
                //Setting of prepared statement varioable




                LP testLP1 = new LP("Michael Jackson", "Thriller",  9.99, 5);
                LP testLP2 = new LP("Replacements", "Hootenanny",  7.99, 3);
                //Hello


                CD testCD1 = new CD("Lady Gaga", "The Fame Monster",  6.99, 7);
                CD testCD2 = new CD("Bob Dylan", "Basement Tapes", 9.99, 3);



                String artistNameCD1 = testCD1.getArtist();

                psInsert.setString(1, testCD1.getArtist());
                psInsert.setString(2, testCD1.getTitle());
                psInsert.setDouble(3, testCD1.getPrice());
                psInsert.setInt(4, testCD1.getConID());
//                psInsert.setDate(5, java.sql.Date.valueOf("2014-12-3"));
                psInsert.executeUpdate();



//                psInsert.setString(1, testCD2.getArtist());
//                psInsert.setString(2, testCD2.getTitle());
//                psInsert.setDouble(3, testCD2.getPrice());
//                psInsert.setInt(4, testCD2.getConID());
//                psInsert.setDate(5, date);
//                psInsert.executeUpdate();

//                psInsert.setString(1, testLP1.getArtist());
//                psInsert.setString(2, testLP1.getTitle());
//                psInsert.setDouble(3, testLP1.getPrice());
//                psInsert.setString(4, "Samuel Johnson");
//                psInsert.setString(5, "612-555-4444");
//                psInsert.executeUpdate();
//
//                psInsert.setString(1, testLP2.getArtist());
//                psInsert.setString(2, testLP2.getTitle());
//                psInsert.setDouble(3, testLP2.getPrice());
//                psInsert.setString(4, "James Frank");
//                psInsert.setString(5, "651-888-8585");
//                psInsert.executeUpdate();
//
//                psInsert.setString(1, "Momo Johnson");
//                psInsert.setString(2, "All of you");
//                psInsert.setDouble(3, 3.3);
//                psInsert.setString(4, "null");
//                psInsert.setString(5, "215-445-5858");
//                psInsert.executeUpdate();

                psInsert.close();


            }

            if(!consig_table_exist()){
                PreparedStatement congInsert;

                String createConsignTable = "CREATE TABLE if not exists " + CONSIG_TALBLE
                        + " (" + PK_COLUMN + " int not null auto_increment, "
                        + CONSIG_NAME + " VARCHAR(50), " + CONID + " int, "+ PHONE_NUM + " VARCHAR(50),   "+ MONEYOWED + " double ,  Primary key (" + PK_COLUMN + "))";
                System.out.println(createConsignTable);
                statementAlbum.executeUpdate(createConsignTable);

                Consignor consignor1 = new Consignor("James Gray", 4, "61288478", 45.0);





                String addDataSql = " INSERT INTO " +  CONSIG_TALBLE  + "(" + CONSIG_NAME + ","
                        + CONID + ","+ PHONE_NUM +", "+ MONEYOWED +") VALUES  (?, ?, ?, ?)";
                System.out.println(addDataSql);
                congInsert = conn.prepareStatement(addDataSql);

                congInsert.setString(1, consignor1.getConsignorName());
                congInsert.setInt(2, consignor1.getConID());
                congInsert.setString(3, consignor1.getConsignorPhone());
                congInsert.setDouble(4, consignor1.getMoneyOwed());
                congInsert.executeUpdate();
                congInsert.close();


            }
            return true;

            //Catch and any and display the error in the command line
        } catch (SQLException se) {

            System.out.println(se);
            se.printStackTrace();
            return false;
        }

    }
    //A method that checks if result exist
    private static boolean album_table_exist() throws SQLException{
        String checkQuery = "SHOW TABLES LIKE '"+ INVENTRY +"'";
        ResultSet tableRs = statementAlbum.executeQuery(checkQuery);
        if(tableRs.next()){
            return true;
        }
        return false;
    }

    private static boolean consig_table_exist()throws SQLException{
        String checkQuery = "SHOW TABLES LIKE '"+ CONSIG_TALBLE+"'";
        ResultSet tableCt = statementConsig.executeQuery(checkQuery);
        if(tableCt.next()){
            return true;
        }
        else {
            return false;
        }
    }

    //A method that shut down th database successfully by closing all of the various variable use.
    public static void shutDown() {
        try {
            if (resultSetalAlbum != null) {
                resultSetalAlbum.close();

            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            if (statementAlbum != null) {
                statementAlbum.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void shutDownCosig() {
        try {
            if (resultSetConsig != null) {
                resultSetConsig.close();

            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            if (resultSetConsig != null) {
                resultSetConsig.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}




