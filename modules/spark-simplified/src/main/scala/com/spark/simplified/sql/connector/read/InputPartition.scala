package com.spark.simplified.sql.connector.read

/**
 * A serializable representation of an input partition returned by
 * planInputPartitions() and the corresponding ones in streaming .
 * <p>
 * Note that {@link InputPartition} will be serialized and sent to executors, then
 * {@link PartitionReader} will be created by
 * {@link PartitionReaderFactory#createReader(InputPartition)} or
 * {@link PartitionReaderFactory#createColumnarReader(InputPartition)} on executors to do
 * the actual reading. So {@link InputPartition} must be serializable while {@link PartitionReader}
 * doesn't need to be.
 *
 * @since 3.0.0
 */
 */
trait InputPartition extends Serializable {


}
