import scala.annotation.tailrec

/**
  * Rotate 2D matrix anticlockwise
  */
object MatrixRotation {


  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().takeWhile(_.nonEmpty).map(_.split(" ")).map(_.map(_.toInt).toList).toList
    val M = input(0)(0)
    val N = input(0)(1)
    val R = input(0)(2)
    require(M >= 2 ,"Matrix rows should be greater than 1")
    require(N <= 300 ,"Matrix columns should not exceed 300")
    require(input.tail.size == M, "number of rows do not match")
    require(input(1).length == N, "number of columns do not match")
    require(R >= 1 && R <= Math.pow(10,9), "number of rotations must be greater than 1")
    rotate(input.tail,R)
  }

  def rotate(mat:List[List[Int]],K:Int)={
    val M = mat.length
    val N = mat(0).length
    val transformed = rotateMatrix(transpose(mat).toList,K)
    val rotated = buildMatrix(transformed,M,N)
    rotated.map(_.mkString(" ")) foreach println
    rotated
  }

  /**
    * Transpose 2D matrix in a linear form as a list of
    * elements on the side of rectangle as shown below
    * From outer to inner rectangle in top,right,bottom,left order
    * 0 1 2
    * 3 4 5
    * 6 7 8
    * 9 10 11
    * would return List(List(0,1,2,5,8,11,10,9,6,3), List(4,7))
    * @param lst 2D matrix
    * @return transposed matrix
    */
  def transpose(lst:List[List[Int]]):List[List[Int]]={
    @tailrec
    def trans(ls:List[List[Int]],ac:List[List[Int]]):List[List[Int]]={
      if(ls.isEmpty) ac
      else if(ls.length == 1) ac ++ ls
      else {
        val g = ls.drop(1).dropRight(1)
        val p = ac ++ List(ls(0):::g.map(_.last):::ls(ls.length-1).reverse ::: g.map(_.head).reverse)
        trans(ls.drop(1).dropRight(1).map(_.tail.dropRight(1)),p)
      }
    }
    trans(lst,List())
  }

  @tailrec
  def shiftRight(l:List[Int], n:Int = 1):List[Int] = {
    if(n==1) l.tail :+ l.head
    else shiftRight(l.tail :+ l.head, n-1)
  }

  /**
    * Rotate transposed Matrix n times
    * @param l transposed matrix
    * @param n number of rotations
    * @return
    */
  def rotateMatrix(l:List[List[Int]],n:Int) =  l.filter(!_.isEmpty).map(shiftRight(_,n))

  /**
    * Build matrix from transposed list
    * @param t list
    * @param M no of rows
    * @param N no of columns
    * @return Array
    */
  def buildMatrix(t:List[List[Int]],M:Int,N:Int) ={

    val q = Array.ofDim[Int](M,N)

    for(w<-0 until t.length){
      val C = N -2*w
      val D = M -2*w
      for(i<-0 until C-1) q(w)(i+w) = t(w)(i)
      for(j<-0 until D)  q(j+w)(C-1+w) = t(w)(C-1+j)
      if(t(w).length > C+D-1) {
        for (k <- 0 until C - 1) q(D - 1 + w)(C - k - 2 + w) = t(w)(C + D - 1 + k)
        for (l <- 0 until D - 2) q(D - 2 - l + w)(w) = t(w)(2 * (C - 1) + D + l)
      }
    }
    q
  }
}
