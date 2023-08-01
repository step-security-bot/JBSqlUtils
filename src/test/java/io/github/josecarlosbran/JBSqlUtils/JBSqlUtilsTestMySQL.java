package io.github.josecarlosbran.JBSqlUtils;


import UtilidadesTest.TestModel;
import com.josebran.LogsJB.LogsJB;
import io.github.josecarlosbran.JBSqlUtils.Enumerations.*;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.DataBaseUndefind;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.ModelNotFound;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.PropertiesDBUndefined;
import io.github.josecarlosbran.JBSqlUtils.Exceptions.ValorUndefined;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static UtilidadesTest.Utilities.logParrafo;
import static io.github.josecarlosbran.JBSqlUtils.JBSqlUtils.createTable;
import static io.github.josecarlosbran.JBSqlUtils.JBSqlUtils.select;

@Listeners({org.uncommons.reportng.HTMLReporter.class, org.uncommons.reportng.JUnitXMLReporter.class})
public class JBSqlUtilsTestMySQL {

    public JBSqlUtilsTestMySQL() {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
    }

    TestModel testModel;


    @Test(testName = "Setear Properties Conexión for Model")
    public void setPropertiesConexiontoModel() throws DataBaseUndefind, PropertiesDBUndefined {
        this.testModel = new TestModel(false);
        this.testModel.setGetPropertySystem(false);
        this.testModel.setPort("5076");
        this.testModel.setHost("127.0.0.1");
        this.testModel.setUser("Bran");
        this.testModel.setPassword("Bran");
        this.testModel.setBD("JBSQLUTILS");
        this.testModel.setDataBaseType(DataBase.MySQL);
        this.testModel.setPropertisURL("?autoReconnect=true&useSSL=false");
        Assert.assertTrue("JBSQLUTILS".equalsIgnoreCase(this.testModel.getBD()),
                "Propiedad Nombre BD's no ha sido seteada correctamente");
        Assert.assertTrue("5076".equalsIgnoreCase(this.testModel.getPort()),
                "Propiedad Puerto BD's no ha sido seteada correctamente");
        Assert.assertTrue("127.0.0.1".equalsIgnoreCase(this.testModel.getHost()),
                "Propiedad Host BD's no ha sido seteada correctamente");
        Assert.assertTrue("Bran".equalsIgnoreCase(this.testModel.getUser()),
                "Propiedad Usuario BD's no ha sido seteada correctamente");
        Assert.assertTrue("Bran".equalsIgnoreCase(this.testModel.getPassword()),
                "Propiedad Password BD's no ha sido seteada correctamente");
        Assert.assertTrue(DataBase.MySQL.name().equalsIgnoreCase(this.testModel.getDataBaseType().name()),
                "Propiedad Tipo de BD's no ha sido seteada correctamente");
        Assert.assertTrue("?autoReconnect=true&useSSL=false".equalsIgnoreCase(this.testModel.getPropertisURL()),
                "Propiedad Propiedades de conexión no ha sido seteada correctamente");
    }

    @Test(testName = "Drop Table If Exists from Model",
            dependsOnMethods = {"setPropertiesConexiontoModel"})
    public void dropTableIfExists() throws Exception {
        this.testModel.crateTable();
        Assert.assertTrue(this.testModel.dropTableIfExist(), "No se pudo eliminar la tabla en BD's");
        Assert.assertFalse(this.testModel.getTableExist(), "La tabla No existe en BD's y aun así responde que si la elimino");
    }

    @Test(testName = "Create Table from Model",
            dependsOnMethods = "dropTableIfExists")
    public void createTable() throws Exception {
        Assert.assertTrue(this.testModel.crateTable(), "La Tabla No fue creada en BD's");
        Assert.assertTrue(this.testModel.getTableExist(), "La tabla No existe en BD's ");
    }

    @Test(testName = "Insert Model",
            dependsOnMethods = "createTable")
    public void insertModel() throws Exception {
        /**
         * Asignamos valores a las columnas del modelo, luego llamamos al método save(),
         * el cual se encarga de insertar un registro en la tabla correspondiente al modelo con la información del mismo
         * si este no existe, de existir actualiza el registro por medio de la clave primaria del modelo.
         */
        this.testModel.getName().setValor("Marcos");
        this.testModel.getApellido().setValor("Cabrera");
        this.testModel.getIsMayor().setValor(false);
        Integer rowsInsert = this.testModel.save();

        logParrafo("Filas insertadas en BD's: " + rowsInsert + " " + this.testModel.toString());
        /**
         * Esperamos se ejecute la instrucción en BD's
         */
        this.testModel.waitOperationComplete();
        Assert.assertTrue(rowsInsert == 1, "El registro no fue insertado en BD's");
    }


    @Test(testName = "Clean Model",
            dependsOnMethods = "insertModel")
    public void cleanModel() throws Exception {
        Assert.assertTrue(this.testModel.getName().getValor().equalsIgnoreCase("Marcos"));
        Assert.assertTrue(this.testModel.getApellido().getValor().equalsIgnoreCase("Cabrera"));
        Assert.assertTrue(this.testModel.getIsMayor().getValor()==Boolean.FALSE);
        Assert.assertTrue(this.testModel.getModelExist());
        logParrafo("Limpiaremos el Modelo: "+this.testModel.toString());
        this.testModel.cleanModel();
        logParrafo("Modelo despues de realizar la limpieza: "+this.testModel.toString());
        Assert.assertTrue(Objects.isNull(this.testModel.getName().getValor()), "No limpio la columna Name del Modelo");
        Assert.assertTrue(Objects.isNull(this.testModel.getApellido().getValor()), "No limpio la columna Apellido del Modelo");
        Assert.assertTrue(Objects.isNull(this.testModel.getIsMayor().getValor()), "No limpio la columna IsMayor del Modelo");
        Assert.assertFalse(this.testModel.getModelExist(), "No limpio la bandera que indica que el modelo no existe en BD's");

    }

    @Test(testName = "First Or Fail",
            dependsOnMethods = "cleanModel",
            expectedExceptions = ModelNotFound.class)
    public void firstOrFail() throws Exception {
        this.testModel.where("Name", Operator.IGUAL_QUE, "Marcossss").and("Apellido", Operator.IGUAL_QUE,
                "Cabrerassss").firstOrFail();
    }


    @Test(testName = "Update Model", dependsOnMethods = "firstOrFail")
    public void updateModel() throws Exception {
        this.testModel.cleanModel();
        /**
         * Obtenemos el modelo de BD's de lo contrario lanza ModelNotFoundException
         */
        this.testModel.where("Name", Operator.IGUAL_QUE, "Marcos").and("Apellido", Operator.IGUAL_QUE,
                "Cabrera").firstOrFail();
        /**
         * Esperamos ejecute la operación en BD's
         */
        this.testModel.waitOperationComplete();
        /**
         * Actualizamos la información
         */
        this.testModel.getName().setValor("MarcosEfrain");
        this.testModel.getIsMayor().setValor(true);
        /**
         * Eliminamos el modelo en BD's
         */
        Integer rowsUpdate = this.testModel.save();
        Assert.assertTrue(rowsUpdate == 1, "El registro no fue actualizado en la BD's");
    }

    @Test(testName = "Delete Model", dependsOnMethods = "updateModel")
    public void deleteModel() throws Exception {
        this.testModel.cleanModel();
        /**
         * Obtenemos el modelo de BD's de lo contrario lanza ModelNotFoundException
         */
        this.testModel.where("Name", Operator.IGUAL_QUE, "MarcosEfrain").and("Apellido", Operator.IGUAL_QUE,
                "Cabrera").and("isMayor", Operator.IGUAL_QUE, true).firstOrFail();
        /**
         * Esperamos ejecute la operación en BD's
         */
        this.testModel.waitOperationComplete();
        /**
         * Eliminamos el modelo en BD's
         */
        Integer rowsDelete = this.testModel.delete();
        Assert.assertTrue(rowsDelete == 1, "El registro no fue eliminado en la BD's");
    }


    @Test(testName = "Insert Models",
            dependsOnMethods = "deleteModel")
    public void insertModels() throws Exception {
        this.testModel.cleanModel();
        List<TestModel> models = new ArrayList<TestModel>();
        //Llenamos la lista de mdelos a insertar en BD's
        for (int i = 0; i < 10; i++) {
            TestModel model = new TestModel();
            model.getName().setValor("Modelo #" + i);
            model.getApellido().setValor("Apellido #" + i);
            if (i % 2 == 0) {
                model.getIsMayor().setValor(false);
            }
            models.add(model);
        }
        Integer rowsInsert = this.testModel.saveALL(models);
        logParrafo("Filas insertadas en BD's: " + rowsInsert + " " + models);
        /**
         * Esperamos se ejecute la instrucción en BD's
         */
        this.testModel.waitOperationComplete();
        //Verificamos que la cantidad de filas insertadas corresponda a la cantidad de modelos enviados a realizar el insert
        Assert.assertTrue(rowsInsert == 10, "Los registros no fueron insertados correctamente en BD's");
    }


    @Test(testName = "Get Model",
            dependsOnMethods = "insertModels")
    public void getModel() throws Exception {
        //Incluir metodo que permita limpiar el modelo
        this.testModel.cleanModel();
        this.testModel.where("Name", Operator.IGUAL_QUE, "Marcossss").and("Apellido", Operator.IGUAL_QUE,
                "Cabrerassss").get();
        this.testModel.waitOperationComplete();
        logParrafo("Trato de obtener un modelo que no existe, el resultado es: "+this.testModel.getModelExist());
        logParrafo(this.testModel.toString());
        Assert.assertFalse(this.testModel.getModelExist(), "Obtuvo un registro que no existe en BD's");
        this.testModel.where("Name", Operator.LIKE, "%Modelo #%").and("IsMayor", Operator.IGUAL_QUE,
                true).get();
        this.testModel.waitOperationComplete();
        logParrafo("Trato de obtener un modelo que sí existe, el resultado es: "+this.testModel.getModelExist());
        logParrafo(this.testModel.toString());
        Assert.assertTrue(this.testModel.getModelExist(), "No obtuvo un registro que no existe en BD's");
    }

    @Test(testName = "Get First Model",
            dependsOnMethods = "getModel")
    public void firstModel() throws Exception {
        //Incluir metodo que permita limpiar el modelo
        this.testModel.cleanModel();
        this.testModel.where("Name", Operator.IGUAL_QUE, "Marcossss").and("Apellido", Operator.IGUAL_QUE,
                "Cabrerassss").first();
        this.testModel.waitOperationComplete();
        logParrafo("Trato de obtener un modelo que no existe, el resultado es: "+this.testModel.getModelExist());
        logParrafo(this.testModel.toString());
        Assert.assertFalse(this.testModel.getModelExist(), "Obtuvo un registro que no existe en BD's");
        this.testModel.where("Name", Operator.LIKE, "%Modelo #%").and("IsMayor", Operator.IGUAL_QUE,
                true).first();
        this.testModel.waitOperationComplete();
        logParrafo("Trato de obtener un modelo que sí existe, el resultado es: "+this.testModel.getModelExist());
        logParrafo(this.testModel.toString());
        Assert.assertTrue(this.testModel.getModelExist(), "No obtuvo un registro que no existe en BD's");
    }

    @Test(testName = "Take Models",
            dependsOnMethods = "firstModel")
    public void takeModels() throws Exception {
        //Incluir metodo que permita limpiar el modelo
        this.testModel.cleanModel();
        List<TestModel> models = new ArrayList<TestModel>();
        models = this.testModel.where("Name", Operator.LIKE, "%Modelo #%").take(6).get();
        this.testModel.waitOperationComplete();
        logParrafo("Se recuperaron " + models.size() + " los cuales son: " + models.toString());
        Assert.assertTrue(models.size() == 6, "Los modelos no fueron recuperados de BD's como se definio en la sentencia Take");
    }

    @Test(testName = "Get All Models",
            dependsOnMethods = "takeModels")
    public void getAllModels() throws Exception {
        //Incluir metodo que permita limpiar el modelo
        this.testModel.cleanModel();
        List<TestModel> models = new ArrayList<TestModel>();
        models = this.testModel.where("Name", Operator.LIKE, "%Modelo #5%").or(
                "Name", Operator.LIKE, "Modelo #8").or("Apellido", Operator.IGUAL_QUE, "Apellido #3").getAll();
        this.testModel.waitOperationComplete();
        logParrafo("Se recuperaron " + models.size() + " los cuales son: " + models.toString());
        Assert.assertTrue(models.size() == 3, "Los modelos no fueron recuperados de BD's");
    }

    @Test(testName = "Delete Models",
            dependsOnMethods = "getAllModels")
    public void deleteModels() throws Exception {
        this.testModel.cleanModel();
        List<TestModel> models = new ArrayList<TestModel>();
        models = this.testModel.where("Name", Operator.LIKE, "%Modelo #5%").or(
                "Name", Operator.LIKE, "Modelo #8").or("Apellido", Operator.IGUAL_QUE, "Apellido #3").getAll();
        this.testModel.waitOperationComplete();
        logParrafo("Se recuperaron " + models.size() + " Para eliminar: " + models.toString());
        Integer rowsDelete = this.testModel.deleteALL(models);
        this.testModel.waitOperationComplete();
        logParrafo("Filas eliminadas en BD's: " + rowsDelete + " " + models);
        //Verificamos que la cantidad de filas insertadas corresponda a la cantidad de modelos enviados a realizar el insert
        Assert.assertTrue(rowsDelete == 3, "Los registros no fueron eliminados correctamente en BD's");

    }

    @Test(testName = "Setear Properties Conexión Globales",
    dependsOnMethods = "deleteModels")
    public void setPropertiesConexion() {
        JBSqlUtils.setDataBaseGlobal("JBSQLUTILS");
        JBSqlUtils.setPortGlobal("5076");
        JBSqlUtils.setHostGlobal("127.0.0.1");
        JBSqlUtils.setUserGlobal("Bran");
        JBSqlUtils.setPasswordGlobal("Bran");
        JBSqlUtils.setDataBaseTypeGlobal(DataBase.MySQL);
        JBSqlUtils.setPropertisUrlConexionGlobal("?autoReconnect=true&useSSL=false");
        Assert.assertTrue("JBSQLUTILS".equalsIgnoreCase(System.getProperty(ConeccionProperties.DBNAME.getPropiertie())),
                "Propiedad Nombre BD's no ha sido seteada correctamente");
        Assert.assertTrue("5076".equalsIgnoreCase(System.getProperty(ConeccionProperties.DBPORT.getPropiertie())),
                "Propiedad Puerto BD's no ha sido seteada correctamente");
        Assert.assertTrue("127.0.0.1".equalsIgnoreCase(System.getProperty(ConeccionProperties.DBHOST.getPropiertie())),
                "Propiedad Host BD's no ha sido seteada correctamente");
        Assert.assertTrue("Bran".equalsIgnoreCase(System.getProperty(ConeccionProperties.DBUSER.getPropiertie())),
                "Propiedad Usuario BD's no ha sido seteada correctamente");
        Assert.assertTrue("Bran".equalsIgnoreCase(System.getProperty(ConeccionProperties.DBPASSWORD.getPropiertie())),
                "Propiedad Password BD's no ha sido seteada correctamente");
        Assert.assertTrue(DataBase.MySQL.name().equalsIgnoreCase(System.getProperty(ConeccionProperties.DBTYPE.getPropiertie())),
                "Propiedad Tipo de BD's no ha sido seteada correctamente");
        Assert.assertTrue("?autoReconnect=true&useSSL=false".equalsIgnoreCase(System.getProperty(ConeccionProperties.DBPROPERTIESURL.getPropiertie())),
                "Propiedad Propiedades de conexión no ha sido seteada correctamente");
    }


    @Test(testName = "Create Table JBSqlUtils",
            dependsOnMethods = "setPropertiesConexion")
    public void creteTableJBSqlUtils() throws Exception {
        /**
         * Definimos las columnas que deseamos posea nuestra tabla
         */
        Column<Integer> Id = new Column<>("Id", DataType.INTEGER, Constraint.AUTO_INCREMENT, Constraint.PRIMARY_KEY);

        Column<String> Name = new Column<>("Name", DataType.VARCHAR);

        Column<String> Apellido = new Column<>("Apellido", DataType.VARCHAR);

        Column<Boolean> Estado = new Column<>("Estado", DataType.BOOLEAN, "true", Constraint.DEFAULT);

        /**
         * Para crear una tabla utilizamos el metodo createTable despues de haber definido el nombre de la tabla que deseamos Crear
         * y las columnas que deseamos tenga nuestra tabla
         */
        Boolean result= false;
        result=JBSqlUtils.createTable("Proveedor").addColumn(Name).addColumn(Id).addColumn(Apellido).
                addColumn(Estado).createTable();
        logParrafo("Resultado de solicitar la creación de la tabla en BD's: "+result);
        Assert.assertTrue(result, "La Tabla no pudo ser creada debido a que ya existe en BD's");

        result =true;
        result=JBSqlUtils.createTable("Proveedor").addColumn(Name).addColumn(Id).addColumn(Apellido).
                addColumn(Estado).createTable();
        logParrafo("Resultado de solicitar la creación de la tabla en BD's: "+result);
        Assert.assertFalse(result, "Retorna que la tabla a sido creada cuando esta ya existe en BD's");
    }

    @Test(testName = "Insert Into JBSqlUtils",
            dependsOnMethods = "creteTableJBSqlUtils")
    public void insertIntoJBSqlUtils() throws Exception {
        int registros=0;

        registros+=JBSqlUtils.insertInto("Proveedor").value("Name", "Erick").andValue("Apellido", "Ramos")
                .execute();
        logParrafo("Resultado de insertar el registro de Erick en la tabla Proveedor: "+registros);
        Assert.assertTrue(registros==1, "No se pudo insertar el registro de Erick en la tabla Proveedor de BD's");

        registros=0;
        registros+=JBSqlUtils.insertInto("Proveedor").value("Name", "Daniel").andValue("Apellido", "Quiñonez").
                andValue("Estado", false).execute();
        logParrafo("Resultado de insertar el registro de Daniel en la tabla Proveedor: "+registros);
        Assert.assertTrue(registros==1, "No se pudo insertar el registro de Daniel en la tabla Proveedor de BD's");

        registros=0;
        registros+=JBSqlUtils.insertInto("Proveedor").value("Name", "Ligia").andValue("Apellido", "Camey")
                .andValue("Estado", true).andValue("Id", 3).execute();
        logParrafo("Resultado de insertar el registro de Ligia en la tabla Proveedor: "+registros);
        Assert.assertTrue(registros==1, "No se pudo insertar el registro de Ligia en la tabla Proveedor de BD's");

        registros=0;
        registros+= JBSqlUtils.insertInto("Proveedor").value("Name", "Elsa").andValue("Apellido", "Aguirre")
                .andValue("Estado", false).execute();
        logParrafo("Resultado de insertar el registro de Elsa en la tabla Proveedor: "+registros);
        Assert.assertTrue(registros==1, "No se pudo insertar el registro de Elsa en la tabla Proveedor de BD's");

        registros=0;
        registros+=JBSqlUtils.insertInto("Proveedor").value("Name", "Alex").andValue("Apellido", "Garcia")
                .execute();
        logParrafo("Resultado de insertar el registro de Alex en la tabla Proveedor: "+registros);
        Assert.assertTrue(registros==1, "No se pudo insertar el registro de Alex en la tabla Proveedor de BD's");
    }


    @Test(testName = "Get In JsonObjects JBSqlUtils",
            dependsOnMethods = "insertIntoJBSqlUtils")
    public void getInJsonObjectsJBSqlUtils() throws Exception {
        /**
         * Si deseamos obtener todas las columnas de la tabla envíamos el parametro columnas del metodo
         * getInJsonObjects como null, de esa manera nos obtendra todas las columnas de la tabla especificada como parametro
         * del metodo select
         */
        List<String> columnas = null;

        /**
         * Si deseamos obtener unicamente determinadas columnas, es necesario envíar como parametro una lista de strings
         * con los nombres de las columnas que deseamos obtener del metodo getInJsonObjects
         */
        columnas = new ArrayList<>();
        columnas.add("Id");
        columnas.add("Name");

        /**
         * Para obtener los registros de una tabla de BD's podemos hacerlo a traves del metodo select envíando como parametro
         * el nombre de la tabla de la cual deseamos obtener los registros, así mismo podemos filtrar los resultados a traves del metodo
         * where el cual proporciona acceso a metodos por medio de los cuales podemos filtrar los resultados.
         */
        List<JSONObject> lista = select("Proveedor").where("Estado", Operator.IGUAL_QUE, true)
                .and("Apellido", Operator.LIKE, "%a%").take(2).getInJsonObjects(columnas);


        logParrafo("Visualizamos los registros obtenidos de BD's");
        /**
         * Imprimimos los registros obtenidos
         */
        lista.forEach(fila -> {
            logParrafo(fila.toString());
        });
        Assert.assertTrue(lista.size()==2, "No se pudo obtener las tuplas que cumplen con los criterios de busqueda en una lista de JsonObject");
    }





}