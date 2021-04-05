package dto;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ReadDataFromExcel;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.Reporter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class TelephoneDialPadTest implements ITest {

	static String currentDir = System.getProperty("user.dir");
	public static String testDataExcelPath = currentDir + "\\data\\excel\\dto\\TelephoneDialPad.xlsx";

	private ThreadLocal<String> testName = new ThreadLocal<>();

	@Override
	public String getTestName() {
		return testName.get();
	}

	/**
	 * Data Provider for retrieve combinations functionality
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "retrieveCombinations")
	public Object[] retrieveCombinationsTestData() throws Exception {
		List<LinkedHashMap<String, String>> mapDataList = ReadDataFromExcel.getExcelDataAsMap(testDataExcelPath,
				"retrieveCombinations");
		return mapDataList.toArray();
	}

	// Change Test Case Name Dynamically
	// TODO
	// Not changing name in Report, currently only available on console

	@BeforeMethod(alwaysRun = true)
	public void BeforeMethod(Method method, Object[] testData) {
		String[] out = testData[0].toString().split(",");
		String testDataSet = out[0].split("=")[1];
		testName.set(method.getName() + "_" + testDataSet);
	}

	/**
	 * Verify retrieve combinations functionality
	 * 
	 * @param data
	 */
	@Test(dataProvider = "retrieveCombinations")
	public void retrieveCombinationsTest(HashMap<String, String> data) {

		try {

			Reporter.log("Test Case Name: " + data.get("TC_NAME") , true);
			Reporter.log("Test Description: " + data.get("TC_DESCRIPTION"), true);
			TelephoneDialPad dailpad = new TelephoneDialPad();
			LinkedList<String> letterCombinations = dailpad.retrieveCombinations(data.get("TC_INPUT"));			
			Reporter.log("Letter Combinations generated: " + letterCombinations.toString() + "\n\n", true);
//			Reporter.log("Letter Combinations expected: " + data.get("TC_EXPECTED"), true);
			
			if(data.get("TC_EXPECTED").toLowerCase().contains("length")) {
				
				String expectedString = data.get("TC_EXPECTED").toString().split("=")[1];
				Assert.assertEquals(letterCombinations.size(), Integer.parseInt(expectedString));
								
			} else {
				
				String expectedString = data.get("TC_EXPECTED").toString();
				List<String> expectedList = Arrays.asList(expectedString.split("\\s*,\\s*"));
				Assert.assertEquals(letterCombinations, expectedList);

			}		
			
		} catch (Exception e) {

			Assert.assertEquals(e.getClass().getSimpleName(), data.get("TC_EXPECTED"));
		}

	}

}
