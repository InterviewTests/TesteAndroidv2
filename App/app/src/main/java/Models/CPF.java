package Models;

import java.util.ArrayList;
import java.util.Collections;

public class CPF {
	private String cpf;
	
	public CPF(String cpf) {
		this.cpf = cpf.replace(".", "").replace("-", "");
	}

	private static ArrayList<Integer> convertStringToArray(String cpf) {
		ArrayList<Integer> array = new ArrayList();
		for (int i = 0; i < cpf.length(); i++) {
			array.add(Character.getNumericValue(cpf.charAt(i)));
		}
		return array;
	}
	
	public boolean ehValido() {
		return testCPF(getCpf());
	}
	
	private static boolean testCPF(String cpf) {
		if (cpf.isEmpty())
			return false;
		if (cpf.matches("/[0-9]{11,11}")) 
			return false;
		ArrayList<Integer> cpfArray = convertStringToArray(cpf);
		if (cpfArray.size() != 11)
			return false;
		
		int firstDigit, secondDigit;
		firstDigit = cpfArray.get(cpfArray.size()-2);
		secondDigit = cpfArray.get(cpfArray.size()-1);
		Collections.reverse(cpfArray);
		return checkFirstDigit(cpfArray, firstDigit) && checkSecondDigit(cpfArray, secondDigit) &&
				haNumerosDiferentes(cpfArray);
	}
	
	private static boolean checkFirstDigit(ArrayList<Integer> list, int digit) {
		int sum = 0;
		for(int i = 10; i > 1; i--) {
			sum += list.get(i) * i;
		}
		int result = (sum * 10) % 11;
		result = (result == 10)?0:result;
		if (result == digit)
			return true;
		else
			return false;
	}
	
	private static boolean checkSecondDigit(ArrayList<Integer> list, int digit) {
		int sum = 0;
		for(int i = 11; i > 1; i--) {
			sum += list.get(i-1) * i;
		}
		int result = (sum * 10) % 11;
		result = (result == 10)?0:result;
		if (result == digit)
			return true;
		else
			return false;
	}

	private static boolean haNumerosDiferentes(ArrayList<Integer> cpfArray){
		int firstNumber = cpfArray.get(0);
		int cont = 0;
		for (int number:cpfArray) {
			if (number == firstNumber)
				cont++;
		}
		if (cont == 11)
			return false;
		else
			return true;
	}
	
	public static boolean checkCPF(String cpf) {
		cpf = cpf.replace(".", "").replace("-", "");
		return testCPF(cpf);
	}

	public String getCpf() {
		return cpf;
	}
}
