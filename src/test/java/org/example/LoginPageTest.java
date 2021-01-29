package org.example;

import org.junit.Test;
import sun.rmi.runtime.Log;

public class LoginPageTest extends PageTest{

    @Test
    public void login()

            //hesabınızla giriş yapmanız gerekmektedir.Telefon numarası istediği için geçersiz hesap alınamadı.
    {new LoginPage(driver).login( "****", "****");

    }

}
