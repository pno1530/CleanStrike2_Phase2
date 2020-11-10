package app.com.domain

import app.com.domain.Player.PlayerId

case class Turn private(playerId: PlayerId, outcomeStrike: OutcomeStrike)

object Turn {

  def apply(players: Int, outcomes: List[OutcomeStrike]) =
    outcomes.zipWithIndex.map(t => {
      val remainder = t._2 % players
      new Turn(remainder+1, t._1)
    })
}