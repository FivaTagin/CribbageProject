/** @file

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

public class HandValue {
  private static StructOfCards mCards = new StructOfCards();
  
	public static void main (String [] Str) {
    
    System.out.println(mCards.getName());
    System.out.println(mCards.funcPickCard(2,3));

    for (int i = 0;  i < mCards.VAR_CARDS_NUM + 1; i ++) {
      System.out.println(mCards.funcPickRanCard());
    }
      

		return;
	}
}
