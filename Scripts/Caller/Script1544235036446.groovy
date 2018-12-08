import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Callee'), 
	[
		"username": "foo",
		"gender"  : "male",
		"times"   : 3,
		"howToGreet":
			{ String u, String g, int t ->
				def prefix = ''
				if (g == 'male') prefix = 'Mr.'
				else if (g == 'femail') prefix = 'Mrs.'
				for (int i = 0; i < t; i++) {
					println('Hello, ' + prefix + " " + u)
				}
				while (t > 0) {
					println('How are you, ' + prefix + " " + u + '?')
					t -= 1
				}
			}
	], 
	FailureHandling.STOP_ON_FAILURE)

