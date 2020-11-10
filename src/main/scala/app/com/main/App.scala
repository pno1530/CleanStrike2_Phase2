package app.com.main

import app.com.service.interpreter.GameEngineInterpreter


object App {
  import GameEngineInterpreter._

  def main(args: Array[String]): Unit = {
    val inputFilePath = "test/resources/Input.txt"
    print("res--"+ playGame(inputFilePath))
  }
}
