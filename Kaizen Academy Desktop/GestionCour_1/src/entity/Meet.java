/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author 21655
 */
public class Meet {
     private int idM;
      private String lienM;
      private int ratee;

    public Meet(int idM, String lienM, int ratee) {
        this.idM = idM;
        this.lienM = lienM;
        this.ratee = ratee;
    }

    public Meet(int aInt, String string) {
        this.idM = idM;
        this.lienM = lienM;
    }
     

    

    

  
  

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public String getLienM() {
        return lienM;
    }

    public void setLienM(String lienM) {
        this.lienM = lienM;
    }

    public int getRatee() {
        return ratee;
    }

    public void setRatee(int ratee) {
        this.ratee = ratee;
    }

    @Override
    public String toString() {
        return "Meet{" + "idM=" + idM + ", lienM=" + lienM + ", ratee=" + ratee + '}';
    }
    

      
      
    
}
