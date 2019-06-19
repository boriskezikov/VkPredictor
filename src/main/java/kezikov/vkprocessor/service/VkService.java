package kezikov.vkprocessor.service;




import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface VkService {
    String sendRequest(String vkID, String tag);
    String predictShow(List<String> preferences, HashMap<String, ArrayList<String>> map) throws IOException;
}
