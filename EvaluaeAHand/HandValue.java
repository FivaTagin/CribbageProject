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
import CommonLib.StructOfCards;
import GameRule.GameRule;


public class HandValue {
  //
  // Define Values
  //
  

  //
  // Define Global Class
  //
  private static StructOfCards mCards = new StructOfCards("PACK ONE");
  private static GameRule mGame = new GameRule();
  private static int VAL_NUM_SINGLE_ROUND = 5;

  public static String [] mInputCards = new String [VAL_NUM_SINGLE_ROUND];
  
	public static void main (String [] Str) {

    funcReachCards(Str, Str.length);
    
		return;
  }
  
  private static void funcReachCards (String [] Str, int Size) {
    for (int i =0; i< Str.length; i++)
      System.out.println("NAME : "+ Str[i]);
    return;
  }
}

public class ARoundOfCards {


  
}
