/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimax.aution.services.impl;

import optimax.aution.services.BidScheme;
import optimax.aution.services.GameContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author obinna.asuzu
 */
@Service
public class CopyBidScheme implements BidScheme{
    
    @Autowired
    GameContext context;

    @Override
    public int getBid() {
        int defaultBid = context.prevWinnerBid() + 1;
        if (context.hasMoreFund()) {
            return defaultBid > context.getMe().getBalance()
                    ? context.getMe().getBalance()
                    : defaultBid;
        } else {
            return -1;
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
