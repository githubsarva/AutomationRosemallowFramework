package com.Saucedemo.login;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.Saucedemo.libraries.Config;
import com.Saucedemo.libraries.Generic;

public class login extends Config {
	
	
	@Test
	public void login() throws Exception
	{


		String moduleControllerPath = "./ModuleController/ModuleController.xlsx";
		String mainModule = Generic.getCellValue(moduleControllerPath, "Main",
				1, 0);
		String es = Generic.getCellValue(moduleControllerPath, "Main", 1, 1);

		String testCaseName = Generic.getCellValue(moduleControllerPath,
				mainModule, 1, 0);
		String testcase_es = Generic.getCellValue(moduleControllerPath,
				mainModule, 1, 1);

		hm.put(testCaseName, "flag");
		if (es.equalsIgnoreCase("yes")) {

			if (testcase_es.equalsIgnoreCase("yes")) {

				
				APP_LOGS.debug("***********************" + "TestCase " + " \" "
						+ testCaseName + " \" " + " starts executing "
						+ "***********************");

				ps.navigateTestURL();
				ps.login(CONFIG.getProperty("USERNAME"),CONFIG.getProperty("PASSWORD"));
				ps.verifyTitle("Swag Labs");
				ps.logout();

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
		String testcaseName = "login";
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
