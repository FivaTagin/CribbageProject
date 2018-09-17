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
package CribbageProject.CommonLib;

public class StructOfCards {

    private double radius; // 半徑 
    private String name;  // 名稱
 
    // 無參數建構方法 
    public StructOfCards() { 
        this(0.0, "no name");
    }
 
    // 有參數建構方法 
    public StructOfCards(double radius, String name) {  
        this.radius = radius; 
        this.name = name; 
    }

    public double getRadius() { 
        return radius; 
    }

    public String getName() { 
        return name; 
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    
    public void setName(String name) {
        this.name = name;
    }
  
  }