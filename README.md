Passing Groovy Closure as parameter to callTestCase()
====

## Problem to solve

A **closure** in Groovy is an open, anonymous, block of code that can take arguments, return a value and be assigned to a variable. 
See [the document](http://groovy-lang.org/closures.html) for detail.

In Katalon Studio, as you already know, a test case can call another test case with parameters. See [the document](https://docs.katalon.com/katalon-studio/docs/call-test-case.html) for detail. A typical usage would be passing String values and int values.

One day, I got a question. Can I pass a closure from a test case to another? Can the callee test case call the passed closure?

## Solution

Make a demo project where
1. Caller test case do `callTestCase()` passing a String value, in int value and a closure as parameter.
2. Callee test case calls the closure, while doing other stuff.

## Description

Here I present 2 code snipet. The code will tell you everything.

[`Caller`](./Test)






