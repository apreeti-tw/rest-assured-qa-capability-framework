package constants;

public final class FilePaths {
    private FilePaths(){}

    private static final String PATH_TO_RESOURCES = System.getProperty("user.dir")+"/src/main/resources/";
    private static final String PROPERTIES_FILE_PATH = PATH_TO_RESOURCES.concat("application_test.properties");
    private static final String USERS_EXCEL_FILE_PATH = PATH_TO_RESOURCES.concat("UserDetails.json");

    public static String getPropertiesFilePath(){
        return PROPERTIES_FILE_PATH;
    }
}
