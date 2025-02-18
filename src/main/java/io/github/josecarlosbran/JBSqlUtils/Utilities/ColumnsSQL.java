/***
 * Copyright (C) 2022 El proyecto de código abierto JBSqlUtils de José Bran
 *
 * Con licencia de Apache License, Versión 2.0 (la "Licencia");
 * no puede usar este archivo excepto de conformidad con la Licencia.
 * Puede obtener una copia de la Licencia en
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * A menos que lo exija la ley aplicable o se acuerde por escrito, el software
 * distribuido bajo la Licencia se distribuye "TAL CUAL",
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ya sean expresas o implícitas.
 * Consulte la Licencia para conocer el idioma específico que rige los permisos y
 * limitaciones bajo la Licencia.
 */
package io.github.josecarlosbran.JBSqlUtils.Utilities;

/**
 * Clase que permite identificar las caracteristicas de la columnas que tiene la tabla que corresponde
 * al modelo.
 * @author Jose Bran
 */
public class ColumnsSQL {
    /**
     * TABLE_CAT String → table catalog (may be null)
     */
    private String TABLE_CAT = null;

    /**
     * TABLE_SCHEM String → table schema (may be null)
     */
    private String TABLE_SCHEM = null;

    /**
     * TABLE_NAME String → table name
     */
    private String TABLE_NAME = null;

    /**
     * COLUMN_NAME String → column name
     */
    private String COLUMN_NAME = null;

    /**
     * DATA_TYPE int → SQL type from java.sql.Types
     */
    private int DATA_TYPE = 0;

    /**
     * TYPE_NAME String → Data source dependent type name, for a UDT the type name is fully qualified
     */
    private String TYPE_NAME = null;


    /**
     * COLUMN_SIZE int → column size.
     */
    private int COLUMN_SIZE = 0;

    /**
     * DECIMAL_DIGITS int → the number of fractional digits. Null is returned for data types where DECIMAL_DIGITS is not applicable.
     */
    private int DECIMAL_DIGITS = 0;

    /**
     * NUM_PREC_RADIX int → Radix (typically either 10 or 2)
     */
    private int NUM_PREC_RADIX = 0;

    /**
     * NULLABLE int → is NULL allowed.
     * columnNoNulls - might not allow NULL values
     * columnNullable - definitely allows NULL values
     * columnNullableUnknown - nullability unknown
     */
    private int NULLABLE = 0;

    /**
     * REMARKS String → comment describing column (may be null)
     */
    private String REMARKS = null;

    /**
     * COLUMN_DEF String → default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be null)
     */
    private String COLUMN_DEF = null;


    /**
     * CHAR_OCTET_LENGTH int → for char types the maximum number of bytes in the column
     */
    private int CHAR_OCTET_LENGTH = 0;

    /**
     * ORDINAL_POSITION int → index of column in table (starting at 1)
     */
    private int ORDINAL_POSITION = 0;

    /**
     * IS_NULLABLE String → ISO rules are used to determine the nullability for a column.
     * YES --- if the column can include NULLs
     * NO --- if the column cannot include NULLs
     * empty string --- if the nullability for the column is unknown
     */
    private String IS_NULLABLE = null;

    /**
     * SCOPE_CATALOG String → catalog of table that is the scope of a reference attribute (null if DATA_TYPE isn't REF)
     */
    private String SCOPE_CATALOG = null;

    /**
     * SCOPE_SCHEMA String → schema of table that is the scope of a reference attribute (null if the DATA_TYPE isn't REF)
     */
    private String SCOPE_SCHEMA = null;
    /**
     * SCOPE_TABLE String → table name that this the scope of a reference attribute (null if the DATA_TYPE isn't REF)
     */
    private String SCOPE_TABLE = null;

    /**
     * SOURCE_DATA_TYPE short → source type of a distinct type or user-generated Ref type,
     * SQL type from java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
     */
    private short SOURCE_DATA_TYPE = 0;

    /**
     * IS_AUTOINCREMENT String → Indicates whether this column is auto incremented
     * YES --- if the column is auto incremented
     * NO --- if the column is not auto incremented
     * empty string --- if it cannot be determined whether the column is auto incremented
     */
    private String IS_AUTOINCREMENT = null;

    /**
     * IS_GENERATEDCOLUMN String → Indicates whether this is a generated column
     * YES --- if this a generated column
     * NO --- if this not a generated column
     * empty string --- if it cannot be determined whether this is a generated column
     */
    private String IS_GENERATEDCOLUMN = null;


    /**
     * Retorna el catalogo al que pertenece la tabla
     * @return TABLE_CAT String → table catalog (may be null)
     */
    public String getTABLE_CAT() {
        if (UtilitiesJB.stringIsNullOrEmpty(TABLE_CAT)) {
            return null;
        }
        return TABLE_CAT;
    }

    /**
     * Sete la categoría de la tabla
     *
     * @param TABLE_CAT TABLE_CAT String → table catalog (may be null)
     */
    public void setTABLE_CAT(String TABLE_CAT) {
        this.TABLE_CAT = TABLE_CAT;
    }

    /**
     * Obtiene TABLE_SCHEM String → table schema (may be null)
     * @return TABLE_SCHEM TABLE_SCHEM String → table schema (may be null)
     */
    public String getTABLE_SCHEM() {
        if (UtilitiesJB.stringIsNullOrEmpty(TABLE_SCHEM)) {
            return null;
        }
        return TABLE_SCHEM;
    }

    /**
     * Setea el Schem de la tabla
     *
     * @param TABLE_SCHEM TABLE_SCHEM String → table schema (may be null)
     */
    public void setTABLE_SCHEM(String TABLE_SCHEM) {
        this.TABLE_SCHEM = TABLE_SCHEM;
    }

    /**
     * Retorna el nombre de la tabla a la que pertenece la columna
     * @return TABLE_NAME String → table name
     */
    public String getTABLE_NAME() {
        if (UtilitiesJB.stringIsNullOrEmpty(TABLE_NAME)) {
            return null;
        }
        return TABLE_NAME;
    }

    /**
     * Setea el nombre de la tabla a la cual pertenece la columna
     *
     * @param TABLE_NAME TABLE_NAME String → table name
     */
    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    /**
     * Retorna el nombre de la columna
     * @return COLUMN_NAME String → column name
     */
    public String getCOLUMN_NAME() {
        if (UtilitiesJB.stringIsNullOrEmpty(COLUMN_NAME)) {
            return null;
        }
        return COLUMN_NAME;
    }

    /**
     * Setea el nombre de la columna en BD's
     *
     * @param COLUMN_NAME COLUMN_NAME String → column name
     */
    public void setCOLUMN_NAME(String COLUMN_NAME) {
        this.COLUMN_NAME = COLUMN_NAME;
    }

    /**
     * Retorna la representación entera del tipo de dato de la columna
     * @return DATA_TYPE int → SQL type from java.sql.Types
     */
    public int getDATA_TYPE() {
        return DATA_TYPE;
    }

    /**
     * Setea el Data Type de la columna en SQL
     *
     * @param DATA_TYPE DATA_TYPE int → SQL type from java.sql.Types
     */
    public void setDATA_TYPE(int DATA_TYPE) {
        this.DATA_TYPE = DATA_TYPE;
    }

    /**
     * Retorna el nombre del tipo de dato de la columna
     * @return TYPE_NAME String → Data source dependent type name, for a UDT the type name is fully qualified
     */
    public String getTYPE_NAME() {
        if (UtilitiesJB.stringIsNullOrEmpty(TYPE_NAME)) {
            return null;
        }
        return TYPE_NAME;
    }

    /**
     * Setea el nombre del tipo de dato de la columna
     *
     * @param TYPE_NAME TYPE_NAME String → Data source dependent type name, for a UDT the type name is fully qualified
     */
    public void setTYPE_NAME(String TYPE_NAME) {
        this.TYPE_NAME = TYPE_NAME;
    }

    /**
     * Retorna el tamaño de la columna
     * @return COLUMN_SIZE int → column size.
     */
    public int getCOLUMN_SIZE() {
        return COLUMN_SIZE;
    }

    /**
     * Setea el tamaño de la columna en BD's
     *
     * @param COLUMN_SIZE COLUMN_SIZE int → column size.
     */
    public void setCOLUMN_SIZE(int COLUMN_SIZE) {
        this.COLUMN_SIZE = COLUMN_SIZE;
    }

    /**
     * Retorna la cantidad de digitos decimales que puede tener la columna
     * @return DECIMAL_DIGITS int → the number of fractional digits. Null is returned for data types where DECIMAL_DIGITS is not applicable.
     */
    public int getDECIMAL_DIGITS() {
        return DECIMAL_DIGITS;
    }

    /**
     * Setea la cantidad de digitos decimales de la columna
     *
     * @param DECIMAL_DIGITS DECIMAL_DIGITS int → the number of fractional digits. Null is returned for data types where DECIMAL_DIGITS is not applicable.
     */
    public void setDECIMAL_DIGITS(int DECIMAL_DIGITS) {
        this.DECIMAL_DIGITS = DECIMAL_DIGITS;
    }

    /**
     * Retorna el Num_Prec_Radix
     * @return NUM_PREC_RADIX int → Radix (typically either 10 or 2)
     */
    public int getNUM_PREC_RADIX() {
        return NUM_PREC_RADIX;
    }

    /**
     * Setea el Num Prec Radix
     *
     * @param NUM_PREC_RADIX NUM_PREC_RADIX int → Radix (typically either 10 or 2)
     */
    public void setNUM_PREC_RADIX(int NUM_PREC_RADIX) {
        this.NUM_PREC_RADIX = NUM_PREC_RADIX;
    }

    /**
     * Retorna si la columna es Nullable
     * @return NULLABLE int → is NULL allowed.
     * columnNoNulls - might not allow NULL values
     * columnNullable - definitely allows NULL values
     * columnNullableUnknown - nullability unknown
     */
    public int getNULLABLE() {
        return NULLABLE;
    }

    /**
     * Setea el entero que indica si la columna es nullable
     *
     * @param NULLABLE NULLABLE int → is NULL allowed.
     *                 * columnNoNulls - might not allow NULL values
     *                 * columnNullable - definitely allows NULL values
     *                 * columnNullableUnknown - nullability unknown
     */
    public void setNULLABLE(int NULLABLE) {
        this.NULLABLE = NULLABLE;
    }

    /**
     * Retorna la descripción de la columna en cuestión
     * @return REMARKS String → comment describing column (may be null)
     */
    public String getREMARKS() {
        if (UtilitiesJB.stringIsNullOrEmpty(REMARKS)) {
            return null;
        }
        return REMARKS;
    }

    /**
     * Setea el comentario que tiene la columna en BD's
     *
     * @param REMARKS REMARKS String → comment describing column (may be null)
     */
    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    /**
     * Retorna el valor por default para la columna en cuestión
     * @return COLUMN_DEF String → default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be null)
     */
    public String getCOLUMN_DEF() {
        if (UtilitiesJB.stringIsNullOrEmpty(COLUMN_DEF)) {
            return null;
        }
        return COLUMN_DEF;
    }

    /**
     * Setea el Valor por default que tiene la columna en BD's
     *
     * @param COLUMN_DEF COLUMN_DEF String → default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be null)
     */
    public void setCOLUMN_DEF(String COLUMN_DEF) {
        this.COLUMN_DEF = COLUMN_DEF;
    }

    /**
     * Retorna el maximo de bytes que puede poseer la columna
     * @return CHAR_OCTET_LENGTH int → for char types the maximum number of bytes in the column
     */
    public int getCHAR_OCTET_LENGTH() {
        return CHAR_OCTET_LENGTH;
    }

    /**
     * Setea el Char Octet Length
     *
     * @param CHAR_OCTET_LENGTH CHAR_OCTET_LENGTH int → for char types the maximum number of bytes in the column
     */
    public void setCHAR_OCTET_LENGTH(int CHAR_OCTET_LENGTH) {
        this.CHAR_OCTET_LENGTH = CHAR_OCTET_LENGTH;
    }

    /**
     * Retorna la posición ordinal de la columna en la tabla
     * @return ORDINAL_POSITION int → index of column in table (starting at 1)
     */
    public int getORDINAL_POSITION() {
        return ORDINAL_POSITION;
    }

    /**
     * Setea la posición ordinal de la columna
     *
     * @param ORDINAL_POSITION ORDINAL_POSITION int → index of column in table (starting at 1)
     */
    public void setORDINAL_POSITION(int ORDINAL_POSITION) {
        this.ORDINAL_POSITION = ORDINAL_POSITION;
    }

    /**
     * @return IS_NULLABLE String → ISO rules are used to determine the nullability for a column.
     * YES --- if the column can include NULLs
     * NO --- if the column cannot include NULLs
     * empty string --- if the nullability for the column is unknown
     */
    public String getIS_NULLABLE() {
        if (UtilitiesJB.stringIsNullOrEmpty(IS_NULLABLE)) {
            return null;
        }
        return IS_NULLABLE;
    }

    /**
     * Setea si la columna es nullable
     *
     * @param IS_NULLABLE IS_NULLABLE String → ISO rules are used to determine the nullability for a column.
     *                    * YES --- if the column can include NULLs
     *                    * NO --- if the column cannot include NULLs
     *                    * empty string --- if the nullability for the column is unknown
     */
    public void setIS_NULLABLE(String IS_NULLABLE) {
        this.IS_NULLABLE = IS_NULLABLE;
    }

    /**
     * Retorna el scope_catalog
     * @return SCOPE_CATALOG String → catalog of table that is the scope of a reference attribute (null if DATA_TYPE isn't REF)
     */
    public String getSCOPE_CATALOG() {
        if (UtilitiesJB.stringIsNullOrEmpty(SCOPE_CATALOG)) {
            return null;
        }
        return SCOPE_CATALOG;
    }

    /**
     * Setea el Scope Catalog
     *
     * @param SCOPE_CATALOG SCOPE_CATALOG String → catalog of table that is the scope of a reference attribute (null if DATA_TYPE isn't REF)
     */
    public void setSCOPE_CATALOG(String SCOPE_CATALOG) {
        this.SCOPE_CATALOG = SCOPE_CATALOG;
    }

    /**
     * Retorna el scope_schema de la tabla en cuestión
     * @return SCOPE_SCHEMA String → schema of table that is the scope of a reference attribute (null if the DATA_TYPE isn't REF)
     */
    public String getSCOPE_SCHEMA() {
        if (UtilitiesJB.stringIsNullOrEmpty(SCOPE_SCHEMA)) {
            return null;
        }
        return SCOPE_SCHEMA;
    }

    /**
     * Setea el SCOPE SCHEMA
     *
     * @param SCOPE_SCHEMA SCOPE_SCHEMA String → schema of table that is the scope of a reference attribute (null if the DATA_TYPE isn't REF)
     */
    public void setSCOPE_SCHEMA(String SCOPE_SCHEMA) {
        this.SCOPE_SCHEMA = SCOPE_SCHEMA;
    }

    /**
     * Retorna el scope de la tabla en cuestión
     * @return SCOPE_TABLE String → table name that this the scope of a reference attribute (null if the DATA_TYPE isn't REF)
     */
    public String getSCOPE_TABLE() {
        if (UtilitiesJB.stringIsNullOrEmpty(SCOPE_TABLE)) {
            return null;
        }
        return SCOPE_TABLE;
    }

    /**
     * Setea el Scope Table
     *
     * @param SCOPE_TABLE SCOPE_TABLE String → table name that this the scope of a reference attribute (null if the DATA_TYPE isn't REF)
     */
    public void setSCOPE_TABLE(String SCOPE_TABLE) {
        this.SCOPE_TABLE = SCOPE_TABLE;
    }

    /**
     * Retorna el tipo de data de la fuente
     * @return SOURCE_DATA_TYPE short → source type of a distinct type or user-generated Ref type,
     * SQL type from java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
     */
    public short getSOURCE_DATA_TYPE() {
        return SOURCE_DATA_TYPE;
    }

    /**
     * Setea el Source data type de la columna
     *
     * @param SOURCE_DATA_TYPE SOURCE_DATA_TYPE short → source type of a distinct type or user-generated Ref type,
     *                         * SQL type from java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
     */
    public void setSOURCE_DATA_TYPE(short SOURCE_DATA_TYPE) {
        this.SOURCE_DATA_TYPE = SOURCE_DATA_TYPE;
    }

    /**
     * Indica si la columna es autoincrementable
     * @return IS_AUTOINCREMENT String → Indicates whether this column is auto incremented
     * YES --- if the column is auto incremented
     * NO --- if the column is not auto incremented
     * empty string --- if it cannot be determined whether the column is auto incremented
     */
    public String getIS_AUTOINCREMENT() {
        if (UtilitiesJB.stringIsNullOrEmpty(IS_AUTOINCREMENT)) {
            return null;
        }
        return IS_AUTOINCREMENT;
    }

    /**
     * Setea si la columna es auto incrementable
     *
     * @param IS_AUTOINCREMENT IS_AUTOINCREMENT String → Indicates whether this column is auto incremented
     *                         * YES --- if the column is auto incremented
     *                         * NO --- if the column is not auto incremented
     *                         * empty string --- if it cannot be determined whether the column is auto incremented
     */
    public void setIS_AUTOINCREMENT(String IS_AUTOINCREMENT) {
        this.IS_AUTOINCREMENT = IS_AUTOINCREMENT;
    }

    /**
     * Indica si la columna es generada automaticamente
     * @return IS_GENERATEDCOLUMN String → Indicates whether this is a generated column
     * YES --- if this a generated column
     * NO --- if this not a generated column
     * empty string --- if it cannot be determined whether this is a generated column
     */
    public String getIS_GENERATEDCOLUMN() {
        if (UtilitiesJB.stringIsNullOrEmpty(IS_GENERATEDCOLUMN)) {
            return null;
        }
        return IS_GENERATEDCOLUMN;
    }

    /**
     * Setea el IS_GENERATEDCOLUMN String → Indicates whether this is a generated column
     *
     * @param IS_GENERATEDCOLUMN YES --- if this a generated column
     *                           NO --- if this not a generated column
     *                           empty string --- if it cannot be determined whether this is a generated column
     */
    public void setIS_GENERATEDCOLUMN(String IS_GENERATEDCOLUMN) {
        this.IS_GENERATEDCOLUMN = IS_GENERATEDCOLUMN;
    }
}
