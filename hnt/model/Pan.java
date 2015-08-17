/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnt.model;

/**
 *
 * @author yang 
 */
public class Pan {

    private String ptag;
    private double psize;

    public Pan(String ptag, double psize) {
        this.ptag = ptag;
        this.psize = psize;
    }

    /**
     * @return the ptag
     */
    public String getPtag() {
        return ptag;
    }

    /**
     * @param ptag the ptag to set
     */
    public void setPtag(String ptag) {
        this.ptag = ptag;
    }

    /**
     * @return the psize
     */
    public double getPsize() {
        return psize;
    }

    /**
     * @param psize the psize to set
     */
    public void setPsize(double psize) {
        this.psize = psize;
    }

}
