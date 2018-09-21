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
  private static final int VAL_SCORE_15S = 2;
  private static final int VAL_PAIR = 2; // a pair must be only in two cards
  private static final int VAL_A_SUIT_OF_CARDS = 14; // start with one for array,
  private static final boolean FLAG_CARDRANK_USED = true;
  private static final boolean FLAG_CARDRANK_UNUSED = false; 
  
  //
  // global values
  //
  private static boolean mCardMarker [] = new boolean [VAL_A_SUIT_OF_CARDS];
  
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
      mCardMarker[cnt] = FLAG_CARDRANK_UNUSED;
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
    // scan all the element in the card array and making a comparsion between the first element and 
    // the rest of part to located the form of pairs
    //
    for (countKey = 0 ; countKey < (ArraySize - 1) ; countKey++) {
      currentPair = rankCards [countKey]; // make the card member as the element of pair.

      if (mCardMarker [currentPair] ) {
        //
        //  error handle : if the rank of cards has been marked as a pair, avoiding this element
        //
        continue;
      }
      pairCnt = 0;

      for (countTar = 0; countTar < ArraySize; countTar++) {
        if (currentPair == rankCards[countTar]) {
          pairCnt++; // increase the counter of same rank cards in a row.
        }
      }
      if (pairCnt < VAL_PAIR && pairCnt > 0) {
        //
        // the Pair has been located, the score is added and mark the card flag as used.
        // 
        allScort+= VAL_SCORE_PAIRS;
        mCardMarker [currentPair] = FLAG_CARDRANK_USED;

      }

    } 
    return allScort;
  }

  public static int funcCalculatePoint (
    StructOfCards Cards
    ) {
  
    return 0;
  }


}