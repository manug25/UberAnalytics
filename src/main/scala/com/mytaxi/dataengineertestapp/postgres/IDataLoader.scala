/**
  * Created by Manu Gupta on 10/16/2017.
  */

package com.mytaxi.dataengineertestapp.postgres

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SQLContext


trait IDataLoader {
  /**
    * Load data with any format to any Database's type.
    *
    * @param sqlContext    :
    * @param connectionUrl : connection url to database
    * @param tableName     : table name in db.
    * @param filePath      : file to upload
    */
  def toDb(sqlContext: SQLContext, connectionUrl: String, tableName: String, filePath: String): Unit
}

