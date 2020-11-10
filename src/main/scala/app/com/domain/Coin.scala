package app.com.domain

trait Coin

case object Black extends Coin{
  def apply(count: Int, maxBlackCoins: Int): Either[Error, List[Black.type]] = for{
    blackCoins <- blackCoins(count, maxBlackCoins)
  } yield List.fill(blackCoins)(Black)

  private def blackCoins: (Int, Int) => Either[Error, Int] = (count: Int, maxBlackCoins: Int) =>
    if(count < 0 || count > maxBlackCoins) Left(InvalidBlackCoinsCount)
    else Right(count)
}
case object Red extends Coin{

  def apply(count: Int, maxRedCoins: Int): Either[Error, List[Red.type]] = for{
    redCoins <- redCoins(count, maxRedCoins)
  } yield List.fill(redCoins)(Red)

  private def redCoins: (Int, Int) => Either[Error, Int] = (count: Int, maxRedCoins: Int) =>
    if(count < 0 || count > maxRedCoins) Left(InvalidRedCoinsCount)
    else Right(count)
}
