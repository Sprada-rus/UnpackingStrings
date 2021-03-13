package bin;

import java.util.ArrayList;

public class Unpack {
    private String outputValue = "";
    private final String regexpStr = "^[a-z]";
    private final String regexpInt = "^[0-9]";

    public Unpack(String inputValue){
        unpacking(inputValue);
    }

    private void unpacking(String value){
        int count = 0;
        for(int i = 0; i < value.length(); i++){

            if (Character.toString(value.charAt(i)).matches(regexpInt)){
                count += charToInt(value.charAt(i));
            } else if (value.charAt(i) == '['){
                i = repeatValue(count, i+1, value);
                count = 0;
            } else if (Character.toString(value.charAt(i)).matches(regexpStr)){
                outputValue += Character.toString(value.charAt(i));
            }
        }
    }

    private int repeatValue(int count, int index, String value){
        int secondRepeat = 0;
        int lastIndex = index;
        ArrayList<Integer> arrayRepeat = new ArrayList<>();
        String result = "";
        for ( int repeat = 0; repeat < count; repeat++) {
            if (charToString(value.charAt(index)).matches(regexpInt)) {
                    for (int j = index; j < value.length(); j++) {
                        if (value.charAt(j) == ']' && value.charAt(j+1) == ']') {
                            lastIndex = j+1;
                            outputValue += result.repeat(secondRepeat);
                            break;
                        } else if (charToString(value.charAt(j)).matches(regexpInt) && value.charAt(j+1) == '[') {
                            secondRepeat = charToInt(value.charAt(j));
                        } else if (charToString(value.charAt(j)).matches(regexpStr)) {
                            result += charToString(value.charAt(j));
                        } else if (value.charAt(j) == ']' && charToString(value.charAt(j+1)).matches(regexpInt)){
                            outputValue += result.repeat(secondRepeat);
                            result = "";
                        }
                    }
                    result = "";

            } else if (charToString(value.charAt(index)).matches(regexpStr)) {

                for (int j = index; j < value.length(); j++) {
                    if (value.charAt(j) == ']') {
                        lastIndex = j;
                        break;
                    } else if (charToString(value.charAt(lastIndex)).matches(regexpInt)) {
                        break;
                    } else {
                        outputValue += charToString(value.charAt(j));
                    }
                }
                result = "";
            }
        }
        return lastIndex;
    }

    private int charToInt(char value){
        return Character.getNumericValue(value);
    }

    private String charToString(char value){
        return Character.toString(value);
    }

    public String getOutputValue() {
        return outputValue;
    }
}
