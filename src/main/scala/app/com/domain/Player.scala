package app.com.domain

import app.com.domain.Player._

case class Player private(id: PlayerId, points: Points = 0, strikes: Strikes = List())

object Player{
  type Points = Int
  type Strikes = List[StrikeType]
  type PlayerId = Int

  def apply(count: Int): Either[Error, List[Player]] =  for {
    validPlayers <- validatePlayers(players(count))
  } yield validPlayers

  private def players: PlayerId => List[Player] = (count: Int) => List.tabulate(count)(n => Player(n+1))

  private def validatePlayers: List[Player] => Either[Error, List[Player]] = (players: List[Player]) =>
    if(players.map(_.id).distinct.size == players.size) Right(players)
    else Left(InvalidPlayersCount)
}