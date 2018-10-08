package com.nathansottek.cryptotrack.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object PrimitiveFactory {

  /**
   * Generates a random [String] with an optional inclusive lower bound and exclusive upper bound
   * @return A random string
   */
  fun randomString(): String {
    return UUID.randomUUID().toString()
  }

  /**
   * Generates a random number with an optional inclusive lower bound and exclusive upper bound
   * @return A random number, defaulting to between 0 inclusive and 1000 exclusive
   */
  fun randomInt(lower: Int = 0, upper: Int = 1000): Int {
    return ThreadLocalRandom.current().nextInt(lower, upper)
  }
}