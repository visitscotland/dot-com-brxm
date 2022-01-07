package com.visitscotland.brxm.services.youtube;

import com.visitscotland.brxm.services.CommonUtilsService;
import com.visitscotland.brxm.utils.Properties;
import com.visitscotland.utils.Contract;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class YoutubeApiService {

    private final CommonUtilsService commonUtilsService;
    private final Properties properties;

    private static final Logger logger = LoggerFactory.getLogger(CommonUtilsService.class);
    private static final String YOUTUBE_API_BASE = "https://www.googleapis.com/youtube/v3/";

    public YoutubeApiService(Properties properties, CommonUtilsService commonUtilsService) {
        this.commonUtilsService = commonUtilsService;
        this.properties = properties;
    }

    /**
     * Retrieve information about a YouTube video (list operation in YouTube Data API
     * Note - Cache will only be used if this method is invoked outside this class
     *
     * @param youtubeId The video id
     * @return If an error occurs, empty optional is returned
     */
    @Cacheable(value = "youtube", unless = "#result == null")
    public Optional<YoutubeVideo> getVideoInfo(String youtubeId) {
        logger.debug("Retrieving information from YouTube API for video {}", youtubeId);
        String youtubeApiKey = properties.getYoutubeApiKey();
        if (Contract.isEmpty(youtubeApiKey)) {
            logger.warn("No YouTube API key set");
            return Optional.empty();
        }
        try {
            String apiRequestUrl = String.format("%svideos?key=%s&part=snippet&id=%s", YOUTUBE_API_BASE, youtubeApiKey, youtubeId);
            URLConnection conn = commonUtilsService.openConnection(apiRequestUrl);
            conn.setConnectTimeout(1000);
            YoutubeListResponse jsonResponse = new ObjectMapper().readValue(conn.getInputStream(), YoutubeListResponse.class);
            if (jsonResponse != null && jsonResponse.items.size() == 1 && jsonResponse.items.get(0) != null) {
                YoutubeSnippet snippet = jsonResponse.items.get(0).snippet;
                YoutubeVideo videoDto = new YoutubeVideo();
                videoDto.setDescription(videoDto.getDescription());
                videoDto.setId(youtubeId);
                videoDto.setTitle(snippet.title);
                videoDto.setPublishDate(snippet.publishedAt);
                return Optional.of(videoDto);
            } else {
                logger.warn("Failed to map youtube API response for video id {} (url={})", youtubeId, apiRequestUrl);
                return Optional.empty();
            }
        } catch (IOException e) {
            logger.error("Failed to retrieve video {} list information", youtubeId, e);
            return Optional.empty();
        }
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class YoutubeSnippet {
    public Date publishedAt;
    public String title;
    public String description;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class YoutubeItem {
    public String id;
    public YoutubeSnippet snippet;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class YoutubeListResponse {
    public List<YoutubeItem> items;
}
