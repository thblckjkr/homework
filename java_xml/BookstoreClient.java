import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.*;

public class BookstoreClient {
    DocumentBuilderFactory builderfactory;
    DocumentBuilder builder;
    XPath xPath;
    Document xmlDocument;
    
    public static void main(String[] args) {
        BookstoreClient x = new BookstoreClient();
        System.out.println("Empezando búsqueda");

        x.init();

        // a) El nombre del autor del libro de horror
        String author = x.getAuthor("genre", "Horror");
        System.out.println("El nombre del autor del libro de horror es " + author);

        // b) El total de comprar todos los libros de fantasia
        Float amount = x.getAmount("genre", "Fantasy");
        System.out.println("La cantidad a gastar en libros de fantasía es " + amount.toString());

        // c) la lista de todos los libros de computacion que tengan que ver con microsoft (para no leerlos)
        String[] books = x.getBooks("description", "Microsoft");
        System.out.println("Libros de microsoft: \n\t" + String.join("\n\t", books));
    }
    
    public void init(){
        try{
            //loading the XML document from a file
            this.builderfactory = DocumentBuilderFactory.newInstance();
            this.builderfactory.setNamespaceAware(true);
            
            this.builder = builderfactory.newDocumentBuilder();
            this.xmlDocument = builder.parse(
                new File(BookstoreClient.class.getResource("books.xml").getFile().replace("%20", " "))
            );
            
            XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
            this.xPath = factory.newXPath();
        }catch(Exception e){
        }
    }

    public String getAuthor(String parameter, String data){
        try{
            XPathExpression xPathExpression = this.xPath.compile("//catalog//book[" + parameter + "='" + data + "']//author");
            String author = xPathExpression.evaluate(this.xmlDocument,XPathConstants.STRING).toString();

            return author;
        }catch(Exception e){}
        return "Failed";
    }

    public String[] getBooks(String parameter, String data){
        String[] response;
        List<String> tmp = new ArrayList<String>();
        try{
            XPathExpression xPathExpression = this.xPath.compile("//catalog//book[contains(" + parameter + ",'" + data + "')]//title");
            NodeList node = (NodeList) xPathExpression.evaluate(this.xmlDocument,XPathConstants.NODESET);

            for (int i = 0; i < node.getLength(); i++) {
                tmp.add( node.item(i).getTextContent() );
            }

            response = new String[ tmp.size() ];
            tmp.toArray( response );
        }catch(Exception e){
            response = new String[ 0 ];
        }
        return response;
    }

    public Float getAmount(String parameter, String data){
        Float total = 0.0f;
        try{
            XPathExpression xPathExpression = this.xPath.compile("//catalog//book[" + parameter + "='" + data + "']//price");
            
            NodeList node = (NodeList) xPathExpression.evaluate(this.xmlDocument,XPathConstants.NODESET);

            for (int i = 0; i < node.getLength(); i++) {
                total += Float.parseFloat( node.item(i).getTextContent() );
            }

            return total;
        }catch(Exception e){}
        return total;
    }
}