import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

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
        Integer amount = x.getAmount("genre", "Fantasy");
        System.out.println("La cantidad de libros de fantasía es " + amount.toString());

        // c) la lista de todos los libros de computacion que tengan que ver con microsoft (para no leerlos)
        x.getAuthors("description", "Microsoft");
        // String[] authors = x.getAuthors("description", "Microsoft");
        // System.out.println(" Autores " + authors.toString() );
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

    public void getAuthors(String parameter, String data){
        try{
            //getting the name of the book having an isbn number == ABCD7327923
            XPathExpression xPathExpression = this.xPath.compile("//catalog//book[contains(" + parameter + ",'" + data + "')]//title");
            NodeList node =  (NodeList) xPathExpression.evaluate(this.xmlDocument,XPathConstants.NODESET);
    
            for (int i = 0; i < node.getLength(); i++) {

                // TODO. Add this to a list and return it
                System.out.println( node.item(i).getTextContent()  );   
            }

        }catch(Exception e){

        }
        
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