package dat.backend.model.services;

public class CheckString {

    public static int stringToInt(String num) {
        int ans = 0;
        String toInt = "";

        if (num.contains(".")) {
            for (int i = 0; i < num.length(); ++i) {
                if ('.' == num.charAt(i)) {
                    i++;
                }
                toInt += num.charAt(i);
            }
        } else {
            toInt = num;
        }

        ans = Integer.parseInt(toInt);

        return ans;
    }


}
