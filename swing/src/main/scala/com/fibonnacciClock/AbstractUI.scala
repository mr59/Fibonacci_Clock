package com.fabClock

import java.awt.Color
import java.time.LocalDateTime

import com.fabClock.ColAndTypes._
import com.fabClock.swing.Main.backColor

object AbstractUI {

  def time: LocalDateTime = LocalDateTime.now()
  def hour: Int           = time.getHour
  def min : Int           = time.getMinute
  def sec  : Int          = time.getSecond

  def twelveHourView(hour : Int) : Int  =  if(hour > 11) hour - 12 else hour
  def ts(hour : Int, min : Int): String = (if(twelveHourView(hour) < 10)  "0"+ twelveHourView(hour)  else twelveHourView(hour) ) + ":" + (if(min < 10) "0" + min else min)
  def timeString : String               = ts(twelveHourView(hour),min ) +" : " + sec
  // def nextX: String = new MouseMover().nextX

  def f( x : Int, i : Int ): (Int, Int) =  if (x / i > 1) (1, x - i) else  (x/i, x % i)

  // todo turn into reccursive function
  def getSeq(i : Int): Seq[Int] =
    Seq(f(i,5)._1,
        f( f(i ,5)._2, 3)._1,
        f( f( f(i,5)._2, 3)._2, 2)._1,
        f( f( f( f(i,5)._2, 3)._2, 2)._2,1)._1,
        f( f( f( f(i,5)._2, 3)._2, 2)._2,1)._2
    )

  def hourSeq: Seq[Int] = getSeq(twelveHourView(hour))
  def minSeq: Seq[Int] = getSeq(min/5)

  def setColor(i : Int): Color = hourSeq(i) + minSeq(i) match {
    case 2 => both
    case 1 => if (hourSeq(i) == 1) hourColor else minColor
    case 0 => backColor
  }

  def getBlock (i : Int) = Block( rectangleList(i), setColor(i) )

  def blockSeq: Seq[Block] = rectangleList.indices map getBlock

}
