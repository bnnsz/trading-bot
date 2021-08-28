/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimax.scheme;

import optimax.play.Player;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Obinna Asuzu
 */
public abstract class BidScheme implements Scheme<BidScheme> {

    protected Player me;
    protected Player other;
    protected AtomicInteger round;
    protected Integer target;

    private Optional<BidScheme> next = Optional.empty();

    public static BidScheme create(int quantity, int cash) {

        return new BidScheme() {

            AtomicInteger round = new AtomicInteger(1);
            Integer target = ((quantity % 2) == 0) ? ((quantity / 2) + 1) : ((quantity + 1) / 2);

            Player me = new Player(quantity, cash);

            Player other = new Player(quantity, cash);

            @Override
            int getBid() {
                return deligateBid();
            }
        };
    }

    public final BidScheme add(BidScheme next) {
        BidScheme scheme = this;
        while (scheme.next.isPresent()) {
            scheme = scheme.next.get();
        }
        next.me = me;
        next.other = other;
        next.target = target;
        scheme.next = Optional.of(next);
        return this;
    }

    public final void savePrevBids(int own, int other) {
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

    abstract int getBid();

    public final int getNexBid() {
        int bid = getBid();
        round.incrementAndGet();
        return bid;
    }

    public final int deligateBid() {
        return next.map(scheme -> scheme.getNexBid())
                .orElse(0);
    }

    //Helper methods
    public int prevRound() {
        return round.get() - 1;
    }

    public int currentRound() {
        return round.get();
    }

    public boolean wonLast() {
        return prevBid(me) > prevBid(other);
    }

    public boolean drawLast() {
        return prevBid(me) == prevBid(other);
    }

    public boolean wonOrDrawLast() {
        return prevBid(me) >= prevBid(other);
    }

    public boolean hasMoreFund() {
        return me.getBalance() > other.getBalance();
    }

    public boolean isLeading() {
        return me.getQu() > other.getQu();
    }

    public boolean isLeadingOrDraw() {
        return me.getQu() > other.getQu();
    }

    public int prevBid(Player p) {
        return p.getBid(prevRound());
    }

    public int increaseBid(Player p, int increase) {
        return prevBid(p) + increase;
    }

    public int increaseBid(Player p) {
        return prevBid(p) + 1;
    }

    public int prevWinnerBid() {
        return Math.max(prevBid(me), prevBid(other));
    }

    public boolean hasReachedTarget(Player p) {
        return p.getQu() >= target;
    }

    public boolean isLastRound() {
        return target - Math.max(me.getQu(), other.getQu()) <= 2;
    }

    public boolean isLastRound(Player p) {
        return target - p.getQu() <= 2;
    }

}
