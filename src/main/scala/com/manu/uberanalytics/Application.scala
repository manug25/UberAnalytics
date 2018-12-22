/**
  * Created by Manu Gupta on 10/16/2017.
  */

package com.manu.uberanalytics

import com.manu.uberanalytics.postgres.{DataLoader, SparkConfig}
import com.manu.uberanalytics.table.tableSchema
import org.apache.spark.sql.functions._

object Application  extends SparkConfig{

  def main(args: Array[String]): Unit = {

    val driverPath = "F:\\data-engineer-applicant-test-7\\src\\main\\scala\\resources\\driver.csv"
    val passengerPath = "F:\\data-engineer-applicant-test-7\\src\\main\\scala\\resources\\passenger.csv"
    val bookingPath = "F:\\data-engineer-applicant-test-7\\src\\main\\scala\\resources\\booking.csv"

    val employeePath= "F:\\data-engineer-applicant-test-7\\src\\main\\scala\\resources\\employee.csv"
    val cost_empPath= "F:\\data-engineer-applicant-test-7\\src\\main\\scala\\resources\\cost.csv"


    val driverTableName = "driver"
    val passengerTableName = "passenger"
    val bookingTableName = "booking"
    val connectionUrl = "jdbc:postgresql://localhost:5432/"
    val dl = new DataLoader
    /*dl.toDb(sqlContext, connectionUrl, driverTableName, driverPath)*/

    val driverDF1 = dl.CreateTable(driverPath, tableSchema.driverSchema)
    val from_pat1="yyyy-mm-dd hh:mm"
    val driverDF = driverDF1
                  .withColumn("date_createdTS", unix_timestamp(driverDF1("date_created"), from_pat1).cast("timestamp"))
                  .drop("date_created")
    driverDF.createOrReplaceTempView("driver")

    val passengerDF1 = dl.CreateTable( passengerPath, tableSchema.passSchema)

    val passengerDF = passengerDF1
                      .withColumn("date_createdTS", unix_timestamp(passengerDF1("date_created"), from_pat1).cast("timestamp"))
                      .drop("date_created")
    passengerDF.createOrReplaceTempView("passenger")

    val bookingDF1 = dl.CreateTable(bookingPath, tableSchema.bookingSchema)

    val bookingDF = bookingDF1
                    .withColumn("date_createdTS", unix_timestamp(bookingDF1("date_created"), from_pat1).cast("timestamp"))
                    .drop("date_created")
                    .withColumn("start_dateTS", unix_timestamp(bookingDF1("start_date"), from_pat1).cast("timestamp"))
                    .drop("start_date")
                    .withColumn("end_dateTS", unix_timestamp(bookingDF1("end_date"), from_pat1).cast("timestamp"))
                    .drop("end_date")
    bookingDF.createOrReplaceTempView("booking")

    val topDriver= spark.sql(
      """select driver.id, tour_value, avg(rating) as avgr from
        | booking join driver on booking.id_driver=driver.id where rating >= avgr group by driver.id, tour_value order by tour_value desc
      """.stripMargin)

    topDriver.show(10)


    val topPass = driverDF.join(
      bookingDF, driverDF("driverDF.id")=== bookingDF("bookingDF.id_driver"),"inner")

  }
}
