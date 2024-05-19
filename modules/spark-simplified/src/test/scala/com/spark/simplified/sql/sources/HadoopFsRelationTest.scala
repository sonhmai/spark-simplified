package com.spark.simplified.sql.sources

class HadoopFsRelationTest {

    lazy val testDF = (1 to 3)
      .map(i => (i, s"val_$i"))
      .toDF("a", "b")

}
