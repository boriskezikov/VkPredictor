package kezikov.vkprocessor;


import com.google.gson.Gson;
import kezikov.vkprocessor.service.TvShowsService;
import kezikov.vkprocessor.service.TvShowsServiceImpl;
import kezikov.vkprocessor.service.VkServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


@RestController
@AllArgsConstructor
@RequestMapping("/vk-test/")
public class EndPoint {

    private VkServiceImpl vkService = new VkServiceImpl();

    private TvShowsService tvShowsService = new TvShowsServiceImpl();

    @PostMapping
    public String process(@RequestBody String vkId) throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("Получил значение с фронта");

        System.out.println(vkId);


        String vkResp = vkService.sendRequest(vkId, "interests");
        String[] words = vkResp.split("\\s");

        ArrayList<NodeList> nodes = tvShowsService.parseXml();
        HashMap<String, ArrayList<String>> map = tvShowsService.parseCategories(nodes);
        Gson gson = new Gson();

        String json = gson.toJson(vkService.predictShow(Arrays.asList(words),map));


        System.out.println("вернул значение на фронт");
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("Запрос выполнялся " + timeSpent / 1000 + " секунд");
        return json;

    }


}


