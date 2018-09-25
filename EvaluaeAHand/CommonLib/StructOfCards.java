/** @file StructOfCards.java
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
import java.lang.*;
import java.util.*;
enum Rank { 
  A(1) ,
  DEUCE(2), 
  THREE(3), 
  FOUR(4), 
  FIVE(5), 
  SIX(6), 
  SEVEN(7), 
  EIGHT(8), 
  NINE(9), 
  T(10), 
  J(11), 
  Q(12), 
  K(13);
  
  private int index;
  private Rank (int Value) {
    this.index = Value;

  }
}
enum Suit { C(0), D(1), H(2), S(3) ;
  private int index = 0;

  private Suit (int Value) {
    this.index = Value;
  }


  public int getValue () {
    return this.index;
  }



}
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
    private static final int VAR_SUIT_CLUB = 0;
    private static final int VAR_SUIT_DIAMOND = 1;
    private static final int VAR_SUIT_HEART = 2;
    private static final int VAR_SUIT_SPADE = 3;
    private static final int VAR_RANK_ACE = 1;
    private static final int VAR_RANK_DEUCE = 2;
    private static final int VAR_RANK_THIRD = 3;
    private static final int VAR_RANK_FOUR = 4;
    private static final int VAR_RANK_FIVE = 5;
    private static final int VAR_RANK_SIX = 6;
    private static final int VAR_RANK_SEVEN = 7;
    private static final int VAR_RANK_EIGHT = 8;
    private static final int VAR_RANK_NIGH = 9;
    private static final int VAR_RANK_TEN = 10;
    private static final int VAR_RANK_JACK = 11;
    private static final int VAR_RANK_QUEEN = 12;
    private static final int VAR_RANK_KING = 13;


    public static final int VAR_CARDS_NUM = VAR_SUIT_NUM * VAR_RANK_NUM;
    public static final int VAR_FAILURE_NUM = -666;
    public static final int INDEX_RANK_OF_CARDS = 0;
    public static final int INDEX_SUIT_OF_CARDS = 1;
    
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


/** 
 *---------------------------------------------------------------------------------------
 *
 *  funcInitCards
 *
 *  Description:
 *     Initialise a new pack of card
 *
 *  Parameters:
 *    @param[in]     N/A
 *  
 *
 *    @retval         PMODULE_STATUS
 *
 *---------------------------------------------------------------------------------------
 **/
    private static void funcInitCards () {
      for ( Suit  suit : Suit.values() )
		for (Rank rank: Rank.values()) {
            mCardsFlag [suit.ordinal()][rank.ordinal()] = VAR_UNPICKED;
            mCards[suit.ordinal()][rank.ordinal()] = rank.name() + suit.name();
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
 *  funcReachCards
 *
 *  Description:
 *     Reach a card and transcript to a readable form which is integer in this case.
 *
 *  Parameters:
 *    @param[in]     String  Card : a single card.
 *  
 *
 *    @retval        Int     gRank : the Rank of the Card.
 *    @retval        Int     gSuit : the Suit of the Card.
 *
 *---------------------------------------------------------------------------------------
 **/
  public static void funcReachCards(
    String Card
    ) 
  {
   
    gRank = funcGetRank(Card.charAt(INDEX_RANK_OF_CARDS));
    gSuit = funcGetSuit(Card.charAt(INDEX_SUIT_OF_CARDS));
  }
 /**
 *---------------------------------------------------------------------------------------
 *
 *  funcGetSuit
 *
 *  Description:
 *     Reach a card's Suit
 *
 *  Parameters:
 *    @param[in]     Char  Suit : the suit of the card
 *  
 *
 *    @retval        Int   VarSuit : the suit of the integer form
 *
 *---------------------------------------------------------------------------------------
 **/
  private static int funcGetSuit (
    char cSuit
    ) 
  {
    int VarSuit = 0;
    Suit eSuit;

    switch (cSuit) {

      case 'S':
        VarSuit = VAR_SUIT_SPADE;
      break;
      case 'D':
        VarSuit = VAR_SUIT_DIAMOND;
      break;
      case 'C':
        VarSuit = VAR_SUIT_CLUB;
      break;
      case 'H':
        VarSuit = VAR_SUIT_HEART;
      break;
      default:
        VarSuit = VAR_FAILURE_NUM;  
      break;
    }
   
    return VarSuit;
  }

  /**
 *---------------------------------------------------------------------------------------
 *
 *  funcGetSuit
 *
 *  Description:
 *     Reach a card's Rank
 *
 *  Parameters:
 *    @param[in]     Char  Rank : the Rank of the card
 *  
 *
 *    @retval        Int   VarRank : the Rank of the integer form
 *
 *---------------------------------------------------------------------------------------
 **/
  private static int funcGetRank (
    char cRank
    ) 
  {
    int VarRank = 0;
    Rank rank;
    //
    // translate card from char form to integer form
    //
    switch (cRank) {
    
      case 'A' :
        VarRank = VAR_RANK_ACE;
      break;
      case 'T' :
        VarRank = VAR_RANK_TEN;
      break;
      case 'J' :
        VarRank = VAR_RANK_JACK;
      break;
      case 'Q' :
        VarRank = VAR_RANK_QUEEN;
      break;
      case 'K' :
        VarRank = VAR_RANK_KING;
      break;
      default :
        VarRank = Integer.parseInt(String.valueOf(cRank));
      break;
    }
    
    if (VarRank < 1 || VarRank > 13) VarRank = VAR_FAILURE_NUM;

    return VarRank;
  }

} 