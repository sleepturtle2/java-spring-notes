package xml;

public class XMLPersonDao {

    XMLJdbcConnection xmlJdbcConnection;

    public XMLJdbcConnection getXmlJdbcConnection() {
        return xmlJdbcConnection;
    }

    public void setXmlJdbcConnection(XMLJdbcConnection xmlJdbcConnection) {
        this.xmlJdbcConnection = xmlJdbcConnection;
    }
}
