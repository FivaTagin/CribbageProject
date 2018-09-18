/** @file
 ;******************************************************************************
;* Copyright (c) 2018, Chugn-Chien,An [fiva1987@gmail.com]. All Rights Reserved.
;*
;* You may not reproduce, distribute, publish, display, perform, modify, adapt,
;* transmit, broadcast, present, recite, release, license or otherwise exploit
;* any part of this publication in any form, by any means, without the prior
;* written permission from Chugn-Chien,An [fiva1987@gmail.com].
;*
;******************************************************************************
*/
package CommonLib;

import java.util.Random;

enum Rank { DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }
enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
 public class StructOfCards {

    //
    // Define Feature Flag
    //
    private static final Boolean FLAG_ONLY_ONE_PACK_OF_CARD = true;

    //
    // Define Value
    //
    private static final int VAR_SUIT_NUM = 4;
    private static final int VAR_RANK_NUM = 13;
    private static final Boolean VAR_UNPICKED = true;
    private static final Boolean VAR_PICKED = false;

    public static final int VAR_CARDS_NUM = VAR_SUIT_NUM * VAR_RANK_NUM;
    public static final int VAR_FAILURE_NUM = 666;
    
    //
    // Define Global Values
    // 
    private String mName;  // 名稱
    private static String mCards [][] = new String[VAR_SUIT_NUM][VAR_RANK_NUM];
    private static Boolean mCardsFlag [][] = new Boolean [VAR_SUIT_NUM][VAR_RANK_NUM];
    private static int mCardCount = 0;
    
    public static int gRank = 0;
    public static int gSuit = 0;

    //
    // Define Global Class
    //
    private static Random mRan = new Random();

    //
    // Functions
    //
    private static void funcInitCards () {
      for ( Suit  suit : Suit.values() )
		for (Rank rank: Rank.values()) {
            mCardsFlag [suit.ordinal()][rank.ordinal()] = VAR_UNPICKED;
            mCards[suit.ordinal()][rank.ordinal()] = rank.name() + " of " + suit.name();
        }
    }

    // 無參數建構方法 
    public StructOfCards() { 
      this("no name");
      funcInitCards ();
    }
 
    // 有參數建構方法 
    public StructOfCards(String name) {  
      this.mName = name; 
      funcInitCards ();
    }

    public static String ShowString () {
      return Rank.THREE.name();
    }

     public String getName() { 
        return mName; 
    }

    public void setName(String name) {
        this.mName = name;
    }
  
    public static String funcPickCard (int Rank, int Suit) {
        return mCards [Suit][Rank];
  
      }
    
 /**
 *---------------------------------------------------------------------------------------
 *
 *  funcPickRanCard
 *
 *  Description:
 *     Picking a new card randiomly
 *
 *  Parameters:
 *    @param[in]     N/A
 *  
 *
 *    @retval         PMODULE_STATUS
 *
 *---------------------------------------------------------------------------------------
 **/

    public static String funcPickRanCard () {
      int Rank = mRan.nextInt(VAR_RANK_NUM);
      int Suit = mRan.nextInt(VAR_SUIT_NUM);
      do {
        if (((mCardsFlag [Suit][Rank] == VAR_UNPICKED) && FLAG_ONLY_ONE_PACK_OF_CARD) && 
           (mCardCount < VAR_CARDS_NUM)) {

          mCardsFlag [Suit][Rank] = VAR_PICKED;
          mCardCount++; 
          break;
        } else if (mCardCount >= VAR_CARDS_NUM) {
          gRank = VAR_FAILURE_NUM;
          gSuit = VAR_FAILURE_NUM;
          return "This Pack is empty.";

        }
        Rank = mRan.nextInt(VAR_RANK_NUM);
        Suit = mRan.nextInt(VAR_SUIT_NUM);
      } while (true);
      gRank = Rank;
      gSuit = Suit;
      return mCards [Suit][Rank];
    }
 /**
 *---------------------------------------------------------------------------------------
 *
 *  funcCheckPackofCard
 *
 *  Description:
 *     Check this pack of card has been picked over or not.
 *
 *  Parameters:
 *    @param[in]     N/A
 *  
 *
 *    @retval        True 
 *    @retval        False 
 *
 *---------------------------------------------------------------------------------------
 **/
    
} 