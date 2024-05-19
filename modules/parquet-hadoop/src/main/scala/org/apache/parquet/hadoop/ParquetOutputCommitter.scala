package org.apache.parquet.hadoop

import org.apache.hadoop.fs.Path
import org.apache.hadoop.mapreduce.TaskAttemptContext

class ParquetOutputCommitter {

}

/**
  *
  * @param outputPath
  * @param context
  */
private class MarkingFileOutputCommitter(
                                          outputPath: Path,
                                          context: TaskAttemptContext
                                        ) extends FileOutputComitter(outputPath, context) {

}