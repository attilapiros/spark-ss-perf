import org.apache.spark.sql._

object Perf {

  val FILE_CONTEXT_BASED = "org.apache.spark.sql.execution.streaming.FileContextBasedCheckpointFileManager"

  val ABORTABLE_STREAM_BASED = "org.apache.spark.internal.io.cloud.AbortableStreamBasedCheckpointFileManager"

  def main(args: Array[String]) {
    if (args.size < 2 || !Seq(FILE_CONTEXT_BASED, ABORTABLE_STREAM_BASED).contains(args(1))) {
      System.err.println(s"Usage: Perf <checkpointLocation> <$FILE_CONTEXT_BASED|$ABORTABLE_STREAM_BASED>")
      return
    }
    val spark = SparkSession
      .builder()
      .appName("Spark Structured Streaming Perf")
      .config("spark.sql.streaming.checkpointFileManagerClass", args(1))
      .getOrCreate()

    val rates = spark
      .readStream
      .format("rate")
      .option("rowsPerSecond", 100)
      .load

    val query = rates
      .writeStream
      .option("checkpointLocation", args(0))
      .foreachBatch { (_: DataFrame, _: Long) => }
      .start()

    Thread.sleep(3 * 60 * 1000)

    query.stop()
  }
}
