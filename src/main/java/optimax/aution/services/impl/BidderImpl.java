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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Obinna Asuzu
 */
@Service
public class BidderImpl implements Bidder {

    @Autowired
    GameContextImpl context;
    @Autowired
    private List<BidScheme> schemes;

    @PostConstruct
    public void init() {
        Collections.sort(schemes,(lhs,rhs) -> compare(lhs.getOrder(), rhs.getOrder()));
    }

    @Override
    public void init(int quantity, int cash) {
        context.init(quantity, cash);
    }

    @Override
    public int placeBid() {
        int bid = schemes.stream()
                .map(s -> s.getBid())
                .findFirst()
                .orElse(0);
        context.incrementRound();
        return bid;
    }

    @Override
    public void bids(int own, int other) {
        context.savePrevBids(own, other);
    }

}
