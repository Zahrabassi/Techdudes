/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kaizen.entity;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class Chairman {
    private String chairmanInitial;
    private String chairmanName;
    private String chairmanEmail;


    public Chairman(String chairmanInitial, String chairmanName, String chairmanEmail) {
        this.chairmanInitial = chairmanInitial;
        this.chairmanName = chairmanName;
        this.chairmanEmail = chairmanEmail;

    }

    public String getChairmanInitial() {
        return chairmanInitial;
    }

    public void setChairmanInitial(String chairmanInitial) {
        this.chairmanInitial = chairmanInitial;
    }

    public String getChairmanName() {
        return chairmanName;
    }

    public void setChairmanName(String chairmanName) {
        this.chairmanName = chairmanName;
    }

    public String getChairmanEmail() {
        return chairmanEmail;
    }

    public void setChairmanEmail(String chairmanEmail) {
        this.chairmanEmail = chairmanEmail;
    }

    

    @Override
    public String toString() {
        return String.format("%s (%s)",
                this.chairmanName,
                this.chairmanInitial);
    }
    
}
