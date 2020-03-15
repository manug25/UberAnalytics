package com.manu.uberanalytics.kafka

import org.apache.spark.sql.DataFrame

object Producer {

  def toKafkaTopic(topicName: String, payload: DataFrame )= {
   try{
     payload.write
       .format("kafka")
       .option("topic", topicName)
       .option("kafka.bootstrap.server", "localhost:9092")
       .option("checkpointLocation", "/batchCheckpoint")
   } catch {
     case e: Exception => e.printStackTrace()
   }
  }

  /*val myProducer: KafkaProducer[String, String] = new KafkaProducer(KafkaConfig.props)

  val myRecord: ProducerRecord[String, String] = new ProducerRecord("myTopic","C01","My first Kafka Producer message")

  try{
    for (i <- 0 to 150){
      myProducer.send(new ProducerRecord("myTopic",Integer.toString(i),"My Kafka Producer message" + Integer.toString(i)))
    }
  } catch {
    case e: Exception => e.printStackTrace()
  }finally {
    myProducer.close()
  }*/
}
