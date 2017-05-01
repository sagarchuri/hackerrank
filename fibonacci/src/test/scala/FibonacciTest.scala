/**
  * Created by SAGAR on 01/05/2017.
  */
import org.scalatest.{Matchers,FunSuite}
class FibonacciTest extends FunSuite with Matchers{

  test("Fibonacci series till 10"){
    Fibonacci.fib.take(10).toList should  be (List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34))
  }

  test("Fibonacci number at 10"){
    Fibonacci.fibonacci(10) should  be (55)
  }

  test("Fibonacci number at 100"){
    Fibonacci.fibonacci(100) should  be (24278230)
  }
}
