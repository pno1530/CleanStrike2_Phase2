package app.com.domain

import org.specs2.mutable.Specification

class CoinTest extends Specification {
  "Coin" should {
    "return black coins list" in {
        val expected = Right(List(Black, Black))

        val actual = Black(2,3)

        actual mustEqual expected
    }

    "return `InvalidBlackCoinsCount` if black coins count is more than limit" in {
        val expected = Left(InvalidBlackCoinsCount)

        val actual = Black(2,1)

        actual mustEqual expected
    }

    "return red coins list" in {
        val expected = Right(List(Red))

        val actual = Red(1,1)

        actual mustEqual expected
    }

    "return `InvalidRedCoinsCount` if red coins count is more than limit" in {
        val expected = Left(InvalidRedCoinsCount)

        val actual = Red(2,1)

        actual mustEqual expected
    }
  }
}
