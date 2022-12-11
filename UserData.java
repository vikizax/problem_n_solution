public class UserData {
    private String name;
    private String religion;
    private String city;
    private String mother_tongue;
    private int age;
    private int no_of_vehicle;
    private int annual_income;

    public UserData(String name, String religion, String city, String mother_tongue, int age, int no_of_vehicle,
            int annual_income) {
        this.name = name;
        this.religion = religion;
        this.city = city;
        this.mother_tongue = mother_tongue;
        this.age = age;
        this.no_of_vehicle = no_of_vehicle;
        this.annual_income = annual_income;
    }

    public static enum StringFields {
        NAME,
        RELIGION,
        CITY,
        MOTHER_TONGUE,
    };

    public static enum NumericFields {
        AGE,
        NO_OF_VEHICLE,
        ANNUAL_INCOME
    };

    public String getStringFieldValue(StringFields key) {
        switch (key) {
            case NAME:
                return this.name;
            case RELIGION:
                return this.religion;
            case CITY:
                return this.city;
            case MOTHER_TONGUE:
                return this.mother_tongue;
            default:
                return "";
        }
    }

    public int getNumericFieldValue(NumericFields key) {
        switch (key) {
            case AGE:
                return this.age;
            case NO_OF_VEHICLE:
                return this.no_of_vehicle;
            case ANNUAL_INCOME:
                return this.annual_income;
            default:
                return 0;
        }
    }
}
