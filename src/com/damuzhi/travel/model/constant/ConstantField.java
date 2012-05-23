package com.damuzhi.travel.model.constant;

public class ConstantField
{
	public static final String MAIN_SERVICE = "damuzhi.travel.service.MainService";
	public static final String SCENERY_ACTION = "damuzhi.activity.place.SceneryActivity";
	public static final String HOTEL_ACTION = "damuzhi.activity.place.HotelActivity";
	public static final String RESTAURANT_ACTION = "damuzhi.activity.place.RestaurantActivity";
	public static final String SAVE_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/damuzhi/data/";
	public static final String DATA_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/damuzhi/data/%s/";
	public static final String IMAGE_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/damuzhi/data/%s/data/";
	public static final String APP_DATA_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/damuzhi/data/app.dat";
	public static final String PLACE_PATH ="/data/place/";
	public static final int DATA_LOCAL = 0;
	public static final int DATA_HTTP = 1;
	public static final int DISTANCE = 10000;
	public static final String APP_FILE = "app.dat";
	public static final String PLACE_TAG = "place";
	public static final String GUIDE_TAG = "guide";
	public static final String OVERVIEW_TAG = "overview";
	public static final String ROUTE_TAG = "route";
	public static final String EXTENSION = ".dat"; 
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String CHECK_NET = "com.damuzhi.travel.network.CheckNet";
	public static final String UTF ="UTF-8";
	public static final String REGISTER = "http://api.trip8888.com/service/registerUser.aspx?type=%s&deviceToken=%s&deviceId=%s";
	public static final String USER_COLLECTION = "http://api.trip8888.com/service/addFavorite.aspx?userId=%s&placeId=%s&longitude=%s&latitude=%s";
	public static final String USER_DELETE_COLLECTION = "http://api.trip8888.com/service/deleteFavorite.aspx?userId=%s&placeId=%s";
	public static final String PLACE_INFO = "http://api.trip8888.com/service/queryPlace.aspx?userId=%s&placeId=%s";
	public static final String FEED_BACK = "http://api.trip8888.com/service/feedback.aspx?userId=%s&contact=%s&content=%s";
	public static final String OVERVIEW = "http://api.trip8888.com/service/queryObject.aspx?type=%s&id=%s&lang=%s";
	public static final String PLACElIST = "http://api.trip8888.com/service/queryList.aspx?type=%s&cityId=%s&lang=%s";
	public static final String APP = "http://api.trip8888.com/service/queryList.aspx?type=%s&lang=%s";
	public static final String ANDROID_VERSION = "http://api.trip8888.com/service/androidVersion.txt";
	public static final String RESULT_OK = "0";
	public static final String LANG_HANS = "1";
	public static final String LANG_HANT = "2";
	public static final String LANG_ENG ="3";
	public static final String PLACE = "1";
	public static final String CITY_BASE = "2";
	public static final String TRAVEL_PREPRATION= "3";
	public static final String TRAVEL_TRANSPORTAION = "4";
	public static final String TRAVEL_UTILITY = "5";
	public static final String TRAVEL_TIPS ="6";
	public static final String TRAVEL_ROUTE = "7";
	public static final String TRAVEL_TIPS_LIST ="5";
	public static final String TRAVEL_ROUTE_LIST = "6";
	public static final String HELP_INFO = "8";
	public static final String OPEN_CITY_LIST = "8";
	public static final String TEST_CITY_LIST = "9";
	public static final String APP_DATA = "10";
	public static final String SCENERY = "21";
	public static final String HOTEL = "22";
	public static final String RESTAURANT = "23";
	public static final String SHOPPING = "24";
	public static final String ENTERTAINMENT = "25";
	public static final String ALL_PLACE_ORDER_BY_RANK = "40";
	public static final String ALL_SCENERY_ORDER_BY_RANK = "41";
	public static final String ALL_HOTEL_ORDER_BY_RANK = "42";
	public static final String ALL_RESTAURANT_ORDER_BY_RANK = "43";
	public static final String ALL_SHOPPING_ORDER_BY_RANK = "44";
	public static final String ALL_FUN_ORDER_BY_RANK = "45";
	public static final String ALL_PLACE = "ȫ��";
	public static final int ALL_PLACE_CATEGORY_ID = -1;
	public static final int HALF_KILOMETER = 500;
	public static final int ONE_KILOMETER = 1000;
	public static final int FIVE_KILOMETER = 5000;
	public static final int TEN_KILOMETER = 10000;
	{
		
	}
	
}
