package com.mytaxi.dataengineertestapp.table


import org.apache.spark.sql.types.{IntegerType, StringType, StructType}

object tableSchema {

  val driverSchema = new StructType().
                    add("id", IntegerType, nullable = false).
                    add("date_created", StringType, nullable = false)
                    .add("name", StringType, nullable = false)

  val passSchema = new StructType()
                      .add("id", IntegerType, nullable = false)
                      .add("date_created", StringType, nullable = false)
                      .add("name", StringType, nullable = false)

  val bookingSchema = new StructType()
                        .add("id", IntegerType, nullable = false)
                        .add("date_created", StringType, nullable = false)
                        .add("id_driver", IntegerType, nullable = false)
                        .add("id_passenger", IntegerType, nullable = false)
                        .add("rating", IntegerType, nullable = false)
                        .add("start_date", StringType, nullable = false)
                        .add("end_date", StringType,nullable = false)
                        .add("tour_value", StringType, nullable = false)

}
