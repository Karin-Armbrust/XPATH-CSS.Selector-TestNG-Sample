package com.data;

import org.testng.annotations.DataProvider;

public class LoginData {
    // This set of data is used for login tests, the loginDataSet tells whether they should be able to login
    @DataProvider(name="login-provider")
    public static Object [][] loadDatasets() {
        Object[][] loginDataSets = new Object[1][3];

        loginDataSets[0][0] = "standard_user";      loginDataSets[0][1] = "secret_sauce";   loginDataSets[0][2] = true;
        //loginDataSets[1][0] = "locked_out_user";    loginDataSets[1][1] = "secret_sauce";   loginDataSets[1][2] = false;
        //loginDataSets[2][0] = "bad_user";           loginDataSets[2][1] = "secret_sauce";   loginDataSets[2][2] = false;
        //loginDataSets[3][0] = "standard_user";      loginDataSets[3][1] = "badpass";        loginDataSets[3][2] = false;

        return loginDataSets;
    }

}
