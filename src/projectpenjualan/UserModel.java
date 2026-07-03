/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectsertikom;

/**
 *
 * @author Haykal Nauval Syafiq
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UserModel {
//inisialisasi conn,pst dan rs
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public void login(String username,String pass,LoginPage lp) 
            throws ClassNotFoundException, SQLException{
//      isi objek conn untuk mendapatkan koneksi dengan database
        conn = new Koneksi().connect();
//        buatlah sql query untuk menampilkan data user berdasarkan kondisi username dan password
        String query = "SELECT * FROM user "
                + "WHERE Username=? and Password=?";
        
//        membaca string query dan passing data pada statement (?) 1 dan 2 
        pst = conn.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, pass);
//        jalankan query dan simpan pada resultset
        rs = pst.executeQuery();
//        jika data user available
        if(rs.next()){
            System.out.println("Login berhasil");
            JOptionPane.showMessageDialog(null, "Login berhasil");
            System.out.println("username:"+
                    rs.getString("username"));
            System.out.println("password:"+
                    rs.getString("password"));
            
            lp.dispose();
            Dashboard d = new Dashboard();
            d.setVisible(true);
            d.setUserName(rs.getString("Name"));
            
        }else{
              JOptionPane.showMessageDialog(null, "Login gagal"); 
            System.out.println("Login gagal , data tidak ada");
        }
    }      
    
}
