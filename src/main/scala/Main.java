package main.scala;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;


public class Main {
    public static void uploadRDF(File f, String uri){
        Model model = ModelFactory.createDefaultModel();
        model.read(f.toString(),"TURTLE");
        System.out.println(model.size());
        DatasetAccessor accessor = DatasetAccessorFactory.createHTTP(uri);
        accessor.putModel(model);
    }

    public static void queryDataset(String querySPARQL, String uri){
        QueryExecution qe = QueryExecutionFactory.sparqlService(uri,querySPARQL);

        ResultSet res = qe.execSelect();


        while(res.hasNext()){
            QuerySolution sol = res.next();
            String s = sol.get("s").toString();
            String p = sol.get("p").toString();
            String o = sol.get("o").toString();

            System.out.println("Subject :" + s + ", Predicate : " +  p + ", Object : " + o);



        }
    }


    public static void insertQuery(String querySPARQL, String uri){
        QueryExecution qe = QueryExecutionFactory.sparqlService(uri,querySPARQL);

        qe.execAsk();

    }
    public static void main(String[] argc) throws FileNotFoundException, MalformedURLException {

        Main.uploadRDF(new File("pizzeria.ttl"),"http://localhost:3030/testPizza");
        Main.queryDataset("SELECT * WHERE { ?s ?p ?o }","http://localhost:3030/testPizza");
        Main.insertQuery("INSERT DATA\n" +
                "{ <http://u-pem.fr/kb/person#clientkvong> <http://u-pem.fr/kb/person#hasName> Vong" +
                "}","http://localhost:3030/testPizza");




    }

}