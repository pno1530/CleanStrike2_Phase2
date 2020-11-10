package app.com.service.Syntax

import app.com.service.Reader
import app.com.domain.{Error, InvalidFile}
import app.com.service.interpreter.common._

import scala.io.Source
import scala.util.Try

object InputReaderInstances{

  implicit def fileReader: Reader[Input, List[String]] = new Reader[Input, List[String]] {
    override def read(input: Input): Either[Error, List[String]] =
      Try(
        Source.fromFile(input).getLines().toList
      ).fold(_ => Left(InvalidFile),
        lines => Right(lines))

  }
}


object Reader{

  implicit class ReadOps[A](input: A){
    def read[B](implicit reader: Reader[A, B]) =
      reader.read(input)
  }
}

