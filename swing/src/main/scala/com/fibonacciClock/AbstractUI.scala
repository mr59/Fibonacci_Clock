package com.fibonacciClock

import java.awt.Color
import java.time.LocalDateTime
import com.fibonacciClock.ColAndTypes._
import scala.collection.mutable.ListBuffer

/**
 *  Get current time and compute Block Sequences -> color to be filled for hour and min
 */

object AbstractUI {

  def time: LocalDateTime = LocalDateTime.now()
  def hour: Int           = time.getHour
  def min : Int           = time.getMinute
  def sec  : Int          = time.getSecond

  def twelveHourView(hour : Int) : Int  =  if(hour > 11) hour - 12 else hour

  def ts(hour : Int, min : Int): String = (if(twelveHourView(hour) < 10)  "0"+ twelveHourView(hour)
                                            else twelveHourView(hour) ) + ":" + (if(min < 10) "0" + min else min)

  def timeString : String               = ts(twelveHourView(hour),min ) +" : " + sec

  def fibonacci(n : Int): Int = {
    /**
     * Work with recursive
     * Returns n-1'th element (index starts with 0) of fibonacci sequence
     */
    n match {
      case 0 => 0
      case 1 => 1
      case _ => fibonacci(n-2) + fibonacci(n-1)
    }
  }

  val fibonacciSeq: Seq[Int] = (1 to 5) map fibonacci reverse

  /**
   * Compute lighted square sequence : 1 for lighted else 0
   * @param dividend value that will be split in lightedSequence
   * @param xs fibonacci Sequence
   * @return Seq[Int]
   *         example : hour = 4
   *         xs = [5,3,2,1,1]
   *         lighted squares sequence should be
   *         Seq [0,1,0,1,0] : 5*0 + 3*1 + 2*0 + 1*1 + 1*0 = 4
   */
  def computeLightedSequence(dividend : Int, xs : Seq[Int]): Seq[Int] = {

    var accumulator : Int = dividend
    var sequenceList = new ListBuffer[Int]

    val lightedSequence : Seq[Int] = {
      xs foreach {
        x => if (accumulator / x >= 1) {
          sequenceList += 1
          accumulator = accumulator - x
        }
        else {
          sequenceList += 0
          accumulator = accumulator
        }
      }
      sequenceList
    }

    lightedSequence
  }

  def hourSeq: Seq[Int] = computeLightedSequence(twelveHourView(hour), fibonacciSeq)
  def minSeq: Seq[Int] = computeLightedSequence(min/5, fibonacciSeq)

  def setColor(i : Int): Color = hourSeq(i) + minSeq(i) match {
    case 2 => both
    case 1 => if (hourSeq(i) == 1) hourColor else minColor
    case 0 => com.fibonacciClock.ColAndTypes.backColor
  }

  def getBlock (i : Int): Block = Block( rectangleList(i), setColor(i) )

  def blockSeq: Seq[Block] = rectangleList.indices map getBlock
  def legendSeq : Seq[Block] = legendList.indices map {i => Block(legendList(i), legendColorSeq(i))}

}
