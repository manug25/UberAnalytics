package com.manu.uberanalytics.kafka

import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}

object KafkaConfig {

  val config: Config = ConfigFactory.load()

  val props = new Properties()

  //Setting Producer properties
  props.put("bootstrap.servers", "localhost:9092")
  props.put("acks", "all")
  props.put("retries","1")
  props.put("auto.offset.reset", "latest")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "prg.apache.kafka.common.serialization.StringSerializer")
}
