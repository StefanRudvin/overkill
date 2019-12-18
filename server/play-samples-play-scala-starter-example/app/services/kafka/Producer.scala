package services.kafka

import java.util.Properties
import org.apache.kafka.clients.producer._

class Producer {
  def produce(): Unit = {
    writeToKafka("events-pubsub")
  }
  def writeToKafka(topic: String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    val record = new ProducerRecord[String, String](topic, "key", "Hi from Kafka eyy")
    producer.send(record)
    producer.close()
    Console.printf("Written brahh")
  }
}