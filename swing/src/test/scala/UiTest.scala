import org.specs2._
import com.fibonacciClock.AbstractUI._

class UiTest extends Specification { def is =                                                          s2"""
  This is a specification to AbstractUi

  Hours and minutes for 14'27'' hours should be converted to twelve hour format : 2                 $twelveHourView1
  should be displayed as "02:27"                                                                    $ts1
  8th element of fibonacci sequence must be 21 : [0,1,1,2,3,5,8,13,21]                              $fibonacciNum
  hour = 4
  xs = [5,3,2,1,1]
  lighted squares sequence should be
  Seq [0,1,0,1,0] : 5*0 + 3*1 + 2*0 + 1*1 + 1*0 = 4                                                 $testLigtedSeqOne
  hour 6, same xs must return =>  Seq [1,0,0,1,0]                                                   $testLigtedSeqTwo

  """

  val hour = 14
  val min = 27

  def twelveHourView1 = twelveHourView(hour) mustEqual 2

  def ts1 = ts(twelveHourView(hour), min) mustEqual "02:27"

  def fibonacciNum = fibonacci(8) mustEqual 21

  val xs = (1 to 5) map fibonacci reverse

  def testLigtedSeqOne = computeLightedSequence(4, xs) mustEqual(Seq(0,1,0,1,0))

  def testLigtedSeqTwo = computeLightedSequence(6, xs) mustEqual(Seq(1,0,0,1,0))

}