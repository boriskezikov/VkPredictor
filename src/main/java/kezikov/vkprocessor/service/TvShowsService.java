package kezikov.vkprocessor.service;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

public interface TvShowsService {
    NodeList parseXml() throws Exception;
    HashMap<String,ArrayList<String>> parseCategories(NodeList categoryNodes);
}

