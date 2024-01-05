#!/usr/bin/env bash

# NOTE: This exact class name is matched downstream by SparkSubmit.
# Any changes need to be reflected there.
CLASS="org.apache.spark.deploy.history.HistoryServer"

# flatten the below command in official Spark script for understanding
# exec "${SPARK_HOME}/sbin"/spark-daemon.sh start $CLASS 1 "$@"
