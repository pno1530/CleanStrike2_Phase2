package app.com.domain

import org.specs2.mutable.Specification

class CarromBoardTest extends Specification {
  "CarromBoard" should {
    "return true for carromboard is empty if coins is not present" in {
      val carromBoard = CarromBoard(0,0).right.get
      val expected = true

      val actual = carromBoard.isEmpty

      actual mustEqual expected
    }

    "return false for carromboard is empty if coins is present" in {
      val carromBoard = CarromBoard(3,1).right.get
      val expected = false

      val actual = carromBoard.isEmpty

      actual mustEqual expected
    }

    "drop 1 Red Coin " in {
      val carromBoard = CarromBoard(3,1).right.get
      val expected = CarromBoard(3,0)

      val actual = carromBoard.dropCoins(1, Red)

      actual.isRight mustEqual true
      actual mustEqual expected
    }

    "return max black coins count" in {
      val expected = 9

      val actual = CarromBoard.maxBlackCoins

      actual mustEqual expected
    }

    "return max black coins count" in {
      val expected = 1

      val actual = CarromBoard.maxRedCoins

      actual mustEqual expected
    }

    "return `InvalidBlackCoinsCount` when black coins count is more than limit " in {
      val expected = Left(InvalidBlackCoinsCount)

      val actual = CarromBoard(10, 1)

      actual mustEqual expected
    }

    "return `InvalidBlackCoinsCount` when black coins count is less than limit " in {
      val expected = Left(InvalidBlackCoinsCount)

      val actual = CarromBoard(-1, 1)

      actual mustEqual expected
    }

    "return `InvalidRedCoinsCount` when red coins count is less than limit " in {
      val expected = Left(InvalidRedCoinsCount)

      val actual = CarromBoard(3, -1)

      actual mustEqual expected
    }

    "return `InvalidRedCoinsCount` when red coins count is more than limit " in {
      val expected = Left(InvalidRedCoinsCount)

      val actual = CarromBoard(3, 2)

      actual mustEqual expected
    }

    "return valid carromBoard" in {
      val expected = CarromBoard(List(Black, Black, Black, Red))

      val actual = CarromBoard(3,1)

      actual mustEqual expected
    }

    "return valid carromBoard" in {
      val expected = CarromBoard(2, 0)

      val actual = CarromBoard(List.fill(2)(Black) ++ List.fill(0)(Red))

      actual mustEqual expected
    }

    "return `InvalidBlackCoins` when black coins are more than limit" in {
      val expected = Left(InvalidBlackCoins)

      val actual = CarromBoard(List.fill(10)(Black) ++ List.fill(0)(Red))

      actual mustEqual expected
    }

    "return `InvalidRedCoins` when red coins are more than limit" in {
      val expected = Left(InvalidRedCoins)

      val actual = CarromBoard(List.fill(1)(Black) ++ List.fill(2)(Red))

      actual mustEqual expected
    }
  }
}
