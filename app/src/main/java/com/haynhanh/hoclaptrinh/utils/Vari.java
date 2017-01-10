package com.haynhanh.hoclaptrinh.utils;

public class Vari {
    public static String DEVELOPER_KEY = "AIzaSyB5Jgo4jTYPq5Nep-7k2KqQCHjV4wbWC-w";
    public static String SENDER_ID = "799973083821";

    public static String maxResultsCat = "10";
    public static String maxResultsChannel = "10";
    public static String maxResultsPlaylist = "10";

    // channelId=%s&maxResults=%s
    public static String linkChannel = "https://www.googleapis.com/youtube/v3/playlists?part=snippet,contentDetails&maxResults=%s&channelId=%s&fields=items(contentDetails,id,snippet(title,thumbnails(high(url),default))),nextPageToken,pageInfo&key="
            + DEVELOPER_KEY;

    // maxResults=%s&playlistId=%s
    public static String linkPlaylist = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=%s&playlistId=%s&fields=items(snippet(title,thumbnails(high(url),default),resourceId(videoId))),nextPageToken,pageInfo&key="
            + DEVELOPER_KEY;

    public static String linkVideo = "https://www.googleapis.com/youtube/v3/videos?part=id,snippet,contentDetails,statistics&id=%s&maxResults=50&fields=items(id,contentDetails(duration),snippet(title,thumbnails(high(url))),statistics(viewCount,likeCount))&key="
            + DEVELOPER_KEY;

    public static String linkSearch = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=50&order=viewCount&type=video&videoDefinition=any&videoLicense=any&videoType=any&fields=items&key=AIzaSyCTC7nW23AiGE-R8a-W6lw0kkcG-I50ROg&q=%s";

    // gmail support
    public static String mailSupport = "enviti137@yahoo.com";
    // link Store
    public static String linkStore = "http://appphim.com/";

    // Startapp
    public static final String STARTAPP_DEV_ID = "101533441";
    public static final String STARTAPP_APP_ID = "210346130";

}