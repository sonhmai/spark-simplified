package com.spark.simplified.kafka

import com.spark.simplified.sql.connector.read.{InputPartition, Offset}
import com.spark.simplified.sql.connector.stream.MicroBatchStream

/**
 * A [[MicroBatchStream]] that reads data from Kafka.
 *
 * The [[KafkaSourceOffset]] is the custom [[Offset]] defined for this source that contains
 * a map of TopicPartition -> offset. Note that this offset is 1 + (available offset). For
 * example if the last record in a Kafka topic "t", partition 2 is offset 5, then
 * KafkaSourceOffset will contain TopicPartition("t", 2) -> 6. This is done keep it consistent
 * with the semantics of `KafkaConsumer.position()`.
 *
 * Zero data lost is not guaranteed when topics are deleted. If zero data lost is critical, the user
 * must make sure all messages in a topic have been processed when deleting a topic.
 *
 * There is a known issue caused by KAFKA-1894: the query using Kafka maybe cannot be stopped.
 * To avoid this issue, you should make sure stopping the query before stopping the Kafka brokers
 * and not use wrong broker addresses.
 */
class KafkaMicroBatchStream extends MicroBatchStream {

  override def latestOffset(): Offset = ???

  override def planInputPartitions(start: Offset,
                                   end: Offset
  ): Array[InputPartition] = ???
}
