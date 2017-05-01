/**
  * Created by SAGAR on 01/05/2017.
  */

object Fibonacci {

  lazy val fib : Stream[BigInt] = 0 #:: fib.scanLeft(BigInt(1))(_+_)

  def fibonacci(n:Int) = fib.take(n+1).reverse.head %(BigInt(10).pow(8) + 7)

  def main(args: Array[String]): Unit = {
    val input = io.Source.stdin.getLines().takeWhile(_.nonEmpty).map(_.toInt).toList.drop(1)
    input.map(k=> fibonacci(k) ) foreach println
  }
}
