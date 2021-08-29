/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimax.aution.services;

import java.util.concurrent.atomic.AtomicInteger;
import optimax.aution.models.Player;

/**
 *
 * @author obinna.asuzu
 */
public interface GameContext {

    int currentRound();

    boolean drawLast();

    /**
     * @return the me
     */
    Player getMe();

    int getNexBid(BidScheme scheme);

    /**
     * @return the other
     */
    Player getOther();

    /**
     * @return the round
     */
    AtomicInteger getRound();

    /**
     * @return the target
     */
    Integer getTarget();

    boolean hasMoreFund();

    boolean hasReachedTarget(Player p);

    int increaseBid(Player p, int increase);

    int increaseBid(Player p);

    void init(int quantity, int cash);

    boolean isLastRound();

    boolean isLastRound(Player p);
    
    int remaingRounds(Player p);

    boolean isLeading();

    boolean isLeadingOrDraw();

    int prevBid(Player p);

    int prevRound();

    int prevWinnerBid();

    //Helper methods
    void savePrevBids(int own, int other);

    boolean wonLast();

    boolean wonOrDrawLast();
    
}
