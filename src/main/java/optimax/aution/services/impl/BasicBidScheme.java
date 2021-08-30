/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimax.aution.services.impl;

import optimax.aution.services.BidScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Obinna Asuzu
 */
@Service
public class BasicBidScheme implements BidScheme {
    
    @Autowired
    GameContextImpl context;

    @Override
    public int getBid() {
        if(context.hasReachedTarget(context.getMe())){
            return 0;
        }
        
        if(context.isLeadingOrDraw() && context.isLastRound(context.getOther())){
            /**
             * Go all in, but if is a draw and the other goes all in and has more fund I loose
             */
            return context.getMe().getBalance();
        }
        if (context.wonOrDrawLast() && context.hasMoreFund()) {
            return context.increaseBid(context.getMe());
        }
        return -1;
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
