import org.scalatest.{FunSuite,Matchers}

class MatrixRotationTest extends FunSuite with Matchers{

  test("2*2 matrix with 5 rotations"){

    val matrix = List(List(2,4),
                      List(5,6))
    MatrixRotation.rotate(matrix,5) should be (Array(Array(4,6),Array(2,5)))
  }

  test("3*4 matrix with 4 rotations"){

    val mat = List(List(1,2,3,4),
              List(5,6,7,8),
              List(9,10,11,12))
    MatrixRotation.rotate(mat,4) should be (Array(Array(8, 12, 11, 10),Array(4, 6, 7, 9),Array(3, 2, 1, 5)))
  }

  test("4*4 matrix with 1 rotations"){
    val mat = List(
      List(1,2,3,4),
      List(5,6,7,8),
      List(9,10,11,12),
      List(13,14,15,16))
    MatrixRotation.rotate(mat,1).deep === (Array(Array(2, 3, 4, 8),List(1, 7, 11, 12),List(5, 6, 10, 16),List(9, 13, 14, 15))).deep
  }

  test("5*4 matrix with 7 rotations"){
    val mat = List(
      List(1,2,3,4),
      List(7,8,9,10),
      List(13,14,15,16),
      List(19,20,21,22),
      List(25,26,27,28))
    MatrixRotation.rotate(mat,7).deep === (Array(
      Array(28, 27, 26, 25),
      Array(22, 9, 5, 19),
      Array(16, 8, 21, 13),
      Array(10, 14, 20, 7),
      Array(4, 3, 2, 1))).deep
  }

  test("5*5 matrix with 1 rotation"){
    val mat = List(
      List(1,2,3,4,30),
      List(7,8,9,10,31),
      List(13,14,15,16,32),
      List(19,20,21,22,33),
      List(25,26,27,28,34))
    MatrixRotation.rotate(mat,7).deep === (Array(
      Array(2, 3, 4, 30,31),
      Array(1, 9, 10, 16,32),
      Array(7, 8, 15,22,33),
      Array(13, 19, 20, 21,34),
      Array(19, 25, 26, 27, 28))).deep
  }

  test("6*8 matrix with 2 rotations"){
    val m =   "11 12 13 14 15 16 17 18," +
              "19 20 21 22 23 24 26 28," +
              "29 30 31 32 33 34 35 36," +
              "37 38 39 40 41 42 43 44,"+
              "45 46 47 48 49 50 51 52,"+
              "53 54 55 56 57 58 59 60"

    val n =   "13 14 15 16 17 18 19 36," +
              "12 22 23 24 26 35 43 44," +
              "11 21 33 41 40 34 51 52," +
              "19 20 32 31 39 42 50 60,"+
              "29 30 38 46 47 48 49 59,"+
              "37 45 53 54 55 56 57 68"
    val mat=m.split(",").toList.map(_.split(" ").toList).map(_.map(_.toInt))
    MatrixRotation.rotate(mat,7).deep === n.split(",").map(_.split(" ")).map(_.map(_.toInt)).deep
  }
}
