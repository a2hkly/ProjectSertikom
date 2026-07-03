/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpenjualan;

/**
 *
 * @author Haykal Nauval Syafiq
 */
import java.sql.*;
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
public class KategoriModel {
     Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public void showData(DefaultTableModel dtm) {
        try {
            conn = new Koneksi().connect();
            
            String sql = "SELECT * FROM kategori";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
               
            Object[] data = {
                 rs.getString("Id"),
                 rs.getString("NamaKategori")
                 };
         
                dtm.addRow(data);  
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> showDataForSelectionGroup() {
        
        try {
            conn = new Koneksi().connect();
            
            String sql = "SELECT * FROM kategori";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
    
//            inisialisasi list dari item yang akan ditampilkan
            List<String> listOfItems = new ArrayList<>();

//looping data            
            while (rs.next()) {
              //            inisialisasi list dari item yang akan ditampilkan

                listOfItems.add(rs.getString("NamaKategori"));
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
    
    
     public void insertData(String namaKategori) 
            {
                
        try {
            conn = new Koneksi().connect();
//            query untuk insert sesuaikan dengan kolom di table
            
            String sql = "INSERT INTO kategori"
                    +" (NamaKategori)"
                    + " VALUES(?)";
            
            pst = conn.prepareStatement(sql);
//             passing data sesuai tipe data di kolom table 
            pst.setString(1, namaKategori);
            
            
            pst.execute();
            JOptionPane.showMessageDialog(null,"Sukses membuat data");
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void updateData(Integer id, String namaKategori) {
        try {
            conn = new Koneksi().connect();
            String sql = "UPDATE kategori SET NamaKategori=?"
                    + " WHERE Id=?";
            
            System.out.println(sql);
            pst = conn.prepareStatement(sql);
            pst.setString(1, namaKategori);
            pst.setInt(2, id);
            
            
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
            
            String sql = "DELETE FROM kategori"
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
            
            String sql = "SELECT * FROM kategori WHERE NamaKategori LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%"+cari+"%");
            
            rs = pst.executeQuery();
            while (rs.next()) {
               
            Object[] data = {
                 rs.getString("Id"),
                 rs.getString("NamaKategori")
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
