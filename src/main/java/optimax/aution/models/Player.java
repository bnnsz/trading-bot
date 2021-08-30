/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimax.aution.models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Obinna Asuzu
 */
public class Player {

    private Map<Integer, Integer> history = new HashMap<>();
    private Integer balance;
    private Integer qu;

    public Player(Integer cash) {
        this.qu = 0;
        balance = cash;
    }

    public void saveBid(int round, int amount, int qu) {
        history.put(round, amount);
        this.balance -= amount;
        this.qu += qu;
    }

    /**
     * @return the history
     */
    public Map<Integer, Integer> getHistory() {
        return new HashMap<>(history);
    }

    /**
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }

    public int getBid(int round) {
        return history.get(round);
    }
    
    public int getQu(){
        return qu;
    }
}
