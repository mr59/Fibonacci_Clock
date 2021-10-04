import org.specs2._
import com.fabClock.AbstractUI._
import org.specs2.matcher.MatchResult

class UiTest extends Specification { def is =                                                                 s2"""
  This is a specification to AbstractUi

  Hours and minutes for 14'27'' hours should be converted to twelve hour format : 2                        $twelveHourView1
  should be displayed as "02:27"                                                                           $ts1

  """

  val hour = 14
  val min = 27

  def twelveHourView1 = twelveHourView(hour) mustEqual 2

  def ts1 = ts(twelveHourView(hour), min) mustEqual "02:27"

}