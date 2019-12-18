package controllers

import javax.inject._

import play.api.mvc._
import services.Counter
import services.kafka.Producer

/**
 * This controller demonstrates how to use dependency injection to
 * bind a component into a controller class. The class creates an
 * `Action` that shows an incrementing count to users. The [[Counter]]
 * object is injected by the Guice dependency injection system.
 */
@Singleton
class KafkaController @Inject() (cc: ControllerComponents,
                                 counter: Counter,
                                 producer: Producer) extends AbstractController(cc) {

  /**
   * Create an action that responds with the [[Counter]]'s current
   * count. The result is plain text. This `Action` is mapped to
   * `GET /count` requests by an entry in the `routes` config file.
   */
  def publish = Action {
    producer.produce()
    Ok(counter.nextCount().toString)
  }

}
