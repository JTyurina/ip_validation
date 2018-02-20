import java.util.Scanner;
import java.util.regex.Pattern;

public class IpValidation {

    private static Pattern pattern;
    private static long ip1, ip2, diff;
    private static final String IP_ADDRESS_PATTERN
            = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";


    public IpValidation() {
        pattern = Pattern.compile(IP_ADDRESS_PATTERN);
    }

    public static long getIp1() {
        return ip1;
    }

    public static void setIp1(long ip1) {
        IpValidation.ip1 = ip1;
    }

    public static void setIp2(long ip2) {
        IpValidation.ip2 = ip2;
    }

    public static long getDiff() {
        return diff;
    }

    public static void setDiff(long diff) {
        IpValidation.diff = diff;
    }


    public static boolean validate(String ipAddress) {
        pattern = Pattern.compile(IP_ADDRESS_PATTERN);
        return pattern.matcher(ipAddress).matches();
    }


    public static long convertToDecimal(String[] s){

        return  Long.parseLong(s[0]) * (long) Math.pow(256,3) +
                Long.parseLong(s[1]) * (long) Math.pow(256,2) +
                Long.parseLong(s[2]) * (long) Math.pow(256,1) +
                Long.parseLong(s[3]) * (long) Math.pow(256,0);
    }


    public static String convertDecimalToIp(long ipAddress){
        return ((ipAddress>>24) & 0xFF) + "." + ((ipAddress>>16) & 0xFF) + "." + ((ipAddress>>8) & 0xFF) + "." +  ((ipAddress) & 0xFF);
    }


    public static long readConvertIp(String ipNumber){
        System.out.println("Insert " + ipNumber + " ip-address:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().trim();
        if (validate(s))
        {
            String[] IpString = s.split(Pattern.quote("."));
            return convertToDecimal(IpString);
        }
        else
        {
            System.out.println("Incorrect input data! Try again.");
            return 0L;
        }

    }

    public static void reverseOrder(){
        if (getDiff() == 0L)
            System.out.println("No ip-addreses found in the range.");
        else
            {
                setDiff(getDiff()*-1);
                for (int i = 1; i < getDiff(); i++)
                {
                    long newIp = getIp1() - i;
                    System.out.println(convertDecimalToIp(newIp));
                }
            }
    }

    public static void directOrder(){

        for (int i = 1; i < getDiff(); i++)
        {
            long newIp = getIp1() + i;
            System.out.println(convertDecimalToIp(newIp));
        }

    }


    public static void main(String[] args) {

        // 0L - введенный адрес не соответствует формату

        long scannedValue1 = readConvertIp("first");
        while (scannedValue1 == 0L)
            scannedValue1 = (readConvertIp("first"));
        setIp1(scannedValue1);

        long scannedValue2 = (readConvertIp("second"));
        while (scannedValue2 == 0L)
            scannedValue2 = readConvertIp("second");
        setIp2(scannedValue2);

        setDiff(scannedValue2-scannedValue1);

        // если не отсортированы
        if (getDiff() <= 0)
            reverseOrder();
        else
            directOrder();
    }

}
