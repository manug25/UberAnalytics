/**
  * Created by Manu Gupta on 10/16/2017.
  */

package com.mytaxi.dataengineertestapp.postgres

import org.apache.spark.sql.SparkSession

/**
  * Created by Manu Gupta on 10/15/2017.
  */
abstract class SparkConfig {

  /*val conf = new SparkConf().setAppName("MyTaxi").setMaster("local").set("spark.driver.allowMultipleContexts", "true")
  val sc = new SparkContext(conf)
   val sqlContext = new SQLContext(sc)*/

  val spark = SparkSession.builder().appName("MyTaxi").master("local[4]").config("spark.sql.warehouse.dir", "file:///C:/tmp/spark-warehouse").getOrCreate()
}
