import scala.collection.mutable.ArrayBuffer

/**
 * Created by Aleksey Voronets on 30.12.14.
 */
object TestArrays
{
    def fun(arr: Array[Array[Int]], n: Int): Array[Array[Int]] =
    {
        def iterate(arr : Array[Array[Int]], n: Int, acc: ArrayBuffer[Array[Int]]): Array[Array[Int]] =
        {
            if(arr.length <= n)
                (acc ++ ArrayBuffer(sortByPopularity(arr.flatten).take(10))).toArray
            else
                iterate(arr.drop(n), n, acc ++ ArrayBuffer(sortByPopularity(arr.take(n).flatten).take(10).toArray))
        }

        iterate(arr, n, ArrayBuffer())
    }

    def sortByPopularity(arr: Array[Int]): Array[Int] =
    {
        val result = arr.groupBy(el => el).map(v => (v._1, v._2.length)).toSeq.sortBy(_._2)
        result.reverse.map(_._1).toArray
    }

}
