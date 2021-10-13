package com.fibonacciClock

import java.awt.{Color, Color => AWTColor}

import com.fibonacciClock.swing.Main.margin

import scala.swing.Rectangle

object ColAndTypes {

        //-------- Rectangles
        val r2 = new Rectangle(margin,margin,40,40 )
        val r1_1 =  new Rectangle(40 + margin,margin,20 ,20 )
        val r1_2 = new Rectangle(40 + margin,20 + margin,20 ,20 )
        val r3 =  new Rectangle(margin,40 + margin,60 ,60 )
        val r5 =  new Rectangle(60 + margin,margin,100 ,100 )
        val rectangleList: Seq[Rectangle] = Seq(r5,r3,r2,r1_2,r1_1)

        val hourLegend = new Rectangle(margin, 120, 10, 10)
        val minLegend = new Rectangle(margin + 50, 120, 10, 10)
        val bothLegend = new Rectangle(margin + 100, 120, 10, 10)
        val legendList: Seq[Rectangle] = Seq(hourLegend, minLegend, bothLegend)

        //-------- Colors
        val backColor: AWTColor = new AWTColor(255, 255, 255)
        val white = new AWTColor(255, 255, 255)
        val black = new AWTColor(0,0,0)
        val blue = new AWTColor(0, 153, 255)
        val yellow = new AWTColor(255, 255, 26)
        val red = new AWTColor(255, 100, 100)
        val green = new AWTColor(50, 200, 50)
        val grey = new AWTColor(190, 190, 190)
        val lightRed = new AWTColor(247,165,165)
        val lightBlue = new AWTColor(153,204,255)
        val lightGreen = new AWTColor(171,247,165)

        val hourColor: AWTColor = lightRed
        val minColor: AWTColor = lightGreen
        val both: AWTColor = lightBlue
        val legendColorSeq : Seq[Color] = Seq(hourColor, minColor, both)
}
