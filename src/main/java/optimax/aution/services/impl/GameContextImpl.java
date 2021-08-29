/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimax.aution.services.impl;

import java.util.concurrent.atomic.AtomicInteger;
import optimax.aution.models.Player;
import org.springframework.stereotype.Component;
import optimax.aution.services.GameContext;
import optimax.aution.services.BidScheme;

/**
 *
 * @author obinna.asuzu
 */
@Component
public class GameContextImpl implements GameContext {

    private Player me;
    private Player other;
    private AtomicInteger round;
    private Integer target;
    
    @Override
    public void init(int quantity, int cash) {
        round = new AtomicInteger(1);
        target = ((quantity % 2) == 0) ? ((quantity / 2) + 1) : ((quantity + 1) / 2);
        me = new Player(quantity, cash);
        other = new Player(quantity, cash);
    }
    
    @Override
    public final int getNexBid(BidScheme scheme) {
        int bid = scheme.getBid();
        round.incrementAndGet();
        return bid;
    }

    /**
     * @return the me
     */
    @Override
    public Player getMe() {
        return me;
    }

    /**
     * @return the other
     */
    @Override
    public Player getOther() {
        return other;
    }

    /**
     * @return the round
     */
    @Override
    public AtomicInteger getRound() {
        return round;
    }

    /**
     * @return the target
     */
    @Override
    public Integer getTarget() {
        return target;
    }

    //Helper methods
    @Override
    public void savePrevBids(int own, int other) {
        int meQu = 0;
        int otherQu = 0;
        if (own > other) {
            meQu = 2;
        } else if (own < other) {
            otherQu = 2;
        } else {
            meQu = 1;
            otherQu = 1;
        }
        this.me.saveBid(prevRound(), own, meQu);
        this.other.saveBid(prevRound(), other, otherQu);
    }

    @Override
    public int prevRound() {
        return getRound().get() - 1;
    }

    @Override
    public int currentRound() {
        return getRound().get();
    }

    @Override
    public boolean wonLast() {
        return prevBid(getMe()) > prevBid(getOther());
    }

    @Override
    public boolean drawLast() {
        return prevBid(getMe()) == prevBid(getOther());
    }

    @Override
    public boolean wonOrDrawLast() {
        return prevBid(getMe()) >= prevBid(getOther());
    }

    @Override
    public boolean hasMoreFund() {
        return getMe().getBalance() > getOther().getBalance();
    }

    @Override
    public boolean isLeading() {
        return getMe().getQu() > getOther().getQu();
    }

    @Override
    public boolean isLeadingOrDraw() {
        return getMe().getQu() > getOther().getQu();
    }

    @Override
    public int prevBid(Player p) {
        return p.getBid(prevRound());
    }

    @Override
    public int increaseBid(Player p, int increase) {
        return prevBid(p) + increase;
    }

    @Override
    public int increaseBid(Player p) {
        return prevBid(p) + 1;
    }

    @Override
    public int prevWinnerBid() {
        return Math.max(prevBid(getMe()), prevBid(getOther()));
    }

    
    
    

    @Override
    public boolean hasReachedTarget(Player p) {
        return p.getQu() >= getTarget();
    }

    @Override
    public boolean isLastRound() {
        return getTarget() - Math.max(getMe().getQu(), getOther().getQu()) <= 2;
    }
    
    @Override
    public int remaingRounds(Player p) {
        return getTarget() - p.getQu();
    }

    @Override
    public boolean isLastRound(Player p) {
        return getTarget() - p.getQu() <= 2;
    }

}
