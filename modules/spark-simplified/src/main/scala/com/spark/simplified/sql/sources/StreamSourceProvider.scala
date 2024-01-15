package com.spark.simplified.sql.sources

import com.spark.simplified.sql.physical.streaming.Source
import org.apache.spark.sql.types.StructType

/**
 * ::Experimental::
 * Implemented by objects that can produce a streaming `Source` for a specific format or system.
 *
 * @since 2.0.0
 */
trait StreamSourceProvider {
  def sourceSchema(): (String, StructType)

  def createSource(): Source
}
