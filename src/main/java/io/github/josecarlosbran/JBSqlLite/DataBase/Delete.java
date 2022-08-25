package io.github.josecarlosbran.JBSqlLite.DataBase;

import io.github.josecarlosbran.JBSqlLite.Enumerations.Operator;
import io.github.josecarlosbran.JBSqlLite.Exceptions.DataBaseUndefind;
import io.github.josecarlosbran.JBSqlLite.Exceptions.PropertiesDBUndefined;
import io.github.josecarlosbran.JBSqlLite.Exceptions.ValorUndefined;

import static io.github.josecarlosbran.JBSqlLite.Utilities.UtilitiesJB.stringIsNullOrEmpty;
/**
 * @author Jose Bran
 * Clase que proporciona los metodos necesarios para la lógica de un Delete en BD's sin necesidad
 * de tener un modelo de la tabla que se desea Eliminar registros.
 */
public class Delete {
    private String sql;

    /**
     * Constructor que recibe como parametro:
     * @param TableName El nombre de la tabla sobre la cual se desea realizar el Update.
     * @throws ValorUndefined Lanza esta excepción si el parametro proporcionado está vacío o es NULL
     */
    public Delete(String TableName) throws ValorUndefined {
        String respuesta = "";
        if (stringIsNullOrEmpty(TableName)) {
            throw new ValorUndefined("El nombre de la Tabla proporcionado esta vacío o es NULL");
        }
        this.sql= "DELETE FROM "+TableName;
    }

    /**
     * Proporciona un punto de entrada para agregar la lógica de una sentencia WHERE a la sentencia SQL que
     * deseamos ejecutar
     * @param columna Columna que sera evaluada
     * @param operador Operador por medio del cual se evaluara la columna
     * @param value Valor contra el que se evaluara la columna
     * @return Punto de entrada a metodos que permiten seguir modificando la expresión de filtro u obtener el o los
     * modelos que hacen match con la consulta generada
     * @throws DataBaseUndefind Lanza esta excepción si en las propiedades del sistema no esta definida el tipo de
     * BD's a la cual se conectara el modelo.
     * @throws PropertiesDBUndefined Lanza esta excepción si en las propiedades del sistema no estan definidas las
     * propiedades de conexión necesarias para conectarse a la BD's especificada.
     * @throws ValorUndefined Lanza esta excepción si alguno de los parametros proporcionados esta
     * Vacío o es Null
     */
    public Where where(String columna, Operator operador, String value) throws DataBaseUndefind, PropertiesDBUndefined, ValorUndefined {
        return new Where(columna, operador, value, this.sql);
    }



}
