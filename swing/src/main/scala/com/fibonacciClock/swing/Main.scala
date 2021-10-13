package com.fibonacciClock.swing

import java.awt.{Dimension, Font, Graphics2D}

import com.fibonacciClock.ColAndTypes.{black, bothLegend, hourLegend, minLegend}
import com.fibonacciClock.{AbstractUI, Block, ColAndTypes}
import javax.swing.{AbstractAction, Timer => SwingTimer}

import scala.swing._

object Main extends SimpleSwingApplication {

  val margin = 10

  def top: MainFrame = new MainFrame {

      contents = mainPanel
    }

    def onPaint(g: Graphics2D): Unit = {
      drawBoard(g)
    }

    def drawBoard(g: Graphics2D): Unit = {

      val ui = AbstractUI

      def drawBoarder(): Unit = {
        g setColor ColAndTypes.black
        ui.blockSeq foreach { g draw _.block }
        ui.legendSeq foreach {g draw _.block}
      }

      def fillBlockSeq(blockSequence : Seq[Block]) : Unit = blockSequence.indices foreach {
        i =>
          g setColor blockSequence(i).color
          g fill blockSequence(i).block
      }

      def drawTime(): Unit = {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12))
        g drawString(ui.timeString, margin + 60 , 150)
      }

      def drawlegendText() = {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12))
        g.setColor(black)
        g drawString("hour" , hourLegend.x + 20, hourLegend.y + 10)
        g drawString("min" , minLegend.x + 20 , minLegend.y + 10)
        g drawString("both" , bothLegend.x + 20 , bothLegend.y + 10)
      }

      def drawNumbers(): Unit = {
        ui.blockSeq.indices foreach {
          i =>
          g drawString(ui.fibonacciSeq(i).toString, ui.blockSeq(i).block.x + ui.blockSeq(i).block.width / 2 - margin / 2
            , ui.blockSeq(i).block.y + ui.blockSeq(i).block.height / 2 + margin / 2 )
          }
      }

      def apply(): Unit = {
          fillBlockSeq(ui.blockSeq)
          drawBoarder()
          drawNumbers()
          fillBlockSeq(ui.legendSeq)
          drawlegendText()
          drawTime()
      }

      apply()
    }

    def mainPanel: Panel = new Panel {
      preferredSize = new Dimension(160 + margin * 2, 120 + margin * 2)
      focusable = true

      override def paint(g: Graphics2D): Unit = {
        g setColor com.fibonacciClock.ColAndTypes.backColor
        g fillRect(0, 0, size.width, size.height)
        onPaint(g)
      }

      val timer = new SwingTimer(500, new AbstractAction() {
        def actionPerformed(e: java.awt.event.ActionEvent) {
          repaint
        }
      })
      timer.start()

    }


}
