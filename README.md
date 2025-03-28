We would like you to complete a refactoring of the attached source code. You will find the instructions below. Once you have completed the 
refactoring, please send us a copy of your solution.
 
**Coding Task:**

Attached is a Mavenized Java project that we would like you to refactor/adapt.  This Java project is used to build version 1.0 of a JAR file that might be offered as a third party library for use in other applications, for example downloaded as a dependency from a public Maven repository.  It includes a FulfillmentPresenter class used to format text for a hard-coded set of payment types.  Customers do not have access to the source but have expressed a need to be able to implement their own payment types.

For version 2.0 of this library your objective is to refactor the provided code such that new payment types can be easily implemented in the customer’s application with the FulfillmentPresenter able to return a payment string for their new type.

There are two principles that must be adhered to:
 
* Closed for modification and open for extension
	* Customers should be able to easily add new payment types without being able to change the source of our library.
* Backward compatibility
	* Existing users should be able to continue using version 2.0 with no changes to their code.  In other words the method signatures of the FulfillmentPresenter **must** not be changed, though the implementation may be.  Other changes may be accepted if there is a valid argument for doing so.

Describe the approach you would take to unit test the existing code prior to making any of the changes for part 1.
 
_Deliverables:_

1) A changed version of the Java class representing the changes you would make. It doesn't have to compile, but should be reasonably valid Java. 
2) A description of your testing approach – example JUnit tests would be fine.
 
_Expectations:_

We do not expect you to spend more than 1 hour on this task. We expect you to be able to explain any tradeoffs and implementation decisions you 
have made. You are free to use any resources, books or the internet if you would like. But please don’t ask questions on forums, newsgroups, etc -
we would like to understand your personal approach.
