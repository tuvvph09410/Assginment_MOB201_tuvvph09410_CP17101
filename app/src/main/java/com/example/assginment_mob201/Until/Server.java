package com.example.assginment_mob201.Until;

public class Server {
    private static String locationName = "https://tucaomypham.000webhostapp.com";
    private static String urlSeason = locationName + "/assginment_mob201/get_all_season.php";
    private static String urlCourse = locationName + "/assginment_mob201/get_all_by_id_season.php";

    public static String getLocationName() {
        return locationName;
    }

    public static String getUrlCourse() {
        return urlCourse;
    }

    public static String getUrlSeason() {
        return urlSeason;
    }
}
