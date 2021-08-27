/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimax.aution.impl;

import optimax.scheme.BasicBidScheme;
import optimax.scheme.BidScheme;
import optimax.aution.Bidder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Obinna Asuzu
 */
@Service
public class BidderImpl implements Bidder {

    BidScheme scheme;

    @Override
    public void init(int quantity, int cash) {
        scheme = BidScheme.create(quantity, cash)
                .add(new BasicBidScheme());
    }

    @Override
    public int placeBid() {
        return scheme.getNexBid();
    }

    @Override
    public void bids(int own, int other) {
        scheme.savePrevBids(own, other);
    }

}
