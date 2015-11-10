package stb.com.nyari.helpers;

/**
 * Created by Ari_S on 7/27/2015.
 */
public class Constants {
    public static final String K_PASSWORD_KEY = "xrg2oFl4ZtqreRI5piYr";

    //Request Code Start Activity For Result
    public static final int REQUEST_CODE_SIGNATURE = 1;
    public static final int REQUEST_CODE_SOUND = 2;
    public static final int REQUEST_CODE_IMAGE = 3;
    public static final int REQUEST_CODE_CAMERA = 4;
    public static final int RC_DIALOG_SEARCH_PATIENT = 5;
    public static final int RC_DIALOG_SEARCH_DOCTOR = 6;

    //Extras
    public static final String EXTRA_IMAGE_FILE = "imageFile";
    public static final String EXTRA_VOICE_FILE = "voiceFile";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_FILE_NAME = "fileName";
    public static final String EXTRA_STATUS = "status";
    public static final String EXTRA_DISCARD = "discard";
    public static final String EXTRA_IS_EXCEPTION = "isException";
    public static final String EXTRA_EXCEPTION_MESSAGE = "exceptionMessage";
    public static final String EXTRA_IS_NETWORK_ERROR = "isNetworkError";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_MR_NO = "mrNo";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_FIRST_NAME = "firstName";
    public static final String EXTRA_LAST_NAME = "lastName";
    public static final String EXTRA_BIRTH_DATE = "birthDate";
    public static final String EXTRA_ADDRESS = "address";
    public static final String EXTRA_PHONE_NUMBER = "phoneNumber";
    public static final String EXTRA_EC_TITLE = "ecTitle";
    public static final String EXTRA_EC_FIRST_NAME = "ecFirstName";
    public static final String EXTRA_EC_LAST_NAME = "ecLastName";
    public static final String EXTRA_CODE = "code";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_SERVICE_CODE = "serviceCode";
    public static final String EXTRA_QUEUE_NUMBER = "queueNumber";
    public static final String EXTRA_MAIN_DIAGNOSE_CODE = "mainDiagnoseCode";
    public static final String EXTRA_RECORD_ID = "recordId";
    public static final String EXTRA_SHIFT_CODE = "shiftCode";
    public static final String EXTRA_REGISTRATION_DATE = "registrationDate";

    // doctor extra
    public static final String EXTRA_DR_CODE = "code";
    public static final String EXTRA_DR_NAME = "name";
    public static final String EXTRA_DR_SERVICE = "service";

    //Intent Types
    public static final String TYPE_TEXT_HTML = "text/html";

    //Shared Preferences
    public static final String PREF_MMS_POLY = "MMS Poly";
    public static final String PREF_IS_SAVE = "isSave";

    //Time format
    public static final String TIME_FORMAT = "yyyy-MM-dd kk:mm";

    public static final String BIRTH_DATE_FORMAT = "dd-MM-yyyy";

    //Time Zone
    public static final String UTC = "UTC";

    //Encryption
    public static final String UTF8 = "UTF8";
    public static final String DES = "DES";

    //Email Address
    public static final String EMAIL_ADMIN_MMS_POLY = "admin_mmspoly@mitrais.com";

    //URL
    public static final String URL_GOOGLE_MAP = "http://maps.google.co.in/maps?q=";

    //Conditions
    public static final String STRING_TRUE = "true";
    public static final String STRING_FALSE = "false";
    public static final String STRING_NULL = "null";

    //Directory Path
    public static final String PATH_DATA = "/Android/data/";
    public static final String PATH_ANDROID = "/Android/";
    public static final String PATH_CAMERA = "/DCIM/Camera";

    public static final int RESULT_BACK_PRESSED = 2;

    public static final String K_PATH_ADDITIONAL_IMAGE = "TransactionImage/";
    public static final String EXT_JPEG = ".jpeg";

    // turn on/off notification, set true to activate notification, set false to deactivate push notification
    public static boolean IS_NOTIFICATION_ACTIVATED = false;

    public static final String K_MIME_TYPE_JSON = "application/json";

    //Button Pos
    public static final int POS_BUTTON_LOGOUT = 8;

    // Database
    public static final String K_DB_NAME = "MMSPoly.db";

    public static final String K_TABLE_ICD10 = "ICD10";

    // Patient
    public static final String K_TABLE_PATIENT = "Patient";
    public static final String K_FIELD_MRNO = "MRNo";
    public static final String K_FIELD_TITLE = "Title";
    public static final String K_FIELD_FIRST_NAME = "FirstName";
    public static final String K_FIELD_LAST_NAME = "LastName";
    public static final String K_FIELD_BIRTH_DATE = "BirthDate";
    public static final String K_FIELD_ADDRESS = "Address";
    public static final String K_FIELD_PHONE_NUMBER = "PhoneNumber";
    public static final String K_FIELD_EC_TITLE = "ECTitle";
    public static final String K_FIELD_EC_FIRST_NAME = "ECFirstName";
    public static final String K_FIELD_EC_LAST_NAME = "ECLastName";

    public static final String K_SERIALIZE_BIRTH_DATE = "DateOfBirth";

    // Medical record
    public static final String K_FIELD_DOCTOR_NAME = "DoctorName";
    public static final String K_FIELD_SERVICE_CODE = "ServiceCode";
    public static final String K_FIELD_SHIFT_CODE = "ShiftCode";
    public static final String K_FIELD_PATIENT_NAME = "PatientName";
    public static final String K_FIELD_MAIN_DIAGNOSE_CODE = "MainDiagnoseCode";
    public static final String K_FIELD_RECORD_ID = "RecordId";
    public static final String K_FIELD_REGISTRATION_DATE = "RegistrationDate";
    public static final String K_FIELD_QUEUE_NUMBER = "QueueNumber";
    public static final String K_SHIFT_DROPDOWNNAME = "Shift";

    // Doctor
    public static final String K_TABLE_DOCTOR = "Doctor";
    public static final String K_FIELD_DR_NAME = "Name";
    public static final String K_FIELD_DR_CODE = "Code";
    public static final String K_FIELD_DR_SERVICE = "Service";
    public static final String K_DR_DROPDOWNNAME = "DoctorService";

    // Preference
    public static final String K_TABLE_PREFERENCE = "Preferences";
    public static final String K_FIELD_SERIALIZED_ID = "Id";
    public static final String K_FIELD_ID = "ID";
    public static final String K_FIELD_TYPE = "Type";
    public static final String K_FIELD_NAME = "Name";
    public static final String K_FIELD_IS_MANDATORY = "IsMandatory";
    public static final String K_FIELD_KEY = "Key";
    public static final String K_FIELD_DEFAULT_VALUE = "DefaultValue";
    public static final String K_FIELD_FIELD_LENGTH = "FieldLength";
    public static final String K_FIELD_IS_NUMERIC_ONLY = "IsNumericOnly";
    public static final String K_FIELD_OPTIONS = "Options";

    // dropdowndata
    public static final String K_TABLE_DROPDOWNDATA = "DropdownData";
    public static final String K_FIELD_DD_ID = "Id";
    public static final String K_FIELD_DD_NAME = "Name";
    public static final String K_SERIALIZED_DD_NAME = "DropDownName";
    public static final String K_FIELD_DD_TEXT = "Text";
    public static final String K_FIELD_DD_VALUE = "Value";


    // Element Type
    public static final int TYPE_EDIT_TEXT = 1;
    public static final int TYPE_SPINNER = 2;
    public static final int TYPE_SWITCH = 3;

    // Application Setting Keys
    public static final String KEY_TOTAL_PER_PAGE = "TotalPerPage";
    public static final String KEY_APPLICATION_LANGUAGE = "AppLan";
    public static final String KEY_REMEMBER_ME = "RememberMe";
    public static final String KEY_PATIENT_TOTAL = "PatientTotal";

    // json response
    public static final String JSON_CONFIGURATION_DATA = "ConfigurationData";
    public static final String JSON_CONFIGURATIONS = "Configurations";
    public static final String JSON_VERSION = "Version";
    public static final String JSON_USER_PREFERENCE = "UserPreference";
    public static final String JSON_USER_VALUE = "Value";


}
