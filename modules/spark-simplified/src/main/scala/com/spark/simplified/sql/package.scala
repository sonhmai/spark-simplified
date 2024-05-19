package com.spark.simplified

import com.spark.simplified.sql.catalyst.Row

package object sql {

  type DataFrame = Dataset[Row]
}
