
package ru.oscar.icq.constants;

public class MetaGenderConstants {

    public static final int UNKNOWN = 0x00;
    public static final int MALE = 0x02;
    public static final int FEMALE = 0x01;

    private int gender;

    public MetaGenderConstants(int gender) {
            this.gender = gender;
    }

    public int getGender() {
            return gender;
    }

    public String toString() {
            String ret = "";
            switch (gender) {
                    case UNKNOWN:
                            ret = "UNKNOWN";
                            break;
                    case MALE:
                            ret = "Male";
                            break;
                    case FEMALE:
                            ret = "Female";
                            break;
            }

            return ret;
    }    

}
