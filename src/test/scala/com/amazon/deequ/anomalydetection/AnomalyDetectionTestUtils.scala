package com.amazon.deequ.anomalydetection

import scala.util.matching.Regex

/**
  * Utilities to test Anomaly Detection methods and related modules
  */
object AnomalyDetectionTestUtils {

  private val numericalValueRegex: Regex = """([+-]?([0-9]*[.])?[0-9]+([Ee][0-9]+)?)""".r

  /**
    * Finds the first numerical value in a string
    *
    * @param details The string containing a numerical value
    * @throws IllegalArgumentException Thrown if no value could be found
    * @return The value itself
    */
  def firstDoubleFromString(details: String): Double = {
    val firstValue = numericalValueRegex.findFirstIn(details)

    require(firstValue.isDefined, "Input string did not contain a numerical value")

    firstValue.get.toString.toDouble
  }

  /**
    * Finds the first three numerical values in a string
    *
    * @param details The string containing at least three numerical values
    * @throws IllegalArgumentException Thrown if less than 3 values could be found
    * @return The values themselves
    */
  def firstThreeDoublesFromString(details: String): (Double, Double, Double) = {
    val values = numericalValueRegex.findAllIn(details).toVector.map(_.toString.toDouble)

    require(values.length >= 3, "Input string did not contain at least 3 numerical values.")

    (values(0), values(1), values(2))
  }
}
