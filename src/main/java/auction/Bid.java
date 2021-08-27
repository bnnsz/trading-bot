/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

/**
 *
 * @author Obinna Asuzu
 */
public class Bid {
    int own;
    int other;

    public int getOwn() {
        return own;
    }

    public void setOwn(int own) {
        this.own = own;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }
    
    public boolean isWon(){
        return own > other;
    }
    
    public boolean isLost(){
        return own < other;
    }
    
    public boolean isDraw(){
        return own == other;
    }
    
}
