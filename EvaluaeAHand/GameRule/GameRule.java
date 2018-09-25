/** @file GameRule.java
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

package GameRule;

import CommonLib.StructOfCards;

public class GameRule {
  //
  // Define Global Class
  //
  private static StructOfCards mCards = new StructOfCards("PACK TWO");

  //
  // Define Values
  //
  private static final int VAL_SCORE_PAIRS = 2;
  private static final int VAL_A_PAIR = 2;
  private static final int VAL_SCORE_15S = 2;
  private static final int VAL_PAIR = 2; // a pair must be only in two cards
  private static final int VAL_RANKS_OF_A_SUIT = 14; // start with one for array,
  private static final int VAL_SUITS_OF_A_PACK = 4;
  private static final int FLAG_CARD_USED = 1;
  private static final int FLAG_CARD_UNUSED = 0; 
  private static final int VAL_FAILURE = 999;
  
  //
  // global values
  //
  private static int mCardMarker [] = new int [VAL_RANKS_OF_A_SUIT];
  private static int mSuitMarker [] = new int [VAL_SUITS_OF_A_PACK];
  
/**
*---------------------------------------------------------------------------------------
*
*  funcInitalVariable
*
*  Description:
*    initialising the valiable that class uses
*
*  Parameters:  
*              N/A
*---------------------------------------------------------------------------------------
**/
  private static void funcInitalVariable () {
    //initialising the array for marking cards' statement
    for (int cnt = 0; cnt < mCardMarker.length; cnt++)
      mCardMarker[cnt] = FLAG_CARD_UNUSED;

    for (int cnt2 = 0; cnt2 < mSuitMarker.length; cnt2++)
      mSuitMarker [cnt2] = FLAG_CARD_UNUSED;
  }

/**
*---------------------------------------------------------------------------------------
*
*  GameRule
*
*  Description:
*     N/A
*
*  Parameters:
*               N/A
*---------------------------------------------------------------------------------------
**/  
  public GameRule () {
    funcInitalVariable();
  }

/**
*---------------------------------------------------------------------------------------
*
*  funcCalPairs
*
*  Description:
*     cacluating point for the situation of paris.
*
*  Parameters:
*    @param[in]     int suitCards : the array of the suit of cards
*    @param[in]     int rankCards : the array of the rank of cards
*    @param[in]     int ArraySize : the size of a set of cards
*
*    @retval        Int allScort : the score of paris.
*
*---------------------------------------------------------------------------------------
**/
  public static int funcCalPairs (
    int suitCards[],
    int rankCards[], 
    int ArraySize
    ) 
  {

    int allScort = 0;
    int countKey = 0;
    int countTar = 0;
    int pairCnt = 0;
    int currentPair;
    
    //
    // reinitialising the variable to make sure the result in correct.
    //
    funcInitalVariable();
    
    //
    // To scan all the element in the card array and making a comparsion between the first element and 
    // the rest of part to located the form of pairs
    //
    for (countKey = 0 ; countKey < (ArraySize - 1) ; countKey++) {
      currentPair = rankCards [countKey]; // make the card member as the element of pair.

      if (mCardMarker [currentPair] >= VAL_A_PAIR) {
        //
        //  error handle : if the rank of cards has been marked as a pair, avoiding this element
        //
        pairCnt = VAL_FAILURE;
        continue;
      } else {
        pairCnt = 0;
        for (countTar = countKey + 1; countTar < ArraySize; countTar++) {
          if (currentPair == rankCards[countTar]) {
            mCardMarker [currentPair]++; // increase the counter of same rank cards in a row.
          }
        }
        //
        // if pair count is over than 2 or small than 1, it's not match the rule of pair.
        //
        if (mCardMarker [currentPair] < VAL_PAIR && mCardMarker [currentPair] > 0) {
          //
          // the Pair has been located, the score is added and mark the card flag as used.
          // 
          
          allScort+= VAL_SCORE_PAIRS;
          mCardMarker [currentPair] += FLAG_CARD_USED;  
        } 
      }
    } 
    return allScort;
  }

  public static int funcCalculatePoint (
    StructOfCards Cards
    ) 
  {
    return 0;
  }


}