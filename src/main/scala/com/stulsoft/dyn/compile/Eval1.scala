/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.dyn.compile

import com.typesafe.scalalogging.LazyLogging
import scala.reflect.runtime.universe
import scala.tools.reflect.ToolBox

/**
  * @author Yuriy Stul
  */
object Eval1 extends App with LazyLogging {
  logger.info("==>main")
  val tb = universe.runtimeMirror(getClass.getClassLoader).mkToolBox()
  tb.eval(tb.parse("""println("hello!")"""))

  val r1 = tb.eval(tb.parse("""2 * 3"""))
  logger.info(s"r1 = $r1")

  logger.info("<==main")
}
