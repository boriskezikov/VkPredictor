package kezikov.vkprocessor;


import kezikov.vkprocessor.service.TvShowsService;
import kezikov.vkprocessor.service.TvShowsServiceImpl;
import kezikov.vkprocessor.service.VkServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/vk-test/")
public class EndPoint {

    private VkServiceImpl vkService;
    private TvShowsService tvShowsService;

    @GetMapping("{vkId}")
    public String process(@PathVariable("vkId") String vkId) throws Exception {

        NodeList nodeList = tvShowsService.parseXml();
        String vkResp = vkService.sendRequest(vkId, "interests");
        String[] words = vkResp.split("\\s");
        return vkService.predictShow(Arrays.asList(words), tvShowsService.parseCategories(nodeList));
    }


}


