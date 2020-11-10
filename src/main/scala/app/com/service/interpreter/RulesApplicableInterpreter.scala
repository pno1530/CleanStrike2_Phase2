package app.com.service.interpreter

import app.com.domain.Player.PlayerId
import app.com.domain._
import app.com.domain.GameState._
import app.com.service.RulesApplicable

class RulesApplicableInterpreter extends RulesApplicable[PlayerId, GameState] with Rule {

  override def applyRules(playerId: PlayerId): GameState => GameState =
    applyRule(playerId)

}

object RulesApplicable extends RulesApplicableInterpreter

trait Rule {
  def applyRule(playerId: PlayerId): GameState => GameState =
    SuccessiveThreeFoulsRule(playerId) andThen SuccessiveThreeNoPocketRule(playerId)
}

object SuccessiveThreeNoPocketRule extends Rule{
  import Rule._


  def apply(playerId: PlayerId) = (gameState: GameState) =>{
    if(islastThreeNoPockets(playerId, gameState)) gameState.copy(players =
      updatePlayer(ThreeNoStrike, playerId, gameState.players))
    else gameState
  }
  private def islastThreeNoPockets(playerId: PlayerId, gameState: GameState) = {
    lastThreeEntries(playerId, gameState).count(_ == None) == 3
  }
}

object SuccessiveThreeFoulsRule extends Rule{
  import Rule._

  def apply(playerId: PlayerId) = (gameState: GameState) =>{
    if(isLastThreeFouls(playerId)(gameState)) gameState.copy(players =
      updatePlayer(ThreeFoulStrike, playerId, gameState.players))
    else gameState
  }

  private def isLastThreeFouls(playerId: PlayerId) = (gameState: GameState) => {
    lastThreeEntries(playerId, gameState).count(stikeType =>
      (stikeType == ThreeFoulStrike || stikeType == ThreeNoStrike ||
        stikeType == DefunctStrike || stikeType == StrikerStrike)) == 3
  }
}

object Rule{

  def lastThreeEntries = (playerId: PlayerId, gameState: GameState) => {
    val striker = gameState.players.find(_.id == playerId).get
    striker.strikes.takeRight(3)
  }
}
