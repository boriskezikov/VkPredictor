package kezikov.vkprocessor.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@Service
public class TvShowsServiceImpl implements TvShowsService {



    @Override
    @Cacheable(cacheNames = "nodes")
    public  ArrayList<NodeList> parseXml() throws NullPointerException {
        try {
            File tvProgram = new File("/Users/kezikovboris/Downloads/testXML/xmltv.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(tvProgram);

            NodeList categoryNodes = doc.getElementsByTagName("category");
            NodeList channels = doc.getElementsByTagName("channel");

            ArrayList<NodeList> nodes = new ArrayList<>();

            nodes.add(categoryNodes);
            nodes.add(channels);

            //if file have been changed last day, we need to parse categories again

            if (tvProgram.lastModified() - System.currentTimeMillis() % 1000 < 86400000)
                parseCategories(nodes);


            return nodes;
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
            return null;
        }

    }


    @Override
    @Cacheable(cacheNames = "shows")
    public HashMap<String, ArrayList<String>> parseCategories(ArrayList<NodeList> nodes) {
        HashMap<String, String> channelMap = new HashMap<>();

        NodeList channels = nodes.get(1);
        NodeList categoryNodes = nodes.get(0);

        for (int i =0; i < channels.getLength(); i++){
            channelMap.put(channels.item(i)
                    .getAttributes()
                    .getNamedItem("id")
                    .toString()
                    .substring(3), channels.item(i).getTextContent());

        }

        HashMap<String, ArrayList<String>> shows = new HashMap<>();
        shows.put("Сериал", new ArrayList<>());

        System.out.println(categoryNodes.getLength());
        for (int i = 0; i < categoryNodes.getLength(); i++) {

            String query = categoryNodes.item(i)
                    .getParentNode()
                    .getTextContent();

            String channel = categoryNodes.item(i).getParentNode()
                    .getAttributes()
                    .getNamedItem("channel")
                    .toString()
                    .substring(8);

            String category = query.substring(query.lastIndexOf(" ") + 1);

            //Изменил на to lower case !!!
            category = category.replaceAll("(?u)[^-аА-я]+", "").toLowerCase();

            query = query +" \nСмотри на канале: "+channelMap.get(channel);

            if (shows.keySet().contains(category)) {
                shows.get(category).add(query);
            } else {
                if (query.contains("Сериал") || query.contains("сериал")) {
                    shows.get("Сериал").add(query);
                    continue;
                }
                shows.put(category, new ArrayList<>());
                shows.get(category).add(query);
            }
            System.out.println(i);

        }
        return shows;

    }


}


