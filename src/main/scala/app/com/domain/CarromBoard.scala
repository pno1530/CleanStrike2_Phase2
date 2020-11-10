package app.com.domain

case class CarromBoard private(coins: List[Coin]){
  def isEmpty = coins.isEmpty

  def dropCoins(count: Int, coin: Coin) = {
    val matchingCoins = coins.filter(_ == coin)
    CarromBoard(matchingCoins.drop(count) ++ coins.filterNot(_ == coin))
  }

}

object CarromBoard{

  val maxBlackCoins = 9
  val maxRedCoins = 1

  def apply(blackCoins: Int = maxBlackCoins, redCoins: Int = maxRedCoins): Either[Error, CarromBoard] = for{
    blackCoinsCount <- validBlackCoinsCount(blackCoins)
    redCoinsCount <- validRedCoinsCount(redCoins)
    blackCoins <- Black(blackCoinsCount, maxBlackCoins)
    redCoins <- Red(redCoinsCount, maxRedCoins)
    carrom <- CarromBoard(blackCoins ++ redCoins)
  } yield carrom

  private def validBlackCoinsCount: Int => Either[Error, Int] = (count: Int) =>
    if(count < 0 || count > maxBlackCoins) Left(InvalidBlackCoinsCount)
    else Right(count)

  private def validRedCoinsCount: Int => Either[Error, Int] = (count: Int) =>
    if(count < 0 || count > maxRedCoins) Left(InvalidRedCoinsCount)
    else Right(count)

  def apply(coins: List[Coin]): Either[Error, CarromBoard] = for{
    blackCoins <- validBlackCoins(coins.filter(_==Black))
    redCoins <- validRedCoins(coins.filter(_==Red))
  } yield new CarromBoard(blackCoins ++ redCoins)

  private def validBlackCoins: List[Coin] => Either[Error, List[Coin]] = (coins: List[Coin]) => {
    val blackCoins = coins.count(_ == Black)
    if( blackCoins > maxBlackCoins || blackCoins < 0) Left(InvalidBlackCoins)
    else Right(coins)
  }

  private def validRedCoins: List[Coin] => Either[Error, List[Coin]] = (coins: List[Coin]) => {
    val redCoins = coins.count(_ == Red)
    if( redCoins > maxRedCoins || redCoins < 0) Left(InvalidRedCoins)
    else Right(coins)
  }
}
