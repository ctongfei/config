package me.tongfei.config

import java.nio.file._
import java.util._

import scala.collection._
import scala.collection.JavaConversions._

/**
 * A configuration manager.
 *
 * @since 0.1.0
 * @author Tongfei Chen
 */
object Config extends DefaultMap[String, String] {

  private[this] val kvs = mutable.HashMap[String, String]()

  def loadPropertiesFromResource(propFileName: String) = {
    val newProperties = new Properties
    val istr = getClass.getResourceAsStream(propFileName)
    if (istr != null) {
      newProperties.load(istr)
      for (e ← newProperties.entrySet)
        kvs += e.getKey.asInstanceOf[String] → e.getValue.asInstanceOf[String]
      System.err.println(s"Properties loaded from resource $propFileName.")
    }
  }

  def loadPropertiesFromFile(filename: String) = {
    val newProperties = new Properties
    val istr = Files.newInputStream(Paths.get(filename))
    if (istr != null) {
      newProperties.load(istr)
      for (e ← newProperties.entrySet)
        kvs += e.getKey.asInstanceOf[String] → e.getValue.asInstanceOf[String]
      System.err.println(s"Properties loaded from file $filename.")
    }
  }

  def loadSystemProperties() = {
    for (e ← System.getProperties.entrySet)
      kvs += e.getKey.asInstanceOf[String] → e.getValue.asInstanceOf[String]
    System.err.println(s"Properties loaded from System.getProperties.")
  }


  // DEFAULT LOADING BEHAVIOR ON OBJECT CREATION:
  loadPropertiesFromResource("/config.properties")
  loadSystemProperties()

  // CONFORMING TO A MAP

  def get(key: String) = kvs get key
  def iterator = kvs.iterator
}
