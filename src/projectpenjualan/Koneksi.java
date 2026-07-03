/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Haykal Nauval Syafiq
 */
package projectpenjualan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
//    set konstanta untuk pengaturan driver,db,user dan password
    private final String JDBC_URL ="com.mysql.cj.jdbc.Driver"; 
    private final String DB_NAME = "sertikom_db";
    private final String DB_URL =
            "jdbc:mysql://localhost/"+DB_NAME;
    private final String USER = "root";
    private final String PASS = "";
//  inisialisasi objek  conn
    Connection conn;
    
    public Connection connect() throws ClassNotFoundException,
            SQLException{
//        isi driver sesuai URL yang di definisikan
        Class.forName(JDBC_URL);
//        isi objek conn untuk mendapatkan koneksi dengan database
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
        System.out.println("koneksi sukses");
        return conn;
    }
}