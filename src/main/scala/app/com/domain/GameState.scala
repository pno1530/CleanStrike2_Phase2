package app.com.domain

import app.com.domain.Player.PlayerId

case class GameState(carromBoard: CarromBoard, players: List[Player])

object GameState{

  def updateGameState(playerId: PlayerId, outcomeStrike: OutcomeStrike) = (gameState: GameState) => {
     for {
      carrom <- updateCarromBoard(outcomeStrike, gameState.carromBoard)
    }  yield GameState(carrom, updatePlayer(outcomeStrike, playerId, gameState.players))
  }

  private def updateCarromBoard(outcomeStrike: OutcomeStrike, carromBoard: CarromBoard) = {
    if ( outcomeStrike == Strike || outcomeStrike == MultiStrike )
      carromBoard.dropCoins(outcomeStrike.dropCoins, Black)
    else if(outcomeStrike == RedStrike)
      carromBoard.dropCoins(outcomeStrike.dropCoins, Red)
    else Right(carromBoard)
  }

  def updatePlayer = (strikeType: StrikeType, playerId: PlayerId, players: List[Player]) => {
    val striker = players.find(_.id == playerId).get
    val updatedPoints = striker.points + strikeType.points
    val updatedStrikes = striker.strikes :+ strikeType
    val updatedPlayer = striker.copy(points = updatedPoints, strikes = updatedStrikes)
    players.filterNot(_.id == playerId) :+ updatedPlayer
  }
}
