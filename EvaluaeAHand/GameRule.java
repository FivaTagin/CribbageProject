import java.util.Arrays;

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

//  package src;

// import CommonLib.StructOfCards;

public class GameRule {
  //
  // Define Global Class
  //
  private static StructOfCards mCards = new StructOfCards("PACK TWO");

  //
  // Define Values
  //
  private static final int VAL_SCORE_PAIRS = 2;
  // private static final int VAL_QUANTITY_A_PAIR = 2;
  private static final int VAL_PAIR = 2; // a pair must be only in two cards

  private static final int VAL_SCORE_15S = 2;
  private static final int VAL_POINT_4_JQK = 10;
  private static final int VAL_POINT_OF_15S = 15;

  // private static final int VAL_SCORE_RUN = 1;
  // private static final int VAL_MIN_RUN = 3;

  private static final int VAL_SCORE_ONE_NOB = 1;
  private static final int INDEX_RANK_OF_NOB = 11;

  private static final int VAL_SCORE_FLUSH = 4;
  private static final int VAL_SCORE_FLUSH_EX = 1;
  private static final int VAL_FLAG_FLUSHED = 4;
  


  private static final int VAL_RANKS_OF_A_SUIT = 14; // start with one for array,
  private static final int VAL_SUITS_OF_A_PACK = 4;
  private static final int INDEX_START_CARD = 4;
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
*  funcGetPermutations
*
*  Description:
*     cacluating Permutations for the situation of a designated set.
*     formula is inQuantity! / (qSet! * (inQuantity- qSet)!)
*
*  Parameters:
*    @param[in]     int inQuantity : the number of input's elements
*    @param[in]     int qSet : the quantity of a Set
*
*    @retval        Int allScort : the score of paris.
*
*---------------------------------------------------------------------------------------
**/
  private static int funcGetPermutations (
    int inQuantity, 
    int qSet
    ) 
  {
    int permutations = 0;

    if (inQuantity < qSet ) return 0; // error handle
    
    // calculate permutations
    permutations = funcGetFactorial(inQuantity) / (funcGetFactorial(qSet) * funcGetFactorial(inQuantity - qSet));

    return permutations;
  }

/**
*---------------------------------------------------------------------------------------
*
*  funcGetFactorial
*
*  Description:
*     cacluating factorial 
*     formula is 1 * 2 * 3 *...* N
*
*  Parameters:
*    @param[in]     int input : the number of factorial
*
*    @retval        Int allScort : the result of factorial.
*
*---------------------------------------------------------------------------------------
**/
  private static int funcGetFactorial (
    int input
    ) 
  {
    int tempCnt;
    int factorial = 1;
    
    for (tempCnt = 1; tempCnt <= input; tempCnt++) 
      factorial *= tempCnt;
    
    return factorial;

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
  int currentPair;
  int countCard = 0;  
  
  //
  // reinitialising the variable to make sure the result in correct.
  //
  funcInitalVariable();
  
  //
  // calcuating every element in the card of hand.
  //
  for (countCard = 0; countCard < ArraySize; countCard++) {
    mCardMarker [rankCards[countCard]]++;
  }

  //
  // calcuating the score of pairs, using factorial method
  //
  for (countCard = 0; countCard < VAL_RANKS_OF_A_SUIT - 1; countCard++) {
    if (mCardMarker [countCard] >= VAL_PAIR ) {// pair is found 
      // find how many pairs in the hand
      allScort += funcGetPermutations (mCardMarker [countCard], VAL_PAIR); 
    }
  }
  // illustrate the score of pairs
  allScort *= VAL_SCORE_PAIRS;
  return allScort;
}

/**
*---------------------------------------------------------------------------------------
*
*  funcCal15s
*
*  Description:
*     cacluating point for the situation of 15s.
*
*  Parameters:
*    @param[in]     int suitCards : the array of the suit of cards
*    @param[in]     int rankCards : the array of the rank of cards
*    @param[in]     int ArraySize : the size of a set of cards
*
*    @retval        Int allScort : the score of 15s.
*
*---------------------------------------------------------------------------------------
**/
  public static int funcCal15s (
    int suitCards[],
    int rankCards[], 
    int ArraySize
    ) 
  {
    int allScort = 0;
    int countKey = 0;
    int countTar = 0;  
    int currentScort = 0;
    int nbit = 1 << ArraySize; // the quantity of permutations for one game which is 2^n times;
    int tempCards [] = new int [ArraySize];
    //
    // reinitialising the variable to make sure the result in correct.
    //
    funcInitalVariable();
    tempCards = Arrays.copyOf(rankCards, ArraySize);

    //
    // translate the rank of card to match the game's rule
    //
    for (int i = 0; i < ArraySize; i++) {
      if (tempCards[i] > VAL_POINT_4_JQK ) 
      tempCards[i] = VAL_POINT_4_JQK;
    }
    //
    // scaning all permutations and making score
    //
    for (countKey = 0; countKey < nbit; countKey++) {
      int buffer [] = new int [5];
      for (countTar = 0; countTar < ArraySize; countTar++) {
        if ((countKey & (1 << countTar)) != 0) {
          buffer [countTar] = tempCards [countTar];
          
        }
      }
      //
      // if the score of a permutations is equal then 15, adding score
      // otherwise, continue the loop for the next permutation element.
      //
      for (int i = 0; i < ArraySize; i++)
        currentScort += buffer [i]; 
      if (currentScort == VAL_POINT_OF_15S) 
        allScort += VAL_SCORE_15S;
      currentScort = 0; // reset score for this set of card

    }

    return allScort;
  }

  public static int funcCalculatePoint (
    StructOfCards Cards
    ) 
  {
    return 0;
  }

  /**
*---------------------------------------------------------------------------------------
*
*  funcCalRuns
*
*  Description:
*     cacluating point for the situation of runs.
*
*  Parameters:
*    @param[in]     int suitCards : the array of the suit of cards
*    @param[in]     int rankCards : the array of the rank of cards
*    @param[in]     int ArraySize : the size of a set of cards
*
*    @retval        Int allScort : the score of runs.
*
*---------------------------------------------------------------------------------------
**/
  public static int funcCalRuns (
    int suitCards[],
    int rankCards[], 
    int ArraySize
    ) 
  { 
    int countCard = 0;
    int countScore = 1;
    int countElement = 0;
    int allScort = 0;

    //
    // reinitialising the variable to make sure the result in correct.
    //
    funcInitalVariable();

    //
    // cacluating every element in the card of hand.
    //
    for (countCard = 0; countCard < ArraySize; countCard++) {
      mCardMarker [rankCards[countCard]]++;
    }

    //
    // scaning all count of cards to find all set of runs
    //
    for (countCard = 1; countCard < VAL_RANKS_OF_A_SUIT - 1; countCard++) {
      if (mCardMarker[countCard] != 0) {
        //
        // set current score. 
        // TODO : if current element is non in the hand. the score will be setting back to 0.
        //
        countScore *= mCardMarker [countCard];
        //
        // find element in a run and make sure it is a continuous row.
        //
        if ((mCardMarker[countCard - 1] * mCardMarker [countCard + 1] * mCardMarker[countCard]) != 0) {
          //
          // if find three hand cards with continuously sequence, the countElement will be marked
          // but the hand value has to make sure there might be 5 serious card in the hand.
          // therefore, after finding a row of cards, the next element will make countElement to be added
          //
          if( countElement > 2 ){
						countElement = countElement + 1;
					}else{
						countElement = 3;
          }	
        }
      }
    }

    return allScort = countElement * countScore;
  }

  /**
*---------------------------------------------------------------------------------------
*
*  funcCalFlush
*
*  Description:
*     cacluating point for the situation of Flush .
*
*  Parameters:
*    @param[in]     int suitCards : the array of the suit of cards
*    @param[in]     int rankCards : the array of the rank of cards
*    @param[in]     int ArraySize : the size of a set of cards
*
*    @retval        Int allScort : the score of Flush.
*
*---------------------------------------------------------------------------------------
**/
  public static int funcCalFlush (
    int suitCards[],
    int rankCards[], 
    int ArraySize
    ) 
  { 
    int allScort = 0;
    int flushCount = 0;
    int startSuit = 0;
    int currentSuit = suitCards [0]; // set first hand of card's suit
    
    // reach the suit of the first card.
    startSuit = suitCards [INDEX_START_CARD];

    for (int countKey = 1; countKey < ArraySize; countKey++)
      //
      // checking all card in hand are same suits
      // than check the suit between the first card and the suit of flush.
      //
      if (suitCards [countKey] == currentSuit) 
        flushCount++;

    if (flushCount == VAL_FLAG_FLUSHED) {
      allScort += VAL_SCORE_FLUSH;
      if (currentSuit == startSuit)
        allScort += VAL_SCORE_FLUSH_EX;

    }
    return allScort;
  }

/**
*---------------------------------------------------------------------------------------
*
*  funcCalNOB
*
*  Description:
*     cacluating point for the situation of Flush .
*
*  Parameters:
*    @param[in]     int suitCards : the array of the suit of cards
*    @param[in]     int rankCards : the array of the rank of cards
*    @param[in]     int ArraySize : the size of a set of cards
*
*    @retval        Int allScort : the score of Flush.
*
*---------------------------------------------------------------------------------------
**/
  public static int funcCalNOB (
    int suitCards[],
    int rankCards[], 
    int ArraySize
    ) 
  { 
    int allScort = 0;
    int startSuit = 0;
    int countKey = 0;
    
    // reach the suit of the first card.
    startSuit = suitCards [4];
    for (countKey = 0; countKey < ArraySize - 1; countKey++) {
      //
      // locating a Jack which matches the same suit with the first card
      // card array of rank is starting from zero. therefore, adjustment for the index.
      //
      if (suitCards [countKey] == startSuit && rankCards [countKey] == INDEX_RANK_OF_NOB) {
        allScort = VAL_SCORE_ONE_NOB;
        break;
      }
    }
    return allScort;
  }


}