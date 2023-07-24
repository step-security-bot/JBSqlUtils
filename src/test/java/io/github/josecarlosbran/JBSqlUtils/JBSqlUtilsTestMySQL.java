package io.github.josecarlosbran.JBSqlUtils;


import io.github.josecarlosbran.JBSqlUtils.Enumerations.DataBase;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.DataBaseUndefind;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.PropertiesDBUndefined;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({org.uncommons.reportng.HTMLReporter.class, org.uncommons.reportng.JUnitXMLReporter.class})
public class JBSqlUtilsTestMySQL {

    public JBSqlUtilsTestMySQL(){

    }

    TestModel testModel;

    @BeforeClass
    public void beforeClass() throws DataBaseUndefind, PropertiesDBUndefined{
        JBSqlUtils.setDataBaseGlobal("JBSQLUTILS");
        JBSqlUtils.setPortGlobal("5076");
        JBSqlUtils.setHostGlobal("127.0.0.1");
        JBSqlUtils.setUserGlobal("Bran");
        JBSqlUtils.setPasswordGlobal("Bran");
        JBSqlUtils.setDataBaseTypeGlobal(DataBase.MySQL);
        this.testModel =new TestModel();
        testModel.setGetPropertySystem(false);
        testModel.setPort("5076");
        testModel.setHost("localhost");
        testModel.setUser("Bran");
        testModel.setPassword("Bran");
        testModel.setBD("JBSQLUTILS");
        testModel.setDataBaseType(DataBase.MySQL);
        testModel.setPropertisURL("");
    }

    @Test
    public void testMySQL(){
        System.out.println("Prueba testng");

    }



}