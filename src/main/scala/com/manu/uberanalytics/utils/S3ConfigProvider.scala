package com.manu.uberanalytics.utils

object S3ConfigProvider extends SparkConfig {
  val accessKeyId = System.getenv("AWS_ACCESS_KEY_ID")
  val secretAccessKey = System.getenv("AWS_SECRET_ACCESS_KEY")
  spark.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", accessKeyId)
  spark.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", secretAccessKey)
}
