/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimax.aution.services.impl;

import optimax.aution.models.Player;
import optimax.aution.services.GameContext;
import optimax.aution.Application;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author obinna.asuzu
 */
@SpringBootTest
public class BidderImplTest {

    @Autowired
    BidderImpl instance;
    @Autowired
    GameContext context;

    int quantity = 20;
    int cash = 500;

    public BidderImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance.init(quantity, cash);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of init method, of class BidderImpl.
     */
    @Test
    public void testInit_int_int() {
        System.out.println("init");

        instance.init(quantity, cash);

        Player me = context.getMe();
        Player other = context.getOther();
        Integer target = ((quantity % 2) == 0) ? ((quantity / 2) + 1) : ((quantity + 1) / 2);

        assertEquals(me.getBalance(), cash);
        assertEquals(me.getBalance(), other.getBalance());
        assertEquals(context.getTarget(), target);
    }

    /**
     * Test of placeBid method, of class BidderImpl.
     */
    @Test
    public void testPlaceBid() {
        System.out.println("placeBid");
        int oldBalance = context.getMe().getBalance();
        int oldRound = context.currentRound();
        int result = instance.placeBid();
        
        int balance = context.getMe().getBalance();
        
        assertEquals(balance, oldBalance+result);
        assertEquals(oldRound, context.prevRound());
    }

    /**
     * Test of bids method, of class BidderImpl.
     */
    @Test
    public void testBids() {
        System.out.println("bids");
        int own = 5;
        int other = 7;
        
        instance.bids(own, other);
        Player me = context.getMe();
        Player otherPlayer = context.getOther();
        int prevRound = context.prevRound();
        
        assertEquals(own, me.getBid(prevRound));
        assertEquals(other, otherPlayer.getBid(prevRound));
    }

}
