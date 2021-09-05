package constants;

public final class FilePaths {
    private FilePaths(){}

    private static final String PATH_TO_RESOURCES = System.getProperty("user.dir")+"/src/main/resources/";
    private static final String PROPERTIES_FILE_PATH = PATH_TO_RESOURCES.concat("application_test.properties");
    private static final String USERS_EXCEL_FILE_PATH = PATH_TO_RESOURCES.concat("Users.xlsx");
    private static final String USERS_JSON_FILE_PATH = PATH_TO_RESOURCES.concat("UserDetails.json");
    private static final String EXTENT_REPORT_FOLDER_PATH = PATH_TO_RESOURCES+"reports/";
    private static String EXTENT_REPORT_FILE_PATH;

    public static String getPropertiesFilePath(){
        return PROPERTIES_FILE_PATH;
    }

    public static String getUsersExcelFilePath(){
        return USERS_EXCEL_FILE_PATH;
    }

    public static String getUsersJsonFilePath() { return USERS_JSON_FILE_PATH; }

    public static void createFile(){
        EXTENT_REPORT_FILE_PATH = EXTENT_REPORT_FOLDER_PATH+"index.html";
    }

    public static String getExtentReportFilePath() {
        return EXTENT_REPORT_FILE_PATH;
    }

}
