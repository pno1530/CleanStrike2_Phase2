package app.com.domain
import org.specs2.mutable.Specification

class PlayerTest extends Specification {
  "Player" should {
    "return list of players " in {
      val expected = Right(List(Player(1,0,List()), Player(2,0,List())))

      val actual = Player(2)

      actual mustEqual expected
    }
  }
}