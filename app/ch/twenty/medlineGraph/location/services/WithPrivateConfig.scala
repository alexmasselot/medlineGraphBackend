package ch.twenty.medlineGraph.location.services

import java.io.File

import com.typesafe.config.ConfigFactory

/**
 * @author Alexandre Masselot.
 */
trait WithPrivateConfig {
  val config = ConfigFactory.parseFile(new File("conf/private.conf"))

}