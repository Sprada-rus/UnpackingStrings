package bin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public String getUserArgs() throws Exception {
		String userArgs = "";
		System.out.println("Введите значения, например 2[xy]3[z]xyz");
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			userArgs = reader.readLine();

			if(userArgs.length() == 0) return null;

			if (checkArgs(userArgs.toLowerCase())) {
				userArgs = userArgs.toLowerCase();
			} else {
				throw new Exception("Пожалуйста, вводите числа как указано в примере");
			}
		}catch (IOException e){
			System.out.println("Exception: " + e);
		}catch (Exception ex){
			System.out.println("Exception: " + ex.getMessage());
			System.exit(-1);
		}

		return userArgs;
	}

	private boolean checkArgs(String args){
		boolean check = true;

		for (int i = 0; i < args.length(); i++){
			if (Character.toString(args.charAt(i)).matches("^[0-9]") && args.charAt(i+1) == ']'){
				check = false;
			} else if (Character.toString(args.charAt(i)).matches("^[a-z]") && Character.toString(args.charAt(i+1)).matches("^[0-1]")){
				check = false;
			}
		}

		return check;
	}

    public static void main(String[] args) throws Exception {
	    Main main = new Main();
	    String inputArgs = main.getUserArgs();
	    String outputResult = "";
	    Unpack unpacking = new Unpack(inputArgs);

	    outputResult = unpacking.getOutputValue();
	    System.out.println("Исходные значения " + inputArgs);
	    System.out.println("Результат распаковки " + outputResult);
    }

}
