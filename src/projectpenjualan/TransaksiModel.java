/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpenjualan;

/**
 *
 * @author Haykal Nauval Syafiq
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class TransaksiModel {
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public void showData(DefaultTableModel dtm) {
        try {
            conn = new Koneksi().connect();
            
            String sql = "SELECT * FROM transaksi";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
               
            Object[] data = {
                 rs.getString("Id"),
                 rs.getString("IdProduk"),
                 rs.getString("TotalHarga"),
                 rs.getString("Bayar")
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
    
     
     public Integer getDataIdProdukByKode(String kodeProduk,TransaksiPage tp) 
            {
             
                 try {
            conn = new Koneksi().connect();
            
            String sql = "SELECT * FROM produk WHERE KodeProduk=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, kodeProduk);
            rs = pst.executeQuery();
            Integer id = 0;
            
            
            if (rs.next()) {
               
                tp.getTxtNamaProduk().setText(rs.getString("NamaProduk"));
                tp.getTxtKategoriProduk().setText(rs.getString("KategoriProduk"));
                tp.getTxtHargaProduk().setText(rs.getString("HargaProduk"));

            }
            
            tp.getTxtKuantitas().setText("1");
                
            
            
            rs.close();
            conn.close();
            
            return id;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
     
     
     public void insertData(Integer idProduk, Integer totalHarga, Integer bayar) 
            {
                
        try {
            conn = new Koneksi().connect();
//            query untuk insert sesuaikan dengan kolom di table
            
            String sql = "INSERT INTO transaksi"
                    +" (IdProduk,TotalHarga,Bayar)"
                    + " VALUES(?,?,?)";
            
            pst = conn.prepareStatement(sql);
//             passing data sesuai tipe data di kolom table 
            pst.setInt(1, idProduk);
            pst.setInt(2, totalHarga);
            pst.setInt(3, bayar);
            
            
            
            
            pst.execute();
            JOptionPane.showMessageDialog(null,"Sukses membuat data");
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
            
            String sql = "DELETE FROM transaksi"
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
      
}