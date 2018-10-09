/** @file HandValue.java
;******************************************************************************
;* Copyright (c) 2018, Chugn-Chien,An [fiva1987@gmail.com]. All Rights Reserved.
;*
;* You may not reproduce, distribute, publish, display, perform, modify, adapt,
;* transmit, broadcast, present, recite, release, license or otherwise exploit
;* any part of this publication in any form, by any means, without the prior
;* written permission of Insyde Software Corporation.
;*
;******************************************************************************
*/

public class HandValue {
  //
  // Define Values
  //
  private static final int VAL_NUM_SINGLE_ROUND = 5;

  //
  // Define Global Class
  //
  private static StructOfCards mCards = new StructOfCards("PACK ONE");
  private static GameRule mGame = new GameRule();
  

  public static int [] mInFaceRank = new int [VAL_NUM_SINGLE_ROUND];
  public static int [] mInFaceSuit = new int [VAL_NUM_SINGLE_ROUND];

  // public static CribbageRank CrbR;
  
	public static void main (
    String [] Str
    ) 
  {
    int score4Hand = 0;
    if (Str.length != VAL_NUM_SINGLE_ROUND) {
      // error handle
      System.out.println ("Error! ONLY allowing to input 5 Cards due to the rule! ");
      return;
    }
    
    funcReachCards (
      Str, 
      Str.length
      );
    // for (int x = 0 ; x < 5 ; x++)
    //   System.out.print (Str[x].charAt(0));
    // System.out.println ("Pair Score :"+mGame.funcCalPairs(mInFaceSuit, mInFaceRank, VAL_NUM_SINGLE_ROUND));
    // System.out.println ("15s Score :"+mGame.funcCal15s(mInFaceSuit, mInFaceRank, VAL_NUM_SINGLE_ROUND));
    // System.out.println ("Runs Score :"+mGame.funcCalRuns(mInFaceSuit, mInFaceRank, VAL_NUM_SINGLE_ROUND));
    // System.out.println ("Flush Score :"+mGame.funcCalFlush(mInFaceSuit, mInFaceRank, VAL_NUM_SINGLE_ROUND));
    // System.out.println ("NOB Score :"+mGame.funcCalNOB(mInFaceSuit, mInFaceRank, VAL_NUM_SINGLE_ROUND));
    score4Hand += mGame.funcCalNOB(mInFaceSuit, mInFaceRank, VAL_NUM_SINGLE_ROUND); 
    score4Hand += mGame.funcCalPairs(mInFaceSuit, mInFaceRank, VAL_NUM_SINGLE_ROUND); 
    score4Hand += mGame.funcCalRuns(mInFaceSuit, mInFaceRank, VAL_NUM_SINGLE_ROUND); 
    score4Hand += mGame.funcCalFlush(mInFaceSuit, mInFaceRank, VAL_NUM_SINGLE_ROUND); 
    score4Hand += mGame.funcCal15s(mInFaceSuit, mInFaceRank, VAL_NUM_SINGLE_ROUND);
    System.out.println(score4Hand);
		return;
  }
  
  private static void funcReachCards (
    String [] Str, 
    int Size
    ) 
  {

    for (int i =0; i< Str.length; i++) {
      // System.out.print("NAME : "+ Str[i].charAt(mCards.INDEX_RANK_OF_CARDS) + Str[i].charAt(mCards.INDEX_SUIT_OF_CARDS));
      mCards.funcReachCards (Str[i]);
      mInFaceRank [i] = mCards.gRank;
      mInFaceSuit [i] = mCards.gSuit;
      // System.out.println(" Value : " + mInFaceRank[i] + mInFaceSuit[i]);
    }
    return;
  }


}