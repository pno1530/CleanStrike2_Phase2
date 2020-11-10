package app.com.domain

import org.specs2.mutable.Specification

class OutcomeStrikeTest extends Specification {
  "Outcome" should{
    "Strike" should {
      "return points and drop coins count for `Strike`" in {
        val expectedPoints = 1
        val expectedCoins = 1

        val actualPoints = Strike.points
        val actualCoins = Strike.dropCoins

        actualCoins mustEqual expectedCoins
        actualPoints mustEqual expectedPoints
      }

      "return points and drop coins count for `MultiStrike`" in {
        val expectedPoints = 2
        val expectedCoins = 2

        val actualPoints = MultiStrike.points
        val actualCoins = MultiStrike.dropCoins

        actualCoins mustEqual expectedCoins
        actualPoints mustEqual expectedPoints
      }

      "return points and drop coins count for `RedStrike`" in {
        val expectedPoints = 3
        val expectedCoins = 1

        val actualPoints = RedStrike.points
        val actualCoins = RedStrike.dropCoins

        actualCoins mustEqual expectedCoins
        actualPoints mustEqual expectedPoints
      }

      "return points and drop coins count for `StrikerStrike`" in {
        val expectedPoints = -1
        val expectedCoins = 0

        val actualPoints = StrikerStrike.points
        val actualCoins = StrikerStrike.dropCoins

        actualCoins mustEqual expectedCoins
        actualPoints mustEqual expectedPoints
      }

      "return points and drop coins count for `DefunctStrike`" in {
        val expectedPoints = -2
        val expectedCoins = 1

        val actualPoints = DefunctStrike.points
        val actualCoins = DefunctStrike.dropCoins

        actualCoins mustEqual expectedCoins
        actualPoints mustEqual expectedPoints
      }

      "return points and drop coins count for `ThreeNoStrike`" in {
        val expectedPoints = -1

        val actualPoints = ThreeNoStrike.points

        actualPoints mustEqual expectedPoints
      }

      "return points and drop coins count for `ThreeFoulStrike`" in {
        val expectedPoints = -1

        val actualPoints = ThreeFoulStrike.points

        actualPoints mustEqual expectedPoints
      }

      "return empty list of Outcome strikes" in {
        val expected = Right(List())

        val actual = OutcomeStrike(List())

        actual mustEqual expected
      }

      "return list of Outcome strikes" in {
        val expected = Right(List(Strike, MultiStrike, RedStrike, Strike))

        val actual = OutcomeStrike(List("1", "2", "3", "1"))

        actual mustEqual expected
      }

      "return `InvalidOutcome` if outcome is not valid" in {
        val expected = Left(InvalidOutcome)

        val actual = OutcomeStrike(List("1", "2", "3", "9"))

        actual mustEqual expected
      }
    }
  }
}
