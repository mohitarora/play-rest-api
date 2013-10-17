package service

import com.google.inject.Singleton

/**
 * A simple service that can increment a number.
 */
@Singleton
class CountingService {

  /**
   * Increment the given number by one.
   */
  def increment(count: Int) = count + 1

}
