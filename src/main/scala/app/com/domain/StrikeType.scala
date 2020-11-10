package app.com.domain

import scala.util.Try

trait StrikeType{
  def points: Int
}

trait OutcomeStrike extends StrikeType{
  def dropCoins: Int
}

case object Strike extends OutcomeStrike{
  val dropCoins = 1
  val points = 1
}
case object MultiStrike extends OutcomeStrike{
  val dropCoins = 2
  val points = 2
}
case object RedStrike extends OutcomeStrike{
  val dropCoins = 1
  val points = 3
}
case object StrikerStrike extends OutcomeStrike {
  val dropCoins = 0
  val points = -1
}
case object DefunctStrike extends OutcomeStrike {
  val dropCoins = 1
  val points = -2
}
case object ThreeNoStrike extends StrikeType {
  val points = -1
}
case object ThreeFoulStrike extends StrikeType {
  val points = -1
}

case object None extends OutcomeStrike {
  val dropCoins = 0
  val points: Int = 0
}

object OutcomeStrike{

  private def allOutcomes = Map(
    "1"-> Strike,
    "2"-> MultiStrike,
    "3"-> RedStrike,
    "4"-> StrikerStrike,
    "5"-> DefunctStrike,
    "6"-> None
  )

  def apply(list: List[String]): Either[Error, List[OutcomeStrike]] =
    outcomeStrike(list.map(OutcomeStrike(_)))

  private def apply(outcome: String): Either[Error, OutcomeStrike] =
    Try(allOutcomes(outcome))
      .fold(_ => Left(InvalidOutcome),
        outcome => Right(outcome))

  import app.com.service.Sequence._

  private def outcomeStrike(list: List[Either[Error, OutcomeStrike]]): Either[Error, List[OutcomeStrike]] =
    sequence(list)
}