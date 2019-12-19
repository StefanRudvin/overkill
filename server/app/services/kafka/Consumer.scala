package services.kafka

import java.time.Duration
import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import scala.jdk.CollectionConverters._

class Consumer {
  private val _topicName: String = "events-pubsub";

//  def main(args: Array[String]): Unit = {

  def consume(): Unit = {
    val properties = new Properties()
    properties.put("bootstrap.servers", "localhost:9092")
    properties.put("group.id", "consumer-tutorial")
    properties.put("key.deserializer", classOf[StringDeserializer])
    properties.put("value.deserializer", classOf[StringDeserializer])

    val kafkaConsumer = new KafkaConsumer[String, String](properties)
    kafkaConsumer.subscribe(Collections.singletonList(_topicName))

    while (true) {
      val results = kafkaConsumer.poll(Duration.ofMillis(200)).asScala
      for (ConsumerRecords <- results) {
        println(ConsumerRecords)
      }
    }
  }
}

//class Consumer {
//
//  private val _topicName: String = "events-pubsub";
//
//  def consume(): Unit = {
//
//    val props:Properties = new Properties()
//    props.put("group.id", "test")
//    props.put("bootstrap.servers","localhost:9092")
//    props.put("key.deserializer",
//      "org.apache.kafka.common.serialization.StringDeserializer")
//    props.put("value.deserializer",
//      "org.apache.kafka.common.serialization.StringDeserializer")
//    props.put("enable.auto.commit", "true")
//    props.put("auto.commit.interval.ms", "1000")
//    val consumer = new KafkaConsumer(props)
//    val topics = List("topic_text")
//    try {
//      consumer.subscribe(Collections.singletonList(_topicName))
//      while (true) {
//        val records = consumer.poll(100L)
//        asJavaCollection(records)
//
//
//        for (record <- records) {
//          println("Topic: " + record.topic() +
//            ",Key: " + record.key() +
//            ",Value: " + record.value() +
//            ", Offset: " + record.offset() +
//            ", Partition: " + record.partition())
//        }
//      }
//    }catch{
//      case e:Exception => e.printStackTrace()
//    }finally {
//      consumer.close()
//    }
//  }
//}