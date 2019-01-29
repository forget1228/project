package com.xj.ptgd.common.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.lang.StringUtils;

/**
 * 使用Jaxb2.0实现XML<->Java Object的Binder. 
 *
 * 特别支持Root对象是List的情形. 
 *
 * @author
 */
public class JaxbUtil {
    // 多线程安全的Context.  
    private JAXBContext jaxbContext;

    /**
     * @param types
     *            所有需要序列化的Root对象的类型. 
     */
    public JaxbUtil(Class<?>... types) throws JAXBException{
            jaxbContext = JAXBContext.newInstance(types);
    }

    /**
     * Java Object->Xml. 
     */
    public String toXml(Object root, String encoding) throws JAXBException{
        StringWriter writer = new StringWriter();
        createMarshaller(encoding).marshal(root, writer);
        return writer.toString();
    }

    /**
     * Java Object->Xml.
     */
    public String toXml(Object root, String encoding,String head) throws JAXBException{
        StringWriter writer = new StringWriter();
        if(head!=null){
            writer.append(head+"");
        }
        createMarshallerAddHead(encoding).marshal(root, writer);
        return writer.toString();
    }

    /**
     * Java Object->Xml, 特别支持对Root Element是Collection的情形. 
     */
    @SuppressWarnings("unchecked")
    public String toXml(Collection root, String rootName, String encoding) throws JAXBException{
        CollectionWrapper wrapper = new CollectionWrapper();
        wrapper.collection = root;

        JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<CollectionWrapper>(
                new QName(rootName), CollectionWrapper.class, wrapper);

        StringWriter writer = new StringWriter();
        createMarshaller(encoding).marshal(wrapperElement, writer);

        return writer.toString();
    }

    /**
     * Xml->Java Object. 
     */
    @SuppressWarnings("unchecked")
    public <T> T fromXml(String xml) throws JAXBException{
        StringReader reader = new StringReader(xml);
        return (T) createUnmarshaller().unmarshal(reader);
    }

    /**
     * Xml->Java Object, 支持大小写敏感或不敏感. 
     */
    @SuppressWarnings("unchecked")
    public <T> T fromXml(String xml, boolean caseSensitive) throws JAXBException{
        String fromXml = xml;
        if (!caseSensitive)
            fromXml = xml.toLowerCase();
        StringReader reader = new StringReader(fromXml);
        return (T) createUnmarshaller().unmarshal(reader);
    }

    /**
     * 创建Marshaller, 设定encoding(可为Null). 
     */
    public Marshaller createMarshaller(String encoding)throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        if (StringUtils.isNotBlank(encoding)) {
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        }
        return marshaller;
    }

    public Marshaller createMarshallerAddHead(String encoding)throws JAXBException {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);//是否格式化
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);
            if (StringUtils.isNotBlank(encoding)) {
                marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            }
            return marshaller;
    }

    /**
     * 创建UnMarshaller. 
     */
    public Unmarshaller createUnmarshaller() throws JAXBException{
        return jaxbContext.createUnmarshaller();
    }

    public static String xmlToJson(String xml) {

        XMLSerializer serializer = new XMLSerializer();

        return serializer.read(xml).toString();

    }
    /**
     * 封装Root Element 是 Collection的情况. 
     */
    public static class CollectionWrapper {
        @SuppressWarnings("unchecked")
        @XmlAnyElement
        protected Collection collection;
    }

    public static String convertToXml(Object obj, String encoding)throws JAXBException {
        String result = null;
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        result = writer.toString();
        return result;
    }

    /**
     * 将xml字符串转换为JSON对象
//     * @param xmlFile xml字符串
     * @return JSON对象
     */
    public static JSONObject  getJSONFromXml(String xmlString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        JSON json = xmlSerializer.read(xmlString);
        return (JSONObject)json;
    }
}  