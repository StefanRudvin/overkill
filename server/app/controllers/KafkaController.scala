package controllers

import javax.inject._

import play.api.mvc._
import services.Counter
import services.kafka.Producer
import services.kafka.Consumer

/**
 * This controller demonstrates how to use dependency injection to
 * bind a component into a controller class. The class creates an
 * `Action` that shows an incrementing count to users. The [[Counter]]
 * object is injected by the Guice dependency injection system.
 */
@Singleton
class KafkaController @Inject()(cc: ControllerComponents,
                                counter: Counter,
                                producer: Producer,
                                consumer: Consumer) extends AbstractController(cc) {

  def publish(message: Option[String]) = Action {
    Console.println("Writing message: " + message.get)
    producer.produce(message.get)
    Ok("Ok")
  }

  def consume() = Action {
    consumer.consume()
    Ok("ok")
  }
}
