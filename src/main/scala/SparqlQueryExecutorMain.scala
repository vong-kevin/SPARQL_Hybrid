package main.scala

object SparqlQueryExecutorMain extends App {
  // load data set
  val sh = SparqlQueryExecutor(LabelBase.INPUT)
  sh.load()
  sh.showModel()
}