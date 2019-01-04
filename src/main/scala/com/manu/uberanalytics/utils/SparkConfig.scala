/**
  * Created by Manu Gupta on 10/16/2017.
  */

package com.manu.uberanalytics.utils

import org.apache.spark.sql.SparkSession

/**
  * Created by Manu Gupta on 10/15/2017.
  */
trait SparkConfig {

  val spark: SparkSession = SparkSession.builder()
    .appName("UberAnalytics")
    .master("local[4]")
    .config("spark.sql.warehouse.dir", "file:///C:/tmp/spark-warehouse")
    .config("spark.shuffle.spill","false")
    .config("spark.rdd.compress", "true")
    .config("spark.storage.memoryFraction", "1")
    .config("spark.shuffle.consolidateFiles", "true")
    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .config("spark.sql.crossJoin.enabled", "true")
    .enableHiveSupport().getOrCreate()
}
