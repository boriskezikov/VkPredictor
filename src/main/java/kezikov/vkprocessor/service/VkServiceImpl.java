package kezikov.vkprocessor.service;


import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


@Service
@RequiredArgsConstructor
public class VkServiceImpl implements VkService {



    @Override
    public String sendRequest(String vkID, String tag) {
        String query = "https://api.vk.com/method/users.get?fields=interests,activities,tv,games&user_ids="
                + vkID+ "&v=5.52&access_token=" + Declarations.getAccess_token();

        HttpURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        String result = "";

        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);

            connection.setConnectTimeout(250);
            connection.setReadTimeout(1000);
            connection.connect();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }

                result = new JSONObject(sb.toString())
                        .getJSONArray("response")
                        .getJSONObject(0)
                        .getString(tag)

                        + " " +new JSONObject(sb.toString())
                        .getJSONArray("response")
                        .getJSONObject(0)
                        .getString("tv")

                        + " " + new JSONObject(sb.toString())
                        .getJSONArray("response")
                        .getJSONObject(0)
                        .getString("activities");

            } else {

                System.out.println("fail "
                        + connection.getResponseCode()
                        + ", "
                        + connection.getResponseMessage());

            }

        } catch (Exception cause) {
            System.out.println("У пользователя пустый филды, выдаю рандом");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }

        return result.toLowerCase();
    }


    @Override
    public String predictShow(List<String> preferences, HashMap<String, ArrayList<String>> map)  {


        Random random = new Random();

        List<String> keys = new ArrayList<>(map.keySet());
        String randomKey = keys.get(random.nextInt(keys.size()));
        ArrayList r = map.get(randomKey);
        int rand = new Random().nextInt(r.size());


        String key = preferences
                .stream()
                .filter(
                x -> map.keySet().contains(x))
                .findFirst()
                .orElse(null);

        try {
            if (key != null) {
                return map.get(key).get(rand);
            }
        }
        catch (IndexOutOfBoundsException throwable){
            predictShow(preferences,map);
        }

        return  map.get(randomKey).get(rand);
    }

}
