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


enum Rank { DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }
enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
 public class StructOfCards {
    
    //
    // Define Value
    //
    private static final int VAR_SUIT_NUM = 4;
    private static final int VAR_RANK_NUM = 13;

    private static String mCards [][]=new String[VAR_SUIT_NUM][VAR_RANK_NUM];

    private static void funcInitCards () {
      for ( Suit  suit : Suit.values() )
		for (Rank rank: Rank.values())
          mCards[suit.ordinal()][rank.ordinal()] = rank.name() + " of " + suit.name();

    }

    public static String funcPickCard (int Rank, int suit) {
      return mCards [Rank][suit];

    }

    private String mName;  // 名稱

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
  
  } 