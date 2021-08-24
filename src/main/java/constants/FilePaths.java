package constants;

import lombok.Data;

@Data
public final class FilePaths {
    private FilePaths(){}

    private static final String PATH_TO_RESOURCES = System.getProperty("user.dir")+"/src/main/resources/";
    private static final String PROPERTIES_FILE_PATH = PATH_TO_RESOURCES.concat("application_test.properties");
    private static final String USERs_EXCEL_FILE_PATH = PATH_TO_RESOURCES.concat("UserDetails.json");
}
