package main.scala

import org.apache.jena.rdf.model.{ModelFactory, Statement}

class SparqlQueryExecutor(val dbSource: String) {
  val model = ModelFactory.createDefaultModel()
  val typeProperty = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type"

  def load() =  model.read(dbSource)
  def showModel() : Unit =  println("is empty ? "+model.isEmpty())
  def size() = model.size()

  def partitionedJoin(subQuery1 : Triple.type , subQuery2 : Statement): Unit ={

  }
}

object SparqlQueryExecutor {
  def apply(dbSource : String) = new SparqlQueryExecutor(dbSource)
}