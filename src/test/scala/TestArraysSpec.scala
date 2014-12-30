import org.scalatest.FunSuite

/**
 * Created by Aleksey Voronets on 30.12.14.
 */
class TestArraysSpec extends FunSuite
{
    test("fun work correct when array length > n and length is a multiple of n")
    {
        val testData = Array(
            Array(1, 2, 2, 3, 3, 3),
            Array(4,4,4,4, 2,3,3),
            Array(6,6,6,7,7,10,11),
            Array(8,8,8,4,2,3,3))

        val result = Array(Array(3, 4, 2, 1), Array(8, 6, 3, 7, 4, 11, 2, 10))
        assert(TestArrays.fun(testData, 2) === result)
    }

    test("fun work correct when array length < n")
    {
        val testData = Array(Array(1, 2, 2, 3, 3, 3))

        val result = Array(Array(3, 2, 1))
        assert(TestArrays.fun(testData, 2) === result)
    }

    test("fun work correct with empty array")
    {
        val testData = Array(Array[Int]())

        val result = Array(Array())
        assert(TestArrays.fun(testData, 2) === result)
    }

    test("fun work correct when array length > n and length is not a multiple of n")
    {
        val testData = Array(
            Array(1, 2, 2, 3, 3, 3),
            Array(4,4,4,4, 2,3,3),
            Array(6,6,6,7,7,10,11),
            Array(8,8,8,4,2,3,3),
            Array(9,9,9,2,2,1,11,12,13,14,15,16))

        val result = Array(Array(3, 4, 2, 1), Array(8, 6, 3, 7, 4, 11, 2, 10), Array(9,2,1,11,12,13,14,15,16))
        assert(TestArrays.fun(testData, 2) === result)
    }
}
