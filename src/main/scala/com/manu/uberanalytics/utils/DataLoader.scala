/**
  * Created by Manu Gupta on 10/16/2017.
  */

package com.manu.uberanalytics.utils

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.types.StructType

/**
  * Created by Manu Gupta on 10/15/2017.
  */
class DataLoader extends SparkConfig {

  def CreateTable(filePath: String, schema: StructType): DataFrame = {

    val df = spark.read.format("csv").schema(schema).load(filePath)
    df
  }
}

