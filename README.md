Passing Groovy Closure as parameter to callTestCase()
====

## Problem to solve

A **closure** in Groovy is an open, anonymous, block of code that can take arguments, return a value and be assigned to a variable.
See [the document](http://groovy-lang.org/closures.html) for detail.

In Katalon Studio, as you already know, a test case can call another test case with parameters. See [the document](https://docs.katalon.com/katalon-studio/docs/call-test-case.html) for detail. A typical usage would be passing String values and int values.

One day, I got a question. **Can I pass a closure from a test case to another? Can the callee test case invoke the passed closure?**

## Solution

Make a demo project where
1. Caller test case do `callTestCase()` passing a String value, an int value and a closure as parameters.
2. Callee test case invokes the closure while taking String and int values as parameter.
3. Callee test case should also do something important other than invoking the closure.

## Description

Here I present 2 code snippet. These will tell you everything.

[`Caller`](Scripts/Caller/Script1544235036446.groovy)
```
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
```

['Callee'](Scripts/Callee/Script1544235027756.groovy)
```
println("Callee started")

howToGreet.call(username, gender, times)

println("Callee finshed")
```

When I ran the Caller, I got the following output in the log.
```
2018-12-08 11:50:29.690 INFO  c.k.katalon.core.main.TestCaseExecutor   - CALL Test Cases/Callee
2018-12-08 11:50:29.768 DEBUG testcase.Callee                          - 1: println("Callee started")
Callee started
2018-12-08 11:50:29.784 DEBUG testcase.Callee                          - 2: howToGreet.call(username, gender, times)
Hello, Mr. foo
Hello, Mr. foo
Hello, Mr. foo
How are you, Mr. foo?
How are you, Mr. foo?
How are you, Mr. foo?
2018-12-08 11:50:29.816 DEBUG testcase.Callee                          - 3: println("Callee finshed")
Callee finshed
2018-12-08 11:50:29.823 INFO  c.k.katalon.core.main.TestCaseExecutor   - END CALL Test Cases/Callee
```

It worked! This proved that I can pass a Groovy closure from testcase to testcase and invoke it successfully.

## How useful is it?

To be honest, I don't know how much useful it is for use to pass Groovy closures around in Katalon Studio.

If you have any usecases where this technique helps, please let me know.
