package main.scala

import com.github.jsonldjava.core.RDFDataset.Node
import org.apache.jena.query.{QueryExecutionFactory, QueryFactory}
import org.apache.jena.rdf.model.{ModelFactory, Resource}

class SparqlQueryExecutor(val dbSource: String) {
  val model = ModelFactory.createDefaultModel()
  val typeProperty = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type"

  def load() =  model.read(dbSource)
  def showModel() : Unit =  println("is empty ? "+model.isEmpty())
  def size() = model.size()

  // Q1^P1 and Q2^P2 are sub-query or a triple pattern for respectively partition p1 and partition P2
  // Here is a list of query.
  // subj and obj are join variable V

  /* On parcourt la liste des sous requêtes, pour chaque noeud, on affecte le résultat de la requête dans d du j ème noeud
   * Si la partition n'a pas une join Variable correspondant aux entrées (join variable),
   * on les répartit dans un data set différent où la join variable le correspond.
   * Pour tout les noeuds qui sont différents du noeud actuel, on transfert le jeu de donnée au nouveau noeud k
   *
   * Enfin on parcourt tout les noeuds j et on mets le résultat dans Resultj^V en faisant une jointure de tout les jeu de données où
   * on fait une union dans chaque jeu de donnée.
   */
  def partitionedJoin(queryList : List[QueryFactory.type], subj : Resource, obj : Resource) ={
    val result
    for(query <- queryList) {
      for (nodej <- Node) {
        val d  = QueryExecutionFactory.sparqlService(nodej, query.toString)
        if (subj != query || obj != query) {

          for(nodek <- Node){
            if(!nodek.equals(nodej)){

            }
          }

        }
      }
    }
    for(nodej <- Node) {
      result =
    }
    return result

  }
}

object SparqlQueryExecutor {
  def apply(dbSource : String) = new SparqlQueryExecutor(dbSource)
}