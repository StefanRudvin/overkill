package services.kafka

import java.util.Properties
import org.apache.kafka.clients.producer._

class Producer {

  private val _topicName: String = "events-pubsub";

  def produce(message: String): Unit = {
    writeToKafka(message);
  }

  def writeToKafka(message: String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    val record = new ProducerRecord[String, String](_topicName, "key", message)
    producer.send(record)
    producer.close()
  }
}