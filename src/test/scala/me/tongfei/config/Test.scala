package me.tongfei.config

/**
 * @author Tongfei Chen
 */
object Test extends App {

  Config.loadPropertiesFromFile("./src/test/resources/test.properties")

  println(Config("A"))

}
