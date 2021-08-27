/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimax.scheme;

import optimax.scheme.BidScheme;

/**
 *
 * @author Obinna Asuzu
 */
public class BasicBidScheme extends BidScheme {

    @Override
    public int getBid() {
        if(hasReachedTarget(me)){
            return 0;
        }
        if(isLeadingOrDraw() && isLastRound(other)){
            /**
             * Go all in, but if is a draw and the other goes all in and has more fund I loose
             */
            return me.getBalance();
        }
        if (wonOrDrawLast() && hasMoreFund()) {
            return increaseBid(me);
        }
        return deligateBid();
    }

}
