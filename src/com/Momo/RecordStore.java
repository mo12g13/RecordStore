package com.Momo;

import java.sql.*;
import java.util.ArrayList;

/** Inventory for second-hand record store, selling CDs and LPs */

public class RecordStore {
    //Definition of the localhost server
    private static String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    //The database name to be created in Mysql
    private static final String DB_NAME = "cubesdatabase";
    //User name and password for the user on the database
    private static final String USER = "root";
    private static final String  PASS = "password"; //todo

    //
    static Statement statement = null;
    static Connection conn = null;
    static ResultSet rs = null;


    public final static String RUBIC = "ALBUM_TABLE";   //The table name

    public final static String PK_COLUMN = "id";//Wanted this to be a primary key but have problem getting it to work
    //A primary key is needed to allow updates to the database on modifications to ResultSet
    public final static String ARTIST_NAME = "Artist"; //Variable of the Cube solver which define the cube solver
    public final static String ARTIST_SONG= "Song";  //The variable that holds the time in seconds in the database;
    private static AlbumDataModel dataModel;  //The model varible of the data model
    private final static String PRICE = "Price";


    public static void main(String[] args) {
       // A setup that creates the database
                //Create some example CDs and add them to an inventory list
        ArrayList<CD> cdInventory = new ArrayList<CD>();

        CD testCD1 = new CD("Lady Gaga", "The Fame Monster", 6.99);
        CD testCD2 = new CD("Bob Dylan", "Basement Tapes", 9.99);
        String artistNameCD1 = testCD1.getArtist();
        String artisNameCD2 = testCD2.getArtist();
        String titleCD1 = testCD1.getTitle();
        String titelCD2 = testCD2.getTitle();
        double priceCD1 = testCD1.getPrice();
        double priceCD2 = testCD2.getPrice();



//        cdInventory.add(testCD1);
//        cdInventory.add(testCD2);
//

        //Create some example LPs and add them to an inventory list
        ArrayList<LP> lpInventory = new ArrayList<LP>();

        LP testLP1 = new LP("Michael Jackson", "Thriller", 4, 9.99);
        LP testLP2 = new LP("Replacements", "Hootenanny", 3, 7.99);

//        lpInventory.add(testLP1);
//        lpInventory.add(testLP2);


        String titleLP1 = testLP1.getTitle();
        double priceLP1 = testLP1.getPrice();

        String artistNameLP2 = testLP2.getArtist();
        String titleLP2 = testLP2.getTitle();
        double priceLP2 = testLP2.getPrice();


        if (!setUP()) {
            System.exit(-1);

        }
        if (!loadRubikCubeData()) {
            System.exit(-1);
        }


        //Initailization of the data form which lunch the gui
        AlbumGui jtable = new AlbumGui(dataModel);

    }
    //A method that either create or recreate the resultset
    public static boolean loadRubikCubeData() {
        try {
            if (rs != null) {
                rs.close();
            }
            String loadData = "SELECT * FROM " + RUBIC ;
            rs = statement.executeQuery(loadData);
            if (dataModel == null) {
                dataModel = new AlbumDataModel(rs);

            } else {
                dataModel.updateResultsSet(rs);
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
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //If result exit run this and create a table
            if (!rubikTableexist()) {
                PreparedStatement psInsert = null;
                // Creation of the table
                String createTable = "CREATE TABLE if not exists " + RUBIC + " (" + PK_COLUMN + " int not null auto_increment, " + ARTIST_NAME + " VARCHAR(50), " + ARTIST_SONG + " VARCHAR(50), "+PRICE+ " DOUBLE, Primary key (" + PK_COLUMN + "))";
                System.out.println(createTable);


                statement.executeUpdate(createTable);
                CD testCD3 = new CD("Lady Gaga", "The Fame Monster", 6.99);
                String artistNameLP1 = testCD3.getArtist();
                String artistName4 = testCD3.getArtist();
                //Use of the prepared statement
                //String addDataSql = "INSERT INTO " +  RUBIC  + "(" + ARTIST_NAME + "," + ARTIST_SONG + ","+PRICE +") VALUES  (?, ?, ?)";
                String addDataSql = "INSERT INTO " +  RUBIC  + "(" + ARTIST_NAME + "," + ARTIST_SONG + ","+PRICE +") VALUES  (?, ?, ?)";
                System.out.println(addDataSql);
                psInsert = conn.prepareStatement(addDataSql);
                //Setting of prepared statement varioable

                psInsert.setString(1, artistName4);
                psInsert.setString(2, "Test");
                psInsert.setDouble(3, 3.4);
                psInsert.executeUpdate();


                psInsert.close();


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
    private static boolean rubikTableexist() throws SQLException{
        String checkQuery = "SHOW TABLES LIKE '"+ RUBIC +"'";
        ResultSet tableRs = statement.executeQuery(checkQuery);
        if(tableRs.next()){
            return true;
        }
        return false;
    }
    //A method that shut down th database successfully by closing all of the various variable use.
    public static void shutDown(){
        try {
            if (rs != null) {
                rs.close();

            }
        }catch (SQLException se){
            se.printStackTrace();
        }
        try{
            if(statement !=null){
                statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }







//        //Create some example CDs and add them to an inventory list
//        ArrayList<CD> cdInventory = new ArrayList<CD>();
//
//        CD testCD1 = new CD("Lady Gaga", "The Fame Monster", 6.99);
//        CD testCD2 = new CD("Bob Dylan", "Basement Tapes", 9.99);
//
//        cdInventory.add(testCD1);
//        cdInventory.add(testCD2);
//
//        //Create some example LPs and add them to an inventory list
//        ArrayList<LP> lpInventory = new ArrayList<LP>();
//
//        LP testLP1 = new LP("Michael Jackson", "Thriller", 4, 9.99);
//        LP testLP2 = new LP("Replacements", "Hootenanny", 3, 7.99);
//
//        lpInventory.add(testLP1);
//        lpInventory.add(testLP2);
//
//        System.out.println("All LPs in the inventory:");
//        for (LP lp : lpInventory) {
//            System.out.println(lp);
//        }
//
//        System.out.println("All CDs in the inventory");
//        for (CD cd : cdInventory) {
//            System.out.println(cd);
//        }
//
//        //Create a master inventory list with all Albums in
//
//        ArrayList<Album> allInventory = new ArrayList<Album>();
//        allInventory.addAll(lpInventory);
//        allInventory.addAll(cdInventory);
//
//
//        //Search inventory for any CD or LP that matches a search term
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter artist or title to search for. Works for partial artists/titles.");
//        String searchString = scanner.next();
//
//        scanner.close();
//
//        searchForAlbum(allInventory, searchString);
//
//    }
//
//    private static void searchForAlbum(ArrayList<Album> albums, String searchTerm){
//
//        searchTerm = searchTerm.toLowerCase();  //Work in lowercase
//
//        boolean found = false;
//
//        for (Album a : albums) {
//            if (a.getTitle().toLowerCase().contains(searchTerm) || a.getArtist().toLowerCase().contains(searchTerm)){
//                found = true;
//                System.out.println(a);
//                //Equivalent to
//                //System.out.println(a.toString());
//            }
//        }
//
//        if (found == false) {
//            System.out.println("No matching albums found for this search term");
//        }

    }


}

