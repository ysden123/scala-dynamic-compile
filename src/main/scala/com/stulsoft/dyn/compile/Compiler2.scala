/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.dyn.compile

import com.typesafe.scalalogging.LazyLogging

import scala.io.Source
import scala.reflect.runtime.universe
import scala.tools.reflect.ToolBox

/**
  * @author Yuriy Stul
  */
object Compiler2 extends App with LazyLogging {
  logger.info("==>main")
  val src = Source.fromResource("PersonData.txt").getLines().mkString("\n")
  val tb = universe.runtimeMirror(getClass.getClassLoader).mkToolBox()
  val clazz = tb.compile(tb.parse(src))().asInstanceOf[Class[_]]
  val ctor = clazz.getDeclaredConstructors()(0)
  val instance = ctor.newInstance()

  //  clazz.getDeclaredMethods.foreach(m => println(m))
  //  clazz.getDeclaredFields.foreach(println)

  val field = clazz.getDeclaredMethod("field").invoke(instance)
  logger.info(s"field = $field")
  logger.info("<==main")
}
