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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dimas Priyandi
 */
public class ProdukModel {
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public void showData(DefaultTableModel dtm) {
        
          // block statement try & catch
          try {
            conn = new Koneksi().connect();
            // query untuk mengambil semua data produk
            String sql = "SELECT * FROM produk";
            // jalankan statement
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
           // looping data
            while (rs.next()) {
               // simpan setiap nilai pada kolom ke dalam objek data
            Object[] data = {
                 rs.getString("Id"),
                 rs.getString("KodeProduk"),
                 rs.getString("NamaProduk"),
                 rs.getString("KategoriProduk"),
                 rs.getString("HargaProduk"),
                 };
         
// tambahkan pada model table model dengan data dari objek data
                dtm.addRow(data);  
            }
//           hentikan koneksi
            rs.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            // untuk handling error class not found
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            // untuk handling error class sql exception
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> showDataForSelectionGroup() {
        
        try {
            conn = new Koneksi().connect();
            
            String sql = "SELECT * FROM produk";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
    
            List<String> listOfItems = new ArrayList<>();
            
            while (rs.next()) {
                listOfItems.add(rs.getString("KodeProduk"));
            }
            
            
            conn.close();
            
            return listOfItems;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public void insertData(String kodeProduk,
            String namaProduk, String kategoriProduk, Integer hargaProduk) 
            {
                
        try {
            conn = new Koneksi().connect();
//            query untuk insert sesuaikan dengan kolom di table
            
            String sql = "INSERT INTO produk"
                    +" (KodeProduk,NamaProduk,KategoriProduk,HargaProduk)"
                    + " VALUES(?,?,?,?)";
            
            pst = conn.prepareStatement(sql);
//             passing data sesuai tipe data di kolom table 
            pst.setString(1, kodeProduk);
            pst.setString(2, namaProduk);
            pst.setString(3, kategoriProduk);
            pst.setInt(4, hargaProduk);
            
            //  untuk insert,update dan delete gunakan perintah execute
            pst.execute();
            JOptionPane.showMessageDialog(null,"Sukses membuat data");
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void updateData(Integer id,String kodeProduk,
            String namaProduk, String kategoriProduk, Integer hargaProduk) {
        try {
            conn = new Koneksi().connect();
            String sql = "UPDATE produk SET KodeProduk=?,"
                    + "NamaProduk=?,"
                    + "KategoriProduk=?,"
                    + "HargaProduk=?"
                    + " WHERE Id=?";
            
            System.out.println(sql);
            pst = conn.prepareStatement(sql);
            pst.setString(1, kodeProduk);
            pst.setString(2, namaProduk);
            pst.setString(3, kategoriProduk);
            pst.setInt(4, hargaProduk);
            pst.setInt(5, id);
            
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null,"Sukses mengedit data");
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
      public void deleteData(Integer id){
        try {
            conn = new Koneksi().connect();
            
            String sql = "DELETE FROM produk"
                    + " WHERE Id=?";
            
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            
            JOptionPane.showMessageDialog(null,"Sukses delete data");
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
    public void searchData(DefaultTableModel dtm,String cari) {
        try {
            conn = new Koneksi().connect();
            //    searching data berdasarkan nama produk atau kategori produk
            String sql = "SELECT * FROM produk WHERE NamaProduk LIKE ? OR "
                    + "KategoriProduk LIKE ?";
            pst = conn.prepareStatement(sql);
            //    gunakan percent sebelum variable untuk mencari nama
            pst.setString(1, "%"+cari+"%");
            pst.setString(2, "%"+cari+"%");
            
            rs = pst.executeQuery();
//             looping data
            while (rs.next()) {
               
            Object[] data = {
                 rs.getString("Id"),
                 rs.getString("KodeProduk"),
                 rs.getString("NamaProduk"),
                 rs.getString("KategoriProduk"),
                 rs.getString("HargaProduk"),
                 };
         
                dtm.addRow(data);  
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
