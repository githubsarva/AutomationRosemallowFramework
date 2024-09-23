package com.Saucedemo.checkout;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.Saucedemo.libraries.Config;
import com.Saucedemo.libraries.Generic;

public class checkout  extends Config {
	
	@Test
	public void chechout() throws Exception
	{


		String moduleControllerPath = "./ModuleController/ModuleController.xlsx";
		String mainModule = Generic.getCellValue(moduleControllerPath, "Main",
				2, 0);
		String es = Generic.getCellValue(moduleControllerPath, "Main", 2, 1);

		String testCaseName = Generic.getCellValue(moduleControllerPath,
				mainModule, 1, 0);
		String testcase_es = Generic.getCellValue(moduleControllerPath,
				mainModule, 1, 1);

		hm.put(testCaseName, "flag");
		if (es.equalsIgnoreCase("yes")) {

			if (testcase_es.equalsIgnoreCase("yes")) {

				String testDataPath = "./test_data/Data.xlsx";

				APP_LOGS.debug("***********************" + "TestCase " + " \" "
						+ testCaseName + " \" " + " starts executing "
						+ "***********************");


				ps.navigateTestURL();
				
				int rc = Generic.getRowCount(testDataPath, testCaseName);
				
				System.out.println("row value:"+rc);
				
				for (int i = 1; i <=rc; i++) {

					APP_LOGS.debug("***** Data Driving from row no :" + i
							+ " *****");

					String un = Generic.getCellValue(testDataPath,
							testCaseName, i, 0);
					String pw = Generic.getCellValue(testDataPath,
							testCaseName, i, 1);
					
					System.out.println(un);
					System.out.println(pw);
				ps.login(un,pw);
				ps.products();
				ps.checkOut("Saravanan", "L", "Ch123456");
				ps.verifySuccessMsg("Thank you for your order!");
				ps.logout();
				}
				
				String res = hm.get(testCaseName).toString();
				if (!res.equalsIgnoreCase("FAIL"))
					hm.put(testCaseName, "PASS");

				APP_LOGS.debug("***********************" + "TestCase " + " \" "
						+ testCaseName + " \" " + " ends execution "
						+ "***********************");
			} else {

				hm.put(testCaseName, "SKIP");
				APP_LOGS.debug("***********************" + "TestCase " + " \" "
						+ testCaseName + " \" "
						+ " execution status is set to NO "
						+ "***********************");
				throw new SkipException("Skipping " + testCaseName
						+ " TestCase");
			}
		} else {
			hm.put(testCaseName, "SKIP");
			APP_LOGS.debug("***********************" + "Main Module " + " \" "
					+ mainModule + " \" " + " execution status is set to NO "
					+ "***********************");
			throw new SkipException("Skipping " + testCaseName + " TestCase");
		}
	
		
		}
	
	@AfterMethod
	public void res() {
		String testcaseName = "checkout";
		String res = hm.get(testcaseName).toString();
		if (res.equalsIgnoreCase("PASS")) {
			hm.put(testcaseName, "PASS");
			System.out.println(testcaseName + " TestCase is PASSED");
			APP_LOGS.debug(testcaseName + " TestCase is PASSED");
		} else if (res.equalsIgnoreCase("SKIP")) {
			hm.put(testcaseName, "SKIP");
			System.out.println(testcaseName + " TestCase is SKIPPED");
			APP_LOGS.debug(testcaseName + " TestCase is SKIPPED");
		} else {
			hm.put(testcaseName, "FAIL");
			System.out.println(testcaseName + " TestCase is FAILED");
			APP_LOGS.debug(testcaseName + " TestCase is FAILED");
		}
	}


	}


