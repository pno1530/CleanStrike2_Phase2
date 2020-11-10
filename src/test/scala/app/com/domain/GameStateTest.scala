package app.com.domain

import org.specs2.mutable.Specification

class GameStateTest extends Specification {
  "GameState" should {
    "return valid updated gamestate for `Strike` player1" in {
      val playerId = 1
      val outcomeStrike = Strike
      val players = List(Player(1, 0, List()), Player(2, 0, List()))
      val carromBoard = CarromBoard(9,1).right.get
      val gameState = GameState(carromBoard, players)
      val expected = List(Player(2,0,List()), Player(1,1,List(Strike)))

      val actual = GameState.updateGameState(playerId, outcomeStrike)(gameState)

      actual.isRight mustEqual true
      actual.right.get.players mustEqual expected
    }

    "return valid updated gamesate for  `MultiStrike` player2" in {
      val playerId = 2
      val outcomeStrike = MultiStrike
      val players = List(Player(1, 0, List()), Player(2, 0, List()))
      val carromBoard = CarromBoard(9,1).right.get
      val gameState = GameState(carromBoard, players)
      val expected = List(Player(1,0,List()), Player(2,2,List(MultiStrike)))

      val actual = GameState.updateGameState(playerId, outcomeStrike)(gameState)

      actual.isRight mustEqual true
      actual.right.get.players mustEqual expected
    }

    "return valid updated gamesate for `RedStrike` player2" in {
      val playerId = 2
      val outcomeStrike = RedStrike
      val players = List(Player(1, 0, List()), Player(2, 0, List()))
      val carromBoard = CarromBoard(9,1).right.get
      val gameState = GameState(carromBoard, players)
      val expected = List(Player(1,0,List()), Player(2,3,List(RedStrike)))

      val actual = GameState.updateGameState(playerId, outcomeStrike)(gameState)

      actual.isRight mustEqual true
      actual.right.get.players mustEqual expected
    }

    "return valid updated gamesate for `DefunctnStrike` for player2" in {
      val playerId = 2
      val outcomeStrike = DefunctStrike
      val players = List(Player(1, 0, List()), Player(2, 0, List()))
      val carromBoard = CarromBoard(9,1).right.get
      val gameState = GameState(carromBoard, players)
      val expected = List(Player(1,0,List()), Player(2,-2,List(DefunctStrike)))

      val actual = GameState.updateGameState(playerId, outcomeStrike)(gameState)

      actual.isRight mustEqual true
      actual.right.get.players mustEqual expected
    }

//    "return Error while dropping the coin which is not available" in {
//      val playerId = 1
//      val outcomeStrike = MultiStrike
//      val players = List(Player(1, 0, List()), Player(2, 0, List()))
//      val carromBoard = CarromBoard(1,1).right.get
//      val gameState = GameState(carromBoard, players)
//      val expected = InvalidCoinsCount
//
//      val actual = GameState.updateGameState(playerId, outcomeStrike)(gameState)
//
//      actual.isLeft mustEqual true
//      actual.left.get mustEqual expected
//    }
   }
}
