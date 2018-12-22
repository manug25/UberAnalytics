/**
  * Created by Manu Gupta on 10/16/2017.
  */

package com.mytaxi.dataengineertestapp

import com.mytaxi.dataengineertestapp.postgres.{DataLoader, SparkConfig}
import com.mytaxi.dataengineertestapp.table.tableSchema
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

    //driverDF.show(10)
   // passengerDF.show(10)
   // bookingDF.show(10)

    val emp= dl.CreateTable(employeePath, tableSchema.empSchema)
    val cost= dl.CreateTable(cost_empPath,tableSchema.costSchema)

    emp.show()
    cost.show()

    emp.createOrReplaceTempView("emp")
    cost.createOrReplaceTempView("cost")

    val result = spark.sql(
      """
        |select t.name,t.total_time,(total_time * cost) from
        |( select name,sum(hour) as total_time from emp group by name)t
        |join cost on t.name=cost.name
      """.stripMargin)

    result.show()

    /*val topDriver= spark.sql(
      """select driver.id, tour_value, avg(rating) as avgr from
        | booking join driver on booking.id_driver=driver.id where rating >= avgr group by driver.id, tour_value order by tour_value desc
      """.stripMargin)

    topDriver.show(10)*/




    /*val topPass = driverDF.join(
      bookingDF, driverDF("driverDF.id")=== bookingDF("bookingDF.id_driver"),"inner")*/
    /*
    driverDF.printSchema()
    bookingDF.printSchema()
    /*driverDF.show(20)*/
    println("-------------------------------------------")
    passengerDF.show(20)
    println("-------------------------------------------")
    bookingDF.show(20)*/

   /* println("Showing Driver")
    val topDriver = bookingDF.join(driverDF, bookingDF.col("C2") <=> driverDF.col("C0")).show()*/
    /* println("Showing Passenger")
     // val topPass = passengerDF.join(bookingDF, passengerDF.col("C0") <=> bookingDF.col("C3")).show()/*.filter("substring(bookingDF.col("C1"),0,4)='2016'")*/
    println("Showing from SQL")
    val topDriver1 = sqlContext.sql("""select driver.C0 as driverId, booking.C4 as avg_rating from driver join booking on driver.C0=booking.C2""").show()

    val topPass1 = sqlContext.sql("""select * from passenger join booking on passenger.C0=booking.C3""").show()
*/
    //val res = sqlContext.sql("select * from driver where substring(C1,0,5)='2016'")

  }
}
