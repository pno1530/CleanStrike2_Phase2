package app.com.domain
import org.specs2.mutable.Specification

class TurnTest extends Specification {
  "Turn" should {
    "return list of turns" in {
      val expected = List(Turn(1,Strike), Turn(2,DefunctStrike), Turn(1,MultiStrike))

      val actual = Turn(2, List(Strike, DefunctStrike, MultiStrike))

      actual mustEqual expected
    }

    "return empty list of turns" in {
      val expected = List()

      val actual = Turn(2, List())

      actual mustEqual expected
    }
  }
}
