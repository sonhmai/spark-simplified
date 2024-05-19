package com.spark.simplified.rdd

import com.spark.simplified.SparkContext

class ParallelCollectionRDD[T](
                                sc: SparkContext,
                                private val data: Seq[T],
                                numSlices: Int,
                                locationPrefs: Map[Int, Seq[String]]
                              ) extends RDD {


}
