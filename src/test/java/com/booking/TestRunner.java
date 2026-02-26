package com.booking;

import org.junit.platform.suite.api.*;
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.booking")
@SelectClasspathResource("features/createBookingScenariosInvalidLongFirstName.feature")
@SelectClasspathResource("features/createBookingScenariosInvalidShortFirstName.feature")
@SelectClasspathResource("features/createBookingScenariosInvalidLongLastName.feature")
@SelectClasspathResource("features/createBookingScenariosInvalidShortLastName.feature")
@SelectClasspathResource("features/createBookingScenariosInvalidLongPhone.feature")
@SelectClasspathResource("features/createBookingScenariosInvalidShortPhone.feature")
@SelectClasspathResource("features/createBookingScenariosInvalidEmail.feature")
@SelectClasspathResource("features/retrieveBookingScenariosNonExistingBooking.feature")
@SelectClasspathResource("features/createBooking.feature")
@SelectClasspathResource("features/retrieveBooking.feature")
@SelectClasspathResource("features/updateEntireBooking.feature")
@SelectClasspathResource("features/partialUpdateBooking.feature")
@SelectClasspathResource("features/deleteBooking.feature")
@SelectClasspathResource("features/messages.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.booking")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html, json:target/cucumber.json")
public class TestRunner {
}