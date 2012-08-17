/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Rental;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author habibi dan riska
 */
//class untuk koneksi ke database
public class SintakRental {
    //url ke database

    private String url = "jdbc:mysql://localhost:3306/rentaldvd";
    //username database
    private String username = "root";
    //password database
    private String password = "";
    //variabel untuk membuat koneksi
    private Connection con;
    //untuk mendapatkan array dari pejabat
    private List<Rental> list;
    //variabel pembantu
    private String kode;
    private String kodemem;
    private String kodepeg;
    private String user;
    private String userlog;

    public SintakRental() {
        try {
            try {
                //mengenalkan driver
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                JOptionPane.showMessageDialog(null, "kesalahan " + ex);
            } catch (IllegalAccessException ex) {
                JOptionPane.showMessageDialog(null, "kesalahan : " + ex);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan : " + ex);
        }
    }

    public SintakRental(Connection con) {
        this.con = con;
    }
    //method untuk koneksi database

    public void connect() throws SQLException {
        this.con = DriverManager.getConnection(url, username, password);
    }
    //method untuk menutup koneksi ke database

    public void disconnect() throws SQLException {
        this.con.close();
    }
    //method mendapatkan data dari tabel dvd dalam bentuk array

    public List<Rental> readdvd() {
        try {
            //membuat statement
            Statement st = con.createStatement();
            String sql = "SELECT * FROM dvd order by kodedvd desc";
            //mendapatkan data dari tabel dalam bentuk result set
            ResultSet rs = st.executeQuery(sql);
            list = new ArrayList<Rental>();
            while (rs.next()) {
                Rental pj = new Rental();
                pj.setKodedvd(rs.getString("kodedvd"));
                pj.setJudul(rs.getString("judul"));
                pj.setGenre(rs.getString("genre"));
                pj.setStatus(rs.getString("status"));
                pj.setStok(rs.getInt("stok"));
                list.add(pj);
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
        return list;

    }

    //method mendapatkan data dari tabel member dalam bentuk array
    public List<Rental> readmem() {
        try {
            //membuat statement
            Statement st = con.createStatement();
            String sql = "SELECT * FROM member order by kodemem desc";
            //mendapatkan data dari tabel dalam bentuk result set
            ResultSet rs = st.executeQuery(sql);
            list = new ArrayList<Rental>();
            while (rs.next()) {
                Rental pj = new Rental();
                pj.setKodemem(rs.getString("kodemem"));
                pj.setNamamem(rs.getString("namamem"));
                pj.setAlamatmem(rs.getString("alamatmem"));
                pj.setTelpmem(rs.getString("telpmem"));
                pj.setDatemem(rs.getString("datemem"));
                list.add(pj);
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
        return list;

    }

    public List<Rental> readpeg() {
        try {
            //membuat statement
            Statement st = con.createStatement();
            String sql = "SELECT * FROM petugas order by kodepeg desc";
            //mendapatkan data dari tabel dalam bentuk result set
            ResultSet rs = st.executeQuery(sql);
            list = new ArrayList<Rental>();
            while (rs.next()) {
                Rental pj = new Rental();
                pj.setKodepeg(rs.getString("kodepeg"));
                pj.setNamapeg(rs.getString("namapeg"));
                pj.setAlamatpeg(rs.getString("alamatpeg"));
                pj.setTelppeg(rs.getString("telppeg"));
                pj.setDatepeg(rs.getString("datepeg"));
                list.add(pj);
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
        return list;

    }
//method untuk generate kode dvd

    public void setKode(String no) {
        try {
            //membuat statement
            String sql = "SELECT max(kodedvd) AS kode FROM dvd WHERE kodedvd like ?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, no + "%");
            //mendapatkan data dari tabel dalam bentuk result set
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("kode") != null) {
                    kode = rs.getString("kode");
                } else {
                    kode = no + "00000";
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
        this.kode = kode;
    }

    public String getKode() {
        return kode;
    }

//method untuk generate kode member
    public void setKodemem(String no) {
        try {
            //membuat statement
            String sql = "SELECT max(kodemem) AS kode FROM member WHERE kodemem like ? ";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, no + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("kode") != null) {
                    kodemem = rs.getString("kode");
                } else {
                    kodemem = no + "00000";
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }

        this.kodemem = kodemem;

    }

    public String getKodemem() {
        return kodemem;
    }

//method untuk generate kode petugas
    public void setKodepeg(String no) {
        try {
            //membuat statement
            String sql = "SELECT kodepeg AS kode FROM petugas WHERE kodepeg = ? ";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, no);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kodepeg = rs.getString("kode");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }

        this.kodepeg = kodepeg;

    }

    public String getKodepeg() {
        return kodepeg;
    }

//method untuk generate jk member
    public void cariJK(String no, Rental pj) {
        try {
            //membuat statement
            String sql = "SELECT jk FROM member WHERE kodemem = ? ";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, no);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pj.setJK(rs.getString("jk"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
    }

//method untuk generate value petugas
    public void cariValPeg(String no, Rental pj) {
        try {
            //membuat statement
            String sql = "SELECT jkpeg,level FROM petugas WHERE kodepeg = ? ";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, no);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pj.setJKPeg(rs.getString("jkpeg"));
                pj.setLevelpeg(rs.getString("level"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
    }

//method untuk insert ke database
    public void insertdvd(Rental pj) {
        try {
            //Memecah tanggal yang tampilannya dd-mm-yyyy
            String thn = pj.getDdate().substring(6, 10);
            String bln = pj.getDdate().substring(3, 5);
            String tgl = pj.getDdate().substring(0, 2);

            String sql = "INSERT INTO dvd VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, pj.getKodedvd());
            ps.setString(2, pj.getJudul());
            ps.setString(3, thn + "-" + bln + "-" + tgl);
            ps.setString(4, pj.getGenre());
            ps.setString(5, pj.getStatus());
            ps.setInt(6, pj.getStok());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
    }

    public void insertmem(Rental pj) {
        try {
            //Memecah tanggal yang tampilannya dd-mm-yyyy
            String thn = pj.getDatemem().substring(6, 10);
            String bln = pj.getDatemem().substring(3, 5);
            String tgl = pj.getDatemem().substring(0, 2);

            String sql = "INSERT INTO member VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, pj.getKodemem());
            ps.setString(2, pj.getNamamem());
            ps.setString(3, pj.getAlamatmem());
            ps.setString(4, pj.getTelpmem());
            ps.setString(5, pj.getJK());
            ps.setString(6, thn + "-" + bln + "-" + tgl);

            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
    }

    public void insertpeg(Rental pj) {
        try {
            //Memecah tanggal yang tampilannya dd-mm-yyyy
            String thn = pj.getDatepeg().substring(6, 10);
            String bln = pj.getDatepeg().substring(3, 5);
            String tgl = pj.getDatepeg().substring(0, 2);

            String sql = "INSERT INTO petugas VALUES(?,?,md5(?),?,?,?,?,?)";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, pj.getKodepeg());
            ps.setString(2, pj.getNamapeg());
            ps.setString(3, "1234");
            ps.setString(4, pj.getAlamatpeg());
            ps.setString(5, pj.getTelppeg());
            ps.setString(6, pj.getJKPeg());
            ps.setString(7, thn + "-" + bln + "-" + tgl);
            ps.setString(8, pj.getLevelpeg());

            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
    }

//method untuk update ke database
    public void updatedvd(String no, Rental pj) {
        try {
            String sql = "UPDATE dvd set judul=?, stok=?, genre=?, status=? WHERE kodedvd=?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, pj.getJudul());
            ps.setInt(2, pj.getStok());
            ps.setString(3, pj.getGenre());
            ps.setString(4, pj.getStatus());
            ps.setString(5, no);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }

    }

    public void updatemem(String no, Rental pj) {
        try {
            String sql = "UPDATE member set namamem=?, alamatmem=?, telpmem=?, jk=? WHERE kodemem=?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, pj.getNamamem());
            ps.setString(2, pj.getAlamatmem());
            ps.setString(3, pj.getTelpmem());
            ps.setString(4, pj.getJK());
            ps.setString(5, no);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }

    }

    public void updatepeg(String no, Rental pj) {
        try {
            String sql = "UPDATE petugas set namapeg=?, alamatpeg=?, telppeg=?, jkpeg=?, level=? WHERE kodepeg=?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, pj.getNamapeg());
            ps.setString(2, pj.getAlamatpeg());
            ps.setString(3, pj.getTelppeg());
            ps.setString(4, pj.getJKPeg());
            ps.setString(5, pj.getLevelpeg());
            ps.setString(6, no);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }

    }

//method untuk delete database
    public void deletedvd(String no) {
        try {
            String sql = "DELETE from dvd WHERE kodedvd=?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, no);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }


    }

    public void deletemem(String no) {
        try {
            String sql = "DELETE from member WHERE kodemem=?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, no);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
    }

    public void deletepeg(String no) {
        try {
            String sql = "DELETE from petugas WHERE kodepeg=?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, no);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
    }

//method untuk pencarian
    public List<Rental> read(String no) {
        try {

            String sql = "SELECT * FROM dvd WHERE kodedvd like ? or judul like ? or genre like ? or status like ?";
            list = new ArrayList<Rental>();
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, "%" + no + "%");
            ps.setString(2, "%" + no + "%");
            ps.setString(3, "%" + no + "%");
            ps.setString(4, "%" + no + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rental pj = new Rental();
                pj.setKodedvd(rs.getString("kodedvd"));
                pj.setJudul(rs.getString("judul"));
                pj.setGenre(rs.getString("genre"));
                pj.setStatus(rs.getString("status"));
                pj.setStok(rs.getInt("stok"));
                list.add(pj);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
        return list;

    }

    public List<Rental> readmemall(String no) {
        try {

            String sql = "SELECT * FROM member WHERE kodemem like ? or namamem like ? ";
            list = new ArrayList<Rental>();
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, "%" + no + "%");
            ps.setString(2, "%" + no + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rental pj = new Rental();
                pj.setKodemem(rs.getString("kodemem"));
                pj.setNamamem(rs.getString("namamem"));
                pj.setAlamatmem(rs.getString("alamatmem"));
                pj.setTelpmem(rs.getString("telpmem"));
                pj.setDatemem(rs.getString("datemem"));
                list.add(pj);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
        return list;

    }

    public List<Rental> readpegall(String no) {
        try {

            String sql = "SELECT * FROM petugas WHERE kodepeg like ? or namapeg like ? ";
            list = new ArrayList<Rental>();
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, "%" + no + "%");
            ps.setString(2, "%" + no + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rental pj = new Rental();
                pj.setKodepeg(rs.getString("kodepeg"));
                pj.setNamapeg(rs.getString("namapeg"));
                pj.setAlamatpeg(rs.getString("alamatpeg"));
                pj.setTelppeg(rs.getString("telppeg"));
                pj.setDatepeg(rs.getString("datepeg"));
                list.add(pj);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
        return list;

    }

//method untuk verifikasi login user
    public void verifikasiAkun(String user, String pass, Rental pj) {
        try {
            //membuat statement
            String sql = "SELECT kodepeg,password FROM petugas WHERE kodepeg = ? ";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pj.setUser(rs.getString("kodepeg"));
                pj.setPass(rs.getString("password"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
    }

//method untuk memasukkan user pass
    public void userLogin(String user, String date ) {
        try {
            //membuat statement
            String sql = "INSERT INTO login(kodepeg,lastlog) VALUES(?,?)";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, date);
           
            ps.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
    }    
 
//method untuk mendapatkan login user
    public void setUser() {
        try {
            //membuat statement
            String sql = "SELECT max(kodepeg)as kode FROM login ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                userlog=rs.getString("kode");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }
        this.userlog=userlog;
    } 
    public String getUser() {
        return userlog;
    }
    
//method untuk logout
    public void userLogout(String user, String date) {
        try {
            String sql = "UPDATE login set lastout=? WHERE kodepeg=?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, user);
         
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada sintak : " + ex);
        }

    }
    
}
