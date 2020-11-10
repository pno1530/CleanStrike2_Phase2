package app.com.service

trait GameEngine[Input, Error, Result] {

    def playGame(inputFilePath: Input): Either[Error, Result]

}