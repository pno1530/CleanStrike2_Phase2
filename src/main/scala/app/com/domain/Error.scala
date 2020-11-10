package app.com.domain

trait Error

case object InvalidBlackCoins extends Error
case object InvalidBlackCoinsCount extends Error
case object InvalidRedCoins extends Error
case object InvalidRedCoinsCount extends Error
case object InvalidPlayersCount extends Error
case object InvalidPlayerTurn extends Error
case object InvalidFile extends Error
case object InvalidOutcome extends Error
case object InsufficientTurns extends Error
case object InvalidCoinsCount extends Error
