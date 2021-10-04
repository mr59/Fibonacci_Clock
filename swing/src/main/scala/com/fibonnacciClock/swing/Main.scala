package com.fabClock.swing

import java.awt.{Dimension, Font, Graphics2D, Color => AWTColor}

import com.fabClock.{AbstractUI, ColAndTypes}
import javax.swing.{AbstractAction, Timer => SwingTimer}

import scala.swing._

object Main extends SimpleSwingApplication {


    val backColor = new AWTColor(255, 255, 255)
    val margin = 10
    val ratio = 10

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
        ui.blockSeq foreach {
          g draw _.block
        }
      }

      def fillRectangle(i: Int): Unit = {
        g setColor ui.blockSeq(i).color
        g fill ui.blockSeq(i).block
      }

      def drawTime(): Unit = {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10))
        g drawString(ui.timeString, 20, 20)
        //      g drawString(ui.nextX, 20, 130)
      }

      def fillBlocks(): Unit = ui.blockSeq.indices foreach fillRectangle

      fillBlocks()
      drawBoarder()
      //drawTime

    }

    def mainPanel: Panel = new Panel {
      preferredSize = new Dimension(160 + margin * 2, 100 + margin * 2)
      focusable = true

      override def paint(g: Graphics2D): Unit = {
        g setColor backColor
        g fillRect(0, 0, size.width, size.height)
        onPaint(g)
      }

      val timer = new SwingTimer(100, new AbstractAction() {
        def actionPerformed(e: java.awt.event.ActionEvent) {
          repaint
        }
      })
      timer.start()

    }


}
