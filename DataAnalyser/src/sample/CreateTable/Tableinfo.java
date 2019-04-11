package sample.CreateTable;

public class Tableinfo {


    String ColumnName;
    String ColumnType;
    int ColumnSize;

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public String getColumnType() {
        return ColumnType;
    }

    public void setColumnType(String columnType) {
        ColumnType = columnType;
    }

    public int getColumnSize() {
        return ColumnSize;
    }

    public void setColumnSize(int columnSize) {
        ColumnSize = columnSize;
    }

    public Tableinfo(String columnName, String columnType, int columnSize) {
        ColumnName = columnName;
        ColumnType = columnType;
        ColumnSize = columnSize;
    }
}
