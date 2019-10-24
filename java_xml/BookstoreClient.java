import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/*
1) Haga un programa en java que lea el archivo books.xml y liste lo siguiente:

a) El nombre del autor del libro de horror

b) El total de comprar todos los libros de fantasia

c) la lista de todos los libros de computacion que tengan que ver con microsoft (para no leerlos)
*/

public class BookstoreClient {
    DocumentBuilderFactory builderfactory;
    DocumentBuilder builder;
    XPath xPath;
    Document xmlDocument;
    
    public static void main(String[] args) {
        BookstoreClient x = new BookstoreClient();
        System.out.println("Empezando búsqueda");

        x.init();
        String author = x.getAuthor("genre", "Horror");
        System.out.println("El nombre del autor del libro de horror es " + author);

        Integer amount = x.getAmount("genre", "Fantasy");
        System.out.println("La cantidad de libros de fantasía es " + amount.toString());
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
    public String getAuthors(String parameter, String data){
        try{
            //getting the name of the book having an isbn number == ABCD7327923
            XPathExpression xPathExpression = this.xPath.compile("//catalog//book[" + parameter + "='" + data + "']//author");
            String author = xPathExpression.evaluate(this.xmlDocument,XPathConstants.STRING).toString();
            
            return author;
        }catch(Exception e){

        }
        return "Failed";
    }

    public Integer getAmount(String parameter, String data){
        try{
            XPathExpression xPathExpression = this.xPath.compile("//catalog//book[" + parameter + "='" + data + "']");
            NodeList node = (NodeList) xPathExpression.evaluate(this.xmlDocument,XPathConstants.NODESET);

            return node.getLength();
        }catch(Exception e){}
        return 0;
    }
}