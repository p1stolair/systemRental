/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author habibi dan riska
 */
public class Rental {
    //variabel yang ada untuk entitas sama dengan yang di database

    //entitas tabel dvd
    private String kodedvd;
    private String judul;
    private String dvddate;
    private String genre;
    private String status;
    private int stok;
    
    //entitas tabel member
    private String kodemem;
    private String namamem;
    private String alamatmem;
    private String telpmem;
    private String datemem;
    private String jk;
    
    //entitas tabel pegawai
    private String kodepeg;
    private String namapeg;
    private String alamatpeg;
    private String telppeg;
    private String datepeg;
    private String jkpeg;
    private String passpeg;
    private String levelpeg;
    
    //entitas pembantu
    private int kode;
    private String user;
    private String pass;
    private String userlog;
    
//method untuk entitas dvd
    
    public String getKodedvd() {
        return kodedvd;
    }

    public void setKodedvd(String kodedvd) {
        this.kodedvd = kodedvd;
    }
    
    public String getJudul() {
        return judul;
    }
    
    public void setJudul(String judul) {
        this.judul = judul;
    }
   
    public String getDdate() {
        return dvddate;
    }
    
    public void setDdate(String dvddate) {
        this.dvddate = dvddate;
    }
    
    public int getStok() {
        return stok;
    }
    
    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
//method pembantu

    public int getKode() {
        return kode;
    }
    
    public void setKode(int status) {
        this.kode = kode;
    }

//method untuk entitas member
    
    public String getKodemem() {
        return kodemem;
    }
    
    public void setKodemem(String kodemem) {
        this.kodemem = kodemem;
    }
    
    public String getNamamem() {
        return namamem;
    }
    
    public void setNamamem(String namamem) {
        this.namamem = namamem;
    }
    
    public String getAlamatmem() {
        return alamatmem;
    }

    public void setAlamatmem(String alamatmem) {
        this.alamatmem = alamatmem;
    }
    
    public String getTelpmem() {
        return telpmem;
    }

    public void setTelpmem(String telpmem) {
        this.telpmem = telpmem;
    }
    
    public String getDatemem() {
        return datemem;
    }

    public void setDatemem(String datemem) {
        this.datemem = datemem;
    }
    
    public String getJK() {
        return jk;
    }

    public void setJK(String jk) {
        this.jk = jk;
    }
    
//method untuk entitas pegawai
    
    public String getKodepeg() {
        return kodepeg;
    }
    
    public void setKodepeg(String kodepeg) {
        this.kodepeg = kodepeg;
    }
    
    public String getNamapeg() {
        return namapeg;
    }
    
    public void setNamapeg(String namapeg) {
        this.namapeg = namapeg;
    }
    
    public String getAlamatpeg() {
        return alamatpeg;
    }

    public void setAlamatpeg(String alamatpeg) {
        this.alamatpeg = alamatpeg;
    }
    
    public String getTelppeg() {
        return telppeg;
    }

    public void setTelppeg(String telppeg) {
        this.telppeg = telppeg;
    }
    
    public String getDatepeg() {
        return datepeg;
    }

    public void setDatepeg(String datepeg){
        this.datepeg = datepeg;
    }
    
    public String getJKPeg() {
        return jkpeg;
    }

    public void setJKPeg(String jkpeg) {
        this.jkpeg = jkpeg;
    }
    
    public String getPasspeg() {
        return passpeg;
    }

    public void setPasspeg(String passpeg) {
        this.passpeg = passpeg;
    }
    
    public String getLevelpeg() {
        return levelpeg;
    }

    public void setLevelpeg(String levelpeg) {
        this.levelpeg = levelpeg;
    }
    
//method login user
    public void setUser(String user) {
        this.user = user;
    }
    public String getUser() {
        return user;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getPass() {
        return pass;
    }
    
}
