package optimax.scheme;

import optimax.play.Player;

public interface Scheme<T> {

    static BidScheme create(int quantity, int cash) {
        return  new BasicBidScheme();
    }

    T add(BidScheme next);

    void savePrevBids(int own, int other);

    int getNexBid();

    boolean isLastRound(Player p);

    int deligateBid();

    //Helper methods
    int prevRound();

    int currentRound();

    boolean wonLast();

    boolean drawLast();

    boolean wonOrDrawLast();

    boolean hasMoreFund();

    boolean isLeading();

    boolean isLeadingOrDraw();

    int prevBid(Player p);

    int increaseBid(Player p, int increase);

    int increaseBid(Player p);

    int prevWinnerBid();

    boolean hasReachedTarget(Player p);

    boolean isLastRound();
}
