/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimax.aution.services.impl;

import optimax.aution.models.Player;
import optimax.aution.services.BidScheme;
import optimax.aution.services.GameContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author obinna.asuzu
 */
@Service
public class BarRaiserBidScheme implements BidScheme {

    @Autowired
    GameContext context;

    @Override
    public int getBid() {
        Player me = context.getMe();
        int bid = (me.getBalance() / (context.remaingRounds(me) * 2)) ^ 2;
        return bid < me.getBalance() ? bid : me.getBalance();
    }

    @Override
    public int getOrder() {
        return 2;
    }

}
