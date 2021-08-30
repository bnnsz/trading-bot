/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimax.aution.services.impl;

import static java.lang.Integer.compare;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import optimax.aution.services.BidScheme;
import optimax.aution.services.Bidder;
import optimax.aution.services.GameContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Obinna Asuzu
 */
@Service
public class BidderImpl implements Bidder {

    @Autowired
    private GameContext context;
    
    @Autowired
    private List<BidScheme> schemes;

    @PostConstruct
    public void init() {
        //sort by order
        //0 has more priority.
        //bid-scheme 0, bid-scheme 1, bid-scheme 2
        Collections.sort(schemes,(lhs,rhs) -> compare(lhs.getOrder(), rhs.getOrder()));
    }

    @Override
    public void init(int quantity, int cash) {
        context.init(quantity, cash);
    }

    @Override
    public int placeBid() {
        /**
         * Each scheme places bid but we select the first positive bid<br>
         * If there were no positive bids we automatically place a zero bid
         */
        int bid = schemes.stream()
                .map(s -> s.getBid())
                .filter(b -> b >= 0)
                .findFirst()
                .orElse(0);
        
        /**
         * We are keeping track of the number of round.<br>
         * Helps with computing our bids
         */
        context.incrementRound();
        return bid;
    }

    @Override
    public void bids(int own, int other) {
        context.savePrevBids(own, other);
    }

}
